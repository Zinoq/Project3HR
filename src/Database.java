
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public static void main(String[] args) {
        try {
            // connectie met database maken
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/parkeerautomaten1?"+"user=root&password=Kappa123");

            //Statement maken
            Statement myStatement = connection.createStatement();

            // Alles binnen de databases selecteren
            String sqlCommand = "SELECT * FROM markten";
            ResultSet myResultset = myStatement.executeQuery(sqlCommand);

            //Process output
            while(myResultset.next()) { //loop door alle resultaten
                System.out.println(myResultset.getString("straatnaam"));
            }
        }
        catch (Exception a) {
            a.printStackTrace();
        }

    }
}

//TODO make it so that we can execute sql commands from outside of the main here
