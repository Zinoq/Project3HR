
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {

    public static void main(String[] args) {

        try {
            // connectie met database maken
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/parkeerautomaten1?"+"user=root&password=Kappa123");
        }
        catch (Exception a) {
            a.printStackTrace();
        }

    }
}

