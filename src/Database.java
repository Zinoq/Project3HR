
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public Statement myStatement;
    public ArrayList<String> Results;

    public Database() {
        // connectie met database maken
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/parkeerautomaten1?" + "user=root&password=Kappa123");
            //Statement maken
            Statement myStatement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> execute(String Query, String Selector) {
        try {
//            ResultSet myResultset =
            ResultSet myResultset = myStatement.executeQuery(Query);

            //Process output
            while(myResultset.next()) { //loop door alle resultaten
                System.out.println(myResultset.getString(Selector));
                Results.add(myResultset.getString(Selector)); //Alle resultaten worden in een lijst gezet
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Results;
    }

    public static void main(String[] args) {
        Database Database = new Database();
        Database.execute("SELECT * FROM markten", "Straatnaam");
    }
}

//TODO fix NullPointer error
