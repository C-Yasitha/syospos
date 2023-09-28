package com.brylix.derp.controller;

import com.brylix.derp.dao.ProductDAO;
import com.brylix.derp.dto.ProductDTO;
import com.brylix.derp.factory.ProductDAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductsController {

    @FXML
    private TableView<ProductDTO> productsTable;

    @FXML
    private TableColumn<ProductDTO, String> codeColumn;

    @FXML
    private TableColumn<ProductDTO, String> nameColumn;

    @FXML
    private TableColumn<ProductDTO, String> descriptionColumn;

    @FXML
    private TableColumn<ProductDTO, String> categoryColumn;

    @FXML
    private TableColumn<ProductDTO, String> brandColumn;

    @FXML
    private TableColumn<ProductDTO, BigDecimal> priceColumn;

    @FXML
    private TableColumn<ProductDTO, Void> actionColumn;

    private ObservableList<ProductDTO> products;
    private ProductDAO productDAO;
    public ProductsController() {
        productDAO = ProductDAOFactory.getProductDAO();
    }

    public void initialize() {
        loadProducts();
    }

    private void loadProducts() {
        products = FXCollections.observableArrayList();

        try {
            List<ProductDTO> productList = productDAO.getAllProducts();

            for (ProductDTO product : productList) {
                // Add the Product to the list
                products.add(product);
            }
            configureTableColumns();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception according to your application's error handling mechanism
        }
    }
    private void configureTableColumns() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        actionColumn.setCellFactory(param -> new TableCell<ProductDTO, Void>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");

            {
                updateButton.setOnAction(event -> {
                    ProductDTO product = getTableRow().getItem();
                    // Handle update button action for the product
                    productAddOrUpdate(product);
                });

                deleteButton.setOnAction(event -> {
                    ProductDTO product = getTableRow().getItem();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Delete Product");
                    alert.setContentText("Are you sure you want to delete this product?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // User clicked OK, delete the product
                        productDAO.deleteProduct(product.getProductCode());
                        // Perform any additional actions or UI updates after deleting the product
                        showAlert(Alert.AlertType.ERROR, "Product deleted.");
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
                    setGraphic(new HBox(updateButton, deleteButton));
                }
            }
        });
        productsTable.setItems(products);
    }

    @FXML
    private void handleAddProductButton() {
        productAddOrUpdate(null);
    }




    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void productAddOrUpdate(ProductDTO productToUpdate){
            Dialog<ProductDTO> dialog = new Dialog<>();
            dialog.setTitle("Add Product");
            dialog.setHeaderText(null);

            // Set the button types (Save and Cancel)
            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            // Create labels and fields for each input
            Label codeLabel = new Label("Code:");
            Label nameLabel = new Label("Name:");
            Label descriptionLabel = new Label("Description:");
            Label priceLabel = new Label("Price:");
            Label categoryLabel = new Label("Category:");
            Label brandLabel = new Label("Brand:");
            Label lowLevelLabel = new Label("Low Level:");
            Label isServiceLabel = new Label("Is Service:");
            Label productWeightLabel = new Label("Product Weight:");
            Label imageLabel = new Label("Image:");

            TextField codeField = new TextField();
            TextField nameField = new TextField();
            TextField descriptionField = new TextField();
            TextField priceField = new TextField();
            TextField categoryField = new TextField();
            TextField brandField = new TextField();
            ChoiceBox<Integer> lowLevelChoiceBox = new ChoiceBox<>();
            ChoiceBox<Boolean> isServiceChoiceBox = new ChoiceBox<>();
            TextField productWeightField = new TextField();
            FileChooser fileChooser = new FileChooser();
            Button uploadButton = new Button("Upload");

            // Set up lowLevel choice box options
            lowLevelChoiceBox.getItems().addAll(1, 2, 3, 4, 5);

            // Set up isService choice box options
            isServiceChoiceBox.getItems().addAll(true, false);

            // Set the dialog content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(codeLabel, 1, 1);
            grid.add(codeField, 2, 1);
            grid.add(nameLabel, 1, 2);
            grid.add(nameField, 2, 2);
            grid.add(descriptionLabel, 1, 3);
            grid.add(descriptionField, 2, 3);
            grid.add(priceLabel, 1, 4);
            grid.add(priceField, 2, 4);
            grid.add(categoryLabel, 1, 5);
            grid.add(categoryField, 2, 5);
            grid.add(brandLabel, 1, 6);
            grid.add(brandField, 2, 6);
            grid.add(lowLevelLabel, 1, 7);
            grid.add(lowLevelChoiceBox, 2, 7);
            grid.add(isServiceLabel, 1, 8);
            grid.add(isServiceChoiceBox, 2, 8);
            grid.add(productWeightLabel, 1, 9);
            grid.add(productWeightField, 2, 9);
            grid.add(imageLabel, 1, 10);
            grid.add(uploadButton, 2, 10);

            dialog.getDialogPane().setContent(grid);

            // Enable the Save button only when all fields are filled
            Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
            saveButton.setDisable(true);


            // Set the initial field values if updating
            if (productToUpdate != null) {
                codeField.setText(productToUpdate.getProductCode());
                nameField.setText(productToUpdate.getProductName());
                descriptionField.setText(productToUpdate.getProductDescription());
                priceField.setText(String.valueOf(productToUpdate.getPrice()));
                categoryField.setText(productToUpdate.getCategory());
                brandField.setText(productToUpdate.getBrand());
                lowLevelChoiceBox.setValue(productToUpdate.getLowLevel());
                isServiceChoiceBox.setValue(productToUpdate.isService());
                productWeightField.setText(String.valueOf(productToUpdate.getProductWeight()));
                imageLabel.setText(productToUpdate.getProductImage());
            }

            // Field validation listeners
            codeField.textProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            nameField.textProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            descriptionField.textProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            priceField.textProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            categoryField.textProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            brandField.textProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            lowLevelChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            isServiceChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            productWeightField.textProperty().addListener((observable, oldValue, newValue) -> {
                updateSaveButtonState(saveButton, codeField, nameField, descriptionField, priceField, categoryField,
                        brandField, lowLevelChoiceBox, isServiceChoiceBox, productWeightField);
            });

            // Image upload button handler
            uploadButton.setOnAction(event -> {
                File file = fileChooser.showOpenDialog(dialog.getOwner());
                if (file != null) {
                    // Upload logic goes here
                    // You can save the image file path or perform any required processing
                    // For simplicity, we will just display the selected file name in a label
                    imageLabel.setText(file.getName());
                }
            });

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    String productCode = codeField.getText();
                    String productName = nameField.getText();
                    String productDescription = descriptionField.getText();
                    String productImage = imageLabel.getText(); // Use the uploaded image path or file name
                    int lowLevel = lowLevelChoiceBox.getValue();
                    boolean isService = isServiceChoiceBox.getValue();
                    double productWeight = Double.parseDouble(productWeightField.getText());
                    Date createdAt = new Date();
                    Date updatedAt = new Date();
                    String category = categoryField.getText();
                    String brand = brandField.getText();
                    Float price = Float.parseFloat(priceField.getText());

                    if (productToUpdate != null) {
                        // Update the existing product
                        productToUpdate.setProductCode(productCode);
                        productToUpdate.setProductName(productName);
                        productToUpdate.setProductDescription(productDescription);
                        productToUpdate.setProductImage(productImage);
                        productToUpdate.setLowLevel(lowLevel);
                        productToUpdate.setService(isService);
                        productToUpdate.setProductWeight(productWeight);
                        productToUpdate.setUpdatedAt(updatedAt);
                        productToUpdate.setCategory(category);
                        productToUpdate.setBrand(brand);
                        productToUpdate.setPrice(price);

                        return productToUpdate;
                    } else {
                        // Create a new product
                        return new ProductDTO(productCode, productName, productDescription, productImage, lowLevel,
                                isService, productWeight, createdAt, updatedAt, category, brand, price);
                    }
                }
                return null;
            });

            Optional<ProductDTO> result = dialog.showAndWait();
            result.ifPresent(product -> {
                // Save or update the product using the product repository
                if (productToUpdate != null) {
                    // Update the existing product
                    productDAO.updateProduct(product);
                    showAlert(Alert.AlertType.INFORMATION, "Product updated successfully.");
                } else {
                    // Create a new product
                    saveProductWithValidation(product);
                    showAlert(Alert.AlertType.INFORMATION, "Product created successfully.");
                }
                // Refresh the product list view
                loadProducts();
            });
    }

    public boolean saveProductWithValidation(ProductDTO product){
        if(product.getProductCode()!=null && product.getProductName()!=null && product.getPrice()!=null && product.getLowLevel()>0){
            productDAO.saveProduct(product);
            return true;
        }else{
            return false;
        }
    }



    private void updateSaveButtonState(Node saveButton, TextField codeField, TextField nameField,
                                       TextField descriptionField, TextField priceField,
                                       TextField categoryField, TextField brandField,
                                       ChoiceBox<Integer> lowLevelChoiceBox, ChoiceBox<Boolean> isServiceChoiceBox,
                                       TextField productWeightField) {
        saveButton.setDisable(codeField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() ||
                descriptionField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() ||
                categoryField.getText().trim().isEmpty() || brandField.getText().trim().isEmpty() ||
                lowLevelChoiceBox.getSelectionModel().isEmpty() || isServiceChoiceBox.getSelectionModel().isEmpty() ||
                productWeightField.getText().trim().isEmpty());
    }
}
