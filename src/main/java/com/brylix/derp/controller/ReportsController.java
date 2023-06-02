package com.brylix.derp.controller;

import com.brylix.derp.dao.ReportDAO;
import com.brylix.derp.dao.ReportDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private WebView webView;

    private WebEngine webEngine;

    private ReportDAO reportDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webView.getEngine();
        this.reportDAO = new ReportDAOImpl();
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        String selectedDate = datePicker.getValue().toString();
        int selectedItem = comboBox.getSelectionModel().getSelectedIndex();

        switch (selectedItem){
            case 0:
                TotalSale(selectedDate);
                break;
        }



    }

    private void TotalSale(String date){
        String htmlcode= "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Sales Report</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            border-collapse: collapse;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        th, td {\n" +
                "            border: 1px solid black;\n" +
                "            padding: 8px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Sales Report</h1>\n" +
                "    <h2>Date: "+date+"</h2>\n";
        htmlcode += reportDAO.TotalSale(date);
        htmlcode +=
                "</body>\n" +
                "</html>\n";

        webEngine.loadContent(htmlcode);
    }
}
