
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public ArrayList<String> Results = new ArrayList<>();
    public Connection connection;
    public ArrayList<Double> XLong = new ArrayList<>();
    public ArrayList<Double> YLat = new ArrayList<>();

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/standard?" + "user=root&password=Kappa123"); //connectie maken met database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> execute(String Query, String Selector) {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery(Query); //met een statement kunnen we sql code runnen, die meegegeven wordt

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getString(Selector));
                Results.add(myResultset.getString(Selector)); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(Results);
        return Results; // lijst wordt gereturned
    }

    public String getSpecific(int index) { //dit is voor als je query meerdere resultaten terug geeft en je wilt een specifieke gebruiken
//        System.out.println(Results.get(index));
        return Results.get(index);
    }

    public ArrayList<Double> getMarkerLong() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT X_Coord FROM standard.parkeerautomaten");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
                //                System.out.println(myResultset.getString(Selector));
                XLong.add(Double.valueOf(myResultset.getString("X_Coord"))); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return XLong;
    }

    public ArrayList<Double> getMarkerLat() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT Y_Coord FROM standard.parkeerautomaten");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
                //                System.out.println(myResultset.getString(Selector));
                XLong.add(java.lang.Double.valueOf(myResultset.getString("Y_Coord"))); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return YLat;
    }

    public static void main(String[] args) {
        Database Database = new Database(); // new database object en verbinding met de database maken
        Database.execute("SELECT * FROM markten", "Straatnaam"); // dit maakt een lijst met resultaten
        Database.getSpecific(2); // dit selecteerd alleen de 2e straat
    }
}


