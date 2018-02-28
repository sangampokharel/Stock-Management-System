package stock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HOMEController implements Initializable {


    @FXML
    private AnchorPane home;

    @FXML
    private TextField fnn;

    @FXML
    private TextField lnn;

    @FXML
    private TextField pm;

    @FXML
    private TextField con;

    @FXML
    private TextField cit;

    @FXML
    private TextField dma;

    @FXML
    private PasswordField passwordfield;

    private static String Username;
    Database_connection db;
    PreparedStatement psd;
    ResultSet rs;
    private Connection conn;

    private String fn;
    private String ln;
    private String pa;
    private String cont;
    private String dmat;
    private String cits;
    private String paas;
    public static void getUser(String citi) {
        Username = citi;
        System.out.println(Username);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = new Database_connection();

        String sql = "SELECT * FROM `sign up` WHERE Citizen=?";
        try {
            conn = db.connect();
            psd = conn.prepareStatement(sql);
            psd.setString(1, Username);
            rs = psd.executeQuery();
            while (rs.next()) {
                fn = rs.getString("FirstName");
                ln = rs.getString("LastName");
                cont = rs.getString("Contact");
                pa = rs.getString("Permanent");
                dmat = rs.getString("DMAT");
                cits = rs.getString("Citizen");
                paas=rs.getString("Password");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        fnn.setText(fn);
        lnn.setText(ln);
        pm.setText(pa);
        con.setText(cont);
        cit.setText(cits);
        dma.setText(dmat);
        passwordfield.setText(paas);
    }


    @FXML
    public void update(ActionEvent event) {
        try {
            Connection conn = Database_connection.connect();
            String sql = "UPDATE `sign up` SET FirstName=?,LastName=?,DMAT=?,Permanent=?,Contact=?,Citizen=?,Password=? WHERE Citizen='" + Username + "'";
            PreparedStatement psd = conn.prepareStatement(sql);
            psd.setString(1, fnn.getText());
            psd.setString(2, lnn.getText());
            psd.setString(3, dma.getText());
            psd.setString(4, pm.getText());
            psd.setString(5, con.getText());
            psd.setString(6, cit.getText());
             psd.setString(7,passwordfield.getText());

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setHeaderText("updated");
            al.setContentText("Profile Updated successfully");
            al.showAndWait();
            psd.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



