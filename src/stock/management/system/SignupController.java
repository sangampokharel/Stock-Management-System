/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.management.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class SignupController implements Initializable {

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField Dmat;
    @FXML
    private JFXTextField add;
    @FXML
    private JFXTextField contact;
    @FXML
    private JFXTextField citizen;
    @FXML
    private JFXTextField pass;
    @FXML
    private JFXButton upload;
    @FXML
    private Label picname;
    
    @FXML
    private JFXButton Login;
    
    //FileInputStream fid;
  
PreparedStatement psd;
Database_connection dbc=new Database_connection();
//File selectedFile;


   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstname.setStyle("-fx-prompt-text-fill:white;"
                + "-fx-text-inner-color:white;");
        lastname.setStyle("-fx-prompt-text-fill:white;"
                + "-fx-text-inner-color:white;");
        Dmat.setStyle("-fx-prompt-text-fill:white;"
                + "-fx-text-inner-color:white;");
        add.setStyle("-fx-prompt-text-fill:white;"
                + "-fx-text-inner-color:white;");
        contact.setStyle("-fx-prompt-text-fill:white;"
                + "-fx-text-inner-color:white;");
        citizen.setStyle("-fx-prompt-text-fill:white;"
                + "-fx-text-inner-color:white;");
        pass.setStyle("-fx-prompt-text-fill:white;"
                + "-fx-text-inner-color:white;");
        
    }   
    
    //when register button is clicked

    @FXML
    private void register(ActionEvent event) throws SQLException {
        

        Connection con=dbc.connect();
        String fn=firstname.getText();
        String ln=lastname.getText();
        String dn=Dmat.getText(); 
        String ad=add.getText();
        String cn=contact.getText();
        String cin=citizen.getText();
        String pan=pass.getText();
        
        if(fn.isEmpty()||ln.isEmpty()||dn.isEmpty()||ad.isEmpty()||cn.isEmpty()||cin.isEmpty()||fn.isEmpty()||pan.isEmpty()){
             Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Please Fill Out the Form Completely");
           alert.showAndWait();
           return;
        }else{
           String sql="INSERT INTO `sign up` VALUES(?,?,?,?,?,?,?)";
          try{
           psd=con.prepareStatement(sql);
            psd.setString(1,fn);
            psd.setString(2,ln);
            psd.setString(3,dn);
            psd.setString(4,ad);
            psd.setString(5,cn);
            psd.setString(6,cin);
            psd.setString(7,pan);
            int b=psd.executeUpdate();
            ((Node)event.getSource()).getScene().getWindow().hide();
            if(b==1){
                Alert al=new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Data Registered Suceessfully");
                al.showAndWait();
            }
          }catch(Exception e){
              System.out.println(e);
          }
         
        }
        
        
          
          
    }
//when upload button is pressed
 /*
    @FXML
    private void uploadphoto(ActionEvent event) {
        FileChooser fl=new FileChooser();
        Window stage = null;
        File selectedFile=fl.showOpenDialog(stage);
        fl.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "txt"),
                new ExtensionFilter("Image Files", "*.png","*.jpg","*.gif"),
                new ExtensionFilter("Audio Files", "*wav","*.mp3","*.aac"),
                new ExtensionFilter("All Files", "*.*")
                
        );
       
        if(selectedFile!=null){
            
            picname.setText(selectedFile.getName());
                
            
            
        }
    }
*/

    @FXML
    private void uploadphoto(ActionEvent event) {
    }
    
    //when Login Button is pressed
       @FXML
    void LoginBtn(ActionEvent event) throws IOException {
            Login.getScene().getWindow().hide();
            Stage ln =new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene=new Scene(root);
            ln.setScene(scene);
            ln.show();
            ln.setResizable(false);
            
    }
    
}
