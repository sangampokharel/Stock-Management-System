/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SellItemsController implements Initializable {

    Connection cons;
    Statement stmn;

    @FXML
    private AnchorPane sellAnchor;

    @FXML
    private TableView<CompanyData> companyTable;
    @FXML
    private TableColumn<CompanyData, String> idcolumn;

    @FXML
    private TableColumn<CompanyData, String> totalshares;

    @FXML
    private TableColumn<CompanyData, String> sharerate;

    @FXML
    private TableColumn<CompanyData, String> announceDate;


    String Tsa;
    String sr;
    String da;
    public ObservableList<CompanyData> data;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database_connection db = new Database_connection();
        try {
            cons = db.connect();
            stmn = cons.createStatement();
            String sql = "SELECT * FROM `company`";
            data = FXCollections.observableArrayList();
            ResultSet rs = stmn.executeQuery(sql);
            while (rs.next()) {
                data.add(new CompanyData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        idcolumn.setCellValueFactory(new PropertyValueFactory<CompanyData, String>("id"));
        totalshares.setCellValueFactory(new PropertyValueFactory<CompanyData, String>("ts"));
        sharerate.setCellValueFactory(new PropertyValueFactory<CompanyData, String>("srr"));
        announceDate.setCellValueFactory(new PropertyValueFactory<CompanyData, String>("sdd"));

        this.companyTable.setItems(data);



        companyTable.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               // CompanyData profileObject=(CompanyData) companyTable.getSelectionModel().getSelectedItem();

                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("alert.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                AlertBoxController alertBoxController=loader.getController();
                alertBoxController.setData(companyTable.getSelectionModel().getSelectedItem().getId(),companyTable.getSelectionModel().getSelectedItem().getTs(),companyTable.getSelectionModel().getSelectedItem().getSrr(),companyTable.getSelectionModel().getSelectedItem().getSdd());
                Parent p=loader.getRoot();
                Stage stage=new Stage();
                Scene scene=new Scene(p);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            }
        });

    }


    @FXML
    private void Back(ActionEvent event) {

    }

    @FXML
    public void LoadGraph(ActionEvent event) {
        Stage graph=new Stage();
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("Graph.fxml"));
            Scene scene=new Scene(root);
            graph.setTitle("company details and information");
            graph.setScene(scene);
            graph.setResizable(false);
            graph.show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }




}



