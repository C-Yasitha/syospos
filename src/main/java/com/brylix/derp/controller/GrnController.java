package com.brylix.derp.controller;

import com.brylix.derp.dao.GrnDAO;
import com.brylix.derp.dao.ProductDAO;
import com.brylix.derp.dto.GrnDTO;
import com.brylix.derp.dto.GrnItemDTO;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.factory.GrnDAOFactory;
import com.brylix.derp.factory.ProductDAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GrnController {
    @FXML
    private TableView<GrnDTO> grnTable;

    @FXML
    private TableColumn<GrnDTO, String> idColumn;

    @FXML
    private TableColumn<GrnDTO, String> dateColumn;

    @FXML
    private TableColumn<GrnDTO, String> supplierColumn;

    @FXML
    private TableColumn<GrnDTO, String> totalColumn;

    @FXML
    private TableColumn<GrnDTO, String> isShelfColumn;

    @FXML
    private TableColumn<GrnDTO, Void> actionColumn;

    private ObservableList<GrnDTO> grns;
    private ProductDAO productDAO;
    private GrnDAO grnDAO;
    private Float total= 0.00F;

    public void initialize() {
        loadProducts();
    }

    private void loadProducts() {
        grns = FXCollections.observableArrayList();

        try {
            List<GrnDTO> genList = grnDAO.getAllGrns();

            for (GrnDTO grn : genList) {
                grn.setShelfStatus(grn.isShelf() ? "Shelf" : "Store");
                // Add the Product to the list
                grns.add(grn);
            }
            configureTableColumns();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }

    private void configureTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("grnDate"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        isShelfColumn.setCellValueFactory(new PropertyValueFactory<>("shelfStatus"));
        actionColumn.setCellFactory(param -> new TableCell<GrnDTO, Void>() {
            private final Button moveButton = new Button("Move");

            {
                moveButton.setOnAction(event -> {
                    GrnDTO grn = getTableRow().getItem();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Move Product");
                    alert.setContentText("Are you sure you want to Move this grn?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // User clicked OK, delete the product
                        grnDAO.moveGrn(grn);
                        // Perform any additional actions or UI updates after deleting the product
                        showAlert(Alert.AlertType.INFORMATION, "Grn Moved.");
                        loadProducts();
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(moveButton));
                }
            }
        });
        grnTable.setItems(grns);
    }
    public GrnController() {
        this.productDAO = ProductDAOFactory.getProductDAO();
        this.grnDAO = GrnDAOFactory.getGrnDAO();
    }

    @FXML
    private void handleAddGrnButton() {
        showDialog();
    }

    private void showDialog() {
        Dialog<GrnDTO> dialog = new Dialog<>();
        dialog.setTitle("Add Grn");
        dialog.setHeaderText(null);

        total= 0.00F;

        // Set up the dialog buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Supplier ID section
        Label supplierIdLabel = new Label("Supplier Name:");
        TextField supplierIdTextField = new TextField();

        Label isShelfLabel = new Label("Is Shelf:");
        ChoiceBox<Boolean> isShelfComboBox = new ChoiceBox<>();
        isShelfComboBox.getItems().addAll(true, false);

        Label totalLabel = new Label("Total:");

        // Product section
        Label productCodeLabel = new Label("Product Code:");
        TextField productCodeTextField = new TextField();

        Label productQtyLabel = new Label("Quantity:");
        TextField productQtyTextField = new TextField();

        Label productCostLabel = new Label("Cost:");
        TextField productCostTextField = new TextField();

        Label expiryDateLabel = new Label("Expiry Date:");
        DatePicker expiryDatePicker = new DatePicker();

        Button addToTable = new Button("+");

        // Create the table
        TableView<GrnItemDTO> table = new TableView<>();
        table.setEditable(true);

        TableColumn<GrnItemDTO, String> codeColumn = new TableColumn<>("Product Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<GrnItemDTO, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<GrnItemDTO, String> expColumn = new TableColumn<>("Expire");
        expColumn.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        TableColumn<GrnItemDTO, Double> qtyColumn = new TableColumn<>("Quantity");
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));

        TableColumn<GrnItemDTO, Double> costColumn = new TableColumn<>("Cost");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        table.getColumns().addAll(codeColumn, nameColumn, expColumn, qtyColumn, costColumn);

        // Set up the Add button action
        addToTable.setOnAction(event -> {
            String productCode = productCodeTextField.getText();
            ProductDTO selectedProduct = productDAO.getProductByCode(productCode,false);
            if(selectedProduct!=null){
                try{
                    String quantity = productQtyTextField.getText();
                    String cost = productCostTextField.getText();
                    LocalDate expiryDate = expiryDatePicker.getValue();
                    Date expiryDateConverted = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    total += Float.parseFloat(quantity)*Float.parseFloat(cost);
                    String productName = selectedProduct.getProductName();

                    GrnItemDTO gtnItem = new GrnItemDTO(selectedProduct.getId(), productName, expiryDateConverted,Float.parseFloat(quantity), Float.parseFloat(cost));
                    table.getItems().add(gtnItem);

                    productCodeTextField.setText("");
                    productQtyTextField.setText("");
                    productCostTextField.setText("");
                    productCodeTextField.requestFocus();
                    expiryDatePicker.setValue(null);
                    totalLabel.setText("Total: "+total);
                }catch(NumberFormatException e){
                    showAlert(Alert.AlertType.ERROR, "Check entered numbers");
                }

                //change total label
            }else{
                showAlert(Alert.AlertType.ERROR, "Product not found.");
            }


        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, supplierIdLabel, supplierIdTextField);
        gridPane.addRow(1, isShelfLabel, isShelfComboBox);
        gridPane.addRow(2, totalLabel);
        gridPane.addRow(4, productCodeLabel, productCodeTextField);
        gridPane.addRow(5, productQtyLabel, productQtyTextField);
        gridPane.addRow(6, productCostLabel, productCostTextField);
        gridPane.addRow(7, expiryDateLabel, expiryDatePicker);
        gridPane.addRow(8, addToTable);
        gridPane.addRow(9, table);

        dialog.setResultConverter(dialogButton -> {
            try {
                if(!supplierIdTextField.getText().isEmpty() && total>0 && isShelfComboBox.getValue()!=null){
                    return new GrnDTO(supplierIdTextField.getText(), total, isShelfComboBox.getValue(), table.getItems());
                }else{
                    showAlert(Alert.AlertType.ERROR, "Input data error");
                    return null;
                }

            }catch (Exception e){
                return null;
            }
        });

        dialog.getDialogPane().setContent(gridPane);
        Optional<GrnDTO> result = dialog.showAndWait();
        result.ifPresent(grn -> {
            if(grn!=null){
                // productDAO.saveProduct(product);
                grnDAO.saveGrn(grn);
                //  loadProducts();
                loadProducts();
            }

        });
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}
