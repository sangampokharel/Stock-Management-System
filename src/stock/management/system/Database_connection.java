
package stock.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database_connection {
    Connection conn;
  public Connection connect() throws SQLException{
      try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stock?useSSL=false","root","");
          System.out.println("Connection established");
          return conn;
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(Database_connection.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return null;
      
  }  

}
