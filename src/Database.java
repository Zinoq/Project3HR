
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public Statement myStatement =  null;
    public ArrayList<String> Results = new ArrayList<String>();
    public Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/parkeerautomaten1?" + "user=root&password=Kappa123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> execute(String Query, String Selector) {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery(Query); // hij kent myStatement niet, null pointer regel 29

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
