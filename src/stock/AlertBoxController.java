package stock;

import Admin.ProfileData;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlertBoxController implements Initializable {


    @FXML
    private TextField totalsharetextfield;

    @FXML
    private TextField shareratetextfield;

    @FXML
    private TextField announceddatetextfield;

    @FXML
     private Label idseller;

    @FXML
    private JFXButton buyshare;



    public String fn;
    public String ln;
    public String cont;
    public String pa;
    public String dmat;
    public String cits;
    public String pass;





    public static String userName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection conn = Database_connection.connect();
            String sql = "SELECT * FROM `sign up` WHERE Citizen=?";
            PreparedStatement stmn = conn.prepareStatement(sql);
            stmn.setString(1, userName);
            ResultSet rs = stmn.executeQuery();
            while (rs.next()) {
                fn = rs.getString("FirstName");
                ln = rs.getString("LastName");
                cont = rs.getString("Contact");
                pa = rs.getString("Permanent");
                dmat = rs.getString("DMAT");
                cits = rs.getString("Citizen");
                pass = rs.getString("Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(String id,String TSA,String share,String datee){
        idseller.setText(id);
        totalsharetextfield.setText(TSA);
        shareratetextfield.setText(share);
        announceddatetextfield.setText(datee);


    }
    public static void getUser(String s) {
        userName = s;
        // System.out.println("Caught "+userName+" and in getUser");
    }


    //when buy shares button is clicked
    @FXML
    public void BuyShares(ActionEvent event){

        try {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setHeaderText("delete");
            confirm.setContentText("Are you Sure you want to Buy Shares and have transactions?");
            Optional<ButtonType> action = confirm.showAndWait();
            if (action.get() == ButtonType.OK) {
                Connection con = Database_connection.connect();
                PreparedStatement psd;
                String sql = "INSERT INTO `company_buy` (BuyersName,BuyersCitizen_no,BuyingRate,Seller,Date) VALUES(?,?,?,?,?)";
                psd = con.prepareStatement(sql);
                psd.setString(1, fn);
                psd.setString(2, userName);
                psd.setString(3, shareratetextfield.getText());
                psd.setString(4, idseller.getText());
                psd.setString(5, announceddatetextfield.getText());
                psd.execute();


            }

            buyshare.getScene().getWindow().hide();


            } catch(SQLException e1){
                System.out.println(e1.getMessage());
            }


    }
}
