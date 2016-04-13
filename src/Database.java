
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public ArrayList<String> Result = new ArrayList<>();
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
//
//    public String singleExecute(String Query) {
//        try {
//            Statement myStatement = connection.createStatement();
//            ResultSet myResultset = myStatement.executeQuery(Query); //met een statement kunnen we sql code runnen, die meegegeven wordt
//            while (myResultset.next()) {
//               myResultset.getString();
//
//            }
//        }
//         catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

    public ArrayList<String> execute(String Query, String Selector) {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery(Query); //met een statement kunnen we sql code runnen, die meegegeven wordt
            while (myResultset.next()) {
                Result.add(myResultset.getString(Selector));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result;
    }

    public ArrayList<Double> getMarkerLat() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getDouble("X_Coord"));
                XLong.add(myResultset.getDouble("Latitude")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return XLong;
    }

    public ArrayList<Double> getMarkerLong() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getDouble("Y_Coord"));
                YLat.add(myResultset.getDouble("Longitude")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return YLat;
    }

    public static void main(String[] args) {
        Database Database = new Database(); // new database object en verbinding met de database maken
//        Database.execute("SELECT Inventarisnr, Deelgem FROM standard.parkeerautomaten WHERE Inventarisnr=100.0", "Inventarisnr"); // dit maakt een lijst met resultaten
//        Database.getSpecific(Results, 2); // dit selecteerd alleen de 2e straat
        System.out.println(Database.getMarkerLong().get(2));
        System.out.println(Database.getMarkerLat().get(2));
    }
}


