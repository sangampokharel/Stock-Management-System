package Admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import stock.Database_connection;

import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable {
    @FXML
    private JFXButton AdminLoginBtn;

    @FXML
    private JFXTextField admin;

    @FXML
    private JFXPasswordField adminpassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin.setStyle("-fx-text-inner-color:white;"
                + "-fx-text-inner-color:white;");
        adminpassword.setStyle("-fx-text-inner-color:white;"
                + "-fx-prompt-text-fill:white;");

    }

    //when admin login button is clicked


    public void AdminLoginBtn(javafx.event.ActionEvent event) {
        String adminCitizen=admin.getText();
        String adminPass=adminpassword.getText();

        try {
            Connection con=Database_connection.connect();
            PreparedStatement psd;
            String sql="SELECT * FROM `admin` WHERE Citizen=? AND Password=?";
            psd=con.prepareStatement(sql);
            psd.setString(1,adminCitizen);
            psd.setString(2,adminPass);
            ResultSet rs=psd.executeQuery();
            if(rs.next()){
                try {
                    Stage stage=new Stage();
                    Parent root= FXMLLoader.load(getClass().getResource("Admin.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    stage.setResizable(false);
                    AdminLoginBtn.getScene().getWindow().hide();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error please provide correct information");
                alert.setContentText("citizen number and password combination failed");
                alert.showAndWait();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
