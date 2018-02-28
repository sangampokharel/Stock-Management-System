/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author dell-i5
 */
public class ProfileController implements Initializable{

    Database_connection db=new Database_connection();
    PreparedStatement psd;
    Connection cons;
    ResultSet rs;
    
    @FXML
   private TextField fnn;

    ProfileController(String citi) {
        String user=citi;
        System.out.println(user);
    }
   
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           cons= db.connect();
           String sql="SELECT * FROM `sign up` WHERE Citizen='"+123456+"'";
           Statement tsmn=cons.createStatement();
           rs=tsmn.executeQuery(sql);
           while(rs.next()){
               String fn=rs.getString("FirstName");
               System.out.println(fn);
               fnn.setText(fn);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
