package stock;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GraphController implements Initializable{

    @FXML
    private BarChart<String,Double> barchart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }




    public void loadData(javafx.event.ActionEvent event) {
        CompanyData cos=new CompanyData();
        String sql="SELECT Year,Amount FROM `compay_graph` ORDER BY Year asc";
        XYChart.Series<String,Double> series=new XYChart.Series<>();
        try {
            Connection con=Database_connection.connect();

            ResultSet rs=con.createStatement().executeQuery(sql);
            while(rs.next()){
               // series.setName(rs.getString(3));
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }

            barchart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
