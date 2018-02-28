/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.management.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController implements Initializable {
 Connection con;
PreparedStatement stmn;
private Database_connection db;

ResultSet rs;

    @FXML
    private JFXButton signup;
    @FXML
    private JFXTextField citinum;
    @FXML
    private JFXPasswordField paa;
    @FXML
    private JFXButton signin;
    
    
    


    //when sign up button is pressed
    @FXML
    public void signUp() throws IOException{
        signup.getScene().getWindow().hide();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.setResizable(false);
         stage.show();
}
    
    
    //when sign in button is pressed
    @FXML
    public void signIn(ActionEvent event) throws SQLException{
        
        String citi=citinum.getText();
        String paas=paa.getText();
        // ProfileController p =new ProfileController(citi);
       if(citi.isEmpty() || paas.isEmpty()){
            Alert al=new Alert(Alert.AlertType.ERROR);
            al.getHeaderText();
            al.setContentText("Please Fill Out the Form ");
            al.showAndWait();
        }
        
        else{
              
           try{
           String sql="SELECT * FROM `sign up`WHERE Citizen='"+citi+"' && Password='"+paas+"'";
           con=db.connect();
            stmn=con.prepareStatement(sql);
            rs=stmn.executeQuery();
            
             if(rs.next()){

                 //Send the name of the logged in user to retrieve its remaining data from the database
               

                 //Now load the new stage and as soon as new stage is load getUser() gets called
                signin.getScene().getWindow().hide();
                 Stage stage=new Stage();
                 Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                  Scene scene = new Scene(root);
                  stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    
                    ProfileController p=new ProfileController(citi);
             
                    
                    
             }else{
                 Alert all=new Alert(Alert.AlertType.ERROR);
                 all.setHeaderText(null);
                 all.setContentText("Username and password doesnot match");
                 all.show();
             }
           stmn.close();
           rs.close();
           }catch(Exception e){
          
           System.out.println(e);
           }
    
        }

}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        citinum.setStyle("-fx-text-inner-color:white;"
                + "-fx-text-inner-color:white;");
        paa.setStyle("-fx-text-inner-color:white;"
                + "-fx-prompt-text-fill:white;");
      
      
        
       db=new Database_connection();
    
       
        
      
    } 
    

    
}
