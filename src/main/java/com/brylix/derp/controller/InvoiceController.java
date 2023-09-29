package com.brylix.derp.controller;

import com.brylix.derp.dao.InvoiceDAO;
import com.brylix.derp.dao.ProductDAO;
import com.brylix.derp.dto.*;
import com.brylix.derp.factory.InvoiceDAOFactory;
import com.brylix.derp.factory.ProductDAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InvoiceController {

    @FXML
    private TableView<InvoiceDTO> invoiceTable;
    @FXML
    private TableColumn<InvoiceDTO, String> idColumn;
    @FXML
    private TableColumn<InvoiceDTO, String> dateColumn;
    @FXML
    private TableColumn<InvoiceDTO, String> customerColumn;
    @FXML
    private TableColumn<InvoiceDTO, String> totalColumn;
    @FXML
    private TableColumn<InvoiceDTO, String> discountColumn;
    @FXML
    private TableColumn<InvoiceDTO, String> tenderedColumn;

    private ProductDAO productDAO;
    private InvoiceDAO invoiceDAO;
    Float total= 0.00F; // store invoice total
    Float tendered =  0.00F;

    private ObservableList<InvoiceDTO> invoices;
    public void initialize() {
        loadProducts();
    }

    private void loadProducts() {
        invoices = FXCollections.observableArrayList();

        try {
            List<InvoiceDTO> invoiceList = invoiceDAO.getAllInvoices();

            for (InvoiceDTO invoice : invoiceList) {
                // Add the Product to the list
                invoices.add(invoice);
            }
            configureTableColumns();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Invoice list  :"+e.getMessage());
            // Handle the exception according to your application's error handling mechanism
        }
    }

    private void configureTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("invDate"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        tenderedColumn.setCellValueFactory(new PropertyValueFactory<>("tendered"));

        invoiceTable.setItems(invoices);
    }
    public InvoiceController() {
        productDAO = ProductDAOFactory.getProductDAO();
        invoiceDAO = InvoiceDAOFactory.getInvoiceDAO();
    }

    @FXML
    private void handleAddGrnButton() {
        showDialog();
    }

    private void showDialog() {
        Dialog<InvoiceDTO> dialog = new Dialog<>();
        dialog.setTitle("Add Invoice");
        dialog.setHeaderText(null);

        total= 0.00F;
        tendered =  0.00F;

        // Set up the dialog buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        Label invoiceDateLabel = new Label("Invoice Date:");
        DatePicker invoiceDatePicker = new DatePicker();

        // Supplier ID section
        Label supplierIdLabel = new Label("Customer Name:");
        TextField supplierIdTextField = new TextField();

        Label totalLabel = new Label("Total:");

        Label discountLabel = new Label("Enter Discount Rs:");
        TextField discountTextField = new TextField();

        Label cashLabel = new Label("Enter Cash:");
        TextField cashTextField = new TextField();

        Label changeLabel = new Label("Change:");

        // Product section
        Label productCodeLabel = new Label("Product Code:");
        TextField productCodeTextField = new TextField();

        Label productQtyLabel = new Label("Quantity:");
        TextField productQtyTextField = new TextField();

        Label productCostLabel = new Label("Price:");
        TextField productCostTextField = new TextField();

        Button addToTable = new Button("+");

        // Create the table
        TableView<InvoiceItemDTO> table = new TableView<>();
        table.setEditable(true);

        TableColumn<InvoiceItemDTO, String> codeColumn = new TableColumn<>("Product Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("productCode"));

        TableColumn<InvoiceItemDTO, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<InvoiceItemDTO, Double> qtyColumn = new TableColumn<>("Quantity");
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));

        TableColumn<InvoiceItemDTO, Double> costColumn = new TableColumn<>("Price");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(codeColumn, nameColumn, qtyColumn, costColumn);

        // Set up the Add button action
        addToTable.setOnAction(event -> {
            String productCode = productCodeTextField.getText();
            try {
                ProductDTO selectedProduct = productDAO.getProductByCode(productCode, true);
                if (selectedProduct != null) {
                    try {
                        String quantity = productQtyTextField.getText();
                        Float qty = Float.parseFloat(quantity);
                        if (selectedProduct.getQty() > 0 && selectedProduct.getQty() >= qty) {
                            String price = productCostTextField.getText();
                            total += Float.parseFloat(quantity) * Float.parseFloat(price);
                            String productName = selectedProduct.getProductName();

                            InvoiceItemDTO invoiceItem = new InvoiceItemDTO(selectedProduct.getId(), productName, Float.parseFloat(price), qty);
                            table.getItems().add(invoiceItem);

                            productCodeTextField.setText("");
                            productQtyTextField.setText("");
                            productCostTextField.setText("");
                            productCodeTextField.requestFocus();
                            totalLabel.setText("Total: " + total);
                        } else {
                            showAlert(Alert.AlertType.ERROR, "No stock in the shelf or expired");
                        }

                    } catch (NumberFormatException e) {
                        showAlert(Alert.AlertType.ERROR, "Check entered numbers");
                    }

                    //change total label
                } else {
                    showAlert(Alert.AlertType.ERROR, "Product not found.");
                }
            }catch(Exception e){
                showAlert(Alert.AlertType.ERROR, "Product error : "+e.getMessage());
            }
        });

        cashTextField.setOnKeyTyped(event->{
            try{
                tendered = Float.parseFloat(cashTextField.getText());
                float discount=0;
                try{
                    discount = Float.parseFloat(discountTextField.getText());
                }catch(NumberFormatException ee){}

                if(tendered>0){
                    changeLabel.setText("Change:"+(total-discount-tendered));
                }
            }catch(NumberFormatException nue){}
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, invoiceDateLabel, invoiceDatePicker);
        gridPane.addRow(1, supplierIdLabel, supplierIdTextField);
        gridPane.addRow(2, productCodeLabel, productCodeTextField);
        gridPane.addRow(3, productQtyLabel, productQtyTextField);
        gridPane.addRow(4, productCostLabel, productCostTextField);
        gridPane.addRow(5, addToTable);
        gridPane.addRow(6, table);
        gridPane.addRow(7, totalLabel);
        gridPane.addRow(8, discountLabel,discountTextField);
        gridPane.addRow(9, cashLabel,cashTextField);
        gridPane.addRow(10, changeLabel);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    if (!supplierIdTextField.getText().isEmpty() && total > 0 && tendered > 0 && invoiceDatePicker.valueProperty() != null) {
                        LocalDate invoiceDate = invoiceDatePicker.getValue();
                        Date invoiceDateConverted = Date.from(invoiceDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        float discount = 0;
                        try {
                            discount = Float.parseFloat(discountTextField.getText());
                        } catch (NumberFormatException ee) {
                        }
                        return new InvoiceDTO(supplierIdTextField.getText(), invoiceDateConverted, total, discount, tendered, table.getItems());
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Input data error");
                        return null;
                    }

                } catch (Exception e) {
                    return null;
                }
            }else{
                return null;
            }
        });

        dialog.getDialogPane().setContent(gridPane);
        Optional<InvoiceDTO> result = dialog.showAndWait();
        result.ifPresent(invoice -> {
            if(invoice!=null){
                new Thread(() -> {
                    try {
                        invoiceDAO.saveInvoice(invoice);
                        Platform.runLater(() -> {
                            loadProducts();
                        });
                    }catch(Exception e){
                        showAlert(Alert.AlertType.ERROR, "Save invoice : "+e.getMessage());
                    }
                }).start();
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
