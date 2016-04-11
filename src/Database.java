
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public ArrayList<String> Results = new ArrayList<String>();
    public Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/standard?" + "user=root&password=Kappa123"); //connectie maken met database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> execute(String Query, String Selector) {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery(Query); //met een statement kunnen we sql code runnen, die meegegeven wordt

            // output
            while(myResultset.next()) { //loop door alle resultaten als er meerdere zijn
                System.out.println(myResultset.getString(Selector));
                Results.add(myResultset.getString(Selector)); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(Results);
        return Results; // lijst wordt gereturned

    }

    public static void main(String[] args) {
        Database Database = new Database();
        Database.execute("SELECT * FROM markten", "Straatnaam");
    }
}


