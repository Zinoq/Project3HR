
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public ArrayList<String> Result = new ArrayList<>();
    public Connection connection;
    public ArrayList<Double> XMLong = new ArrayList<>();
    public ArrayList<Double> YMLat = new ArrayList<>();
    public ArrayList<Double> XPLong = new ArrayList<>();
    public ArrayList<Double> YPLat = new ArrayList<>();

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
            while (myResultset.next()) { // eigenlijk een soort for loop door de resultaten
                Result.clear();
                Result.add(myResultset.getString(Selector));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result;
    }

    public ArrayList<String> newExecute(String Query, String Selector) {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery(Query); //met een statement kunnen we sql code runnen, die meegegeven wordt
            while (myResultset.next()) { // eigenlijk een soort for loop door de resultaten
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
                XMLong.add(myResultset.getDouble("Latitude")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return XMLong;
    }

    public ArrayList<Double> getMarkerLong() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getDouble("Y_Coord"));
                YMLat.add(myResultset.getDouble("Longitude")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return YMLat;
    }

    public ArrayList<Double> getParkeerLat() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT X_Coord FROM standard.parkeerautomaten WHERE X_Coord IS NOT NULL");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getDouble("X_Coord"));
                XPLong.add(myResultset.getDouble("X_coord")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return XPLong;
    }

    public ArrayList<Double> getParkeerLong() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT Y_Coord FROM standard.parkeerautomaten WHERE Y_Coord IS NOT NULL");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getDouble("Y_Coord"));
                YPLat.add(myResultset.getDouble("Y_Coord")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        System.out.println(Results);
        return YPLat;
    }

}

//    public static void main(String[] args) {
//        Database Database = new Database(); // new database object en verbinding met de database maken
//
//        String Query_ = "SELECT SUM(year_2006), SUM(year_2007), SUM(year_2008), SUM(year_2009), SUM(year_2011) FROM slachtofferschap_fietsendiefstal;";
//        System.out.println(Double.parseDouble(Database.execute(Query_, "SUM(year_2006)").get(0)));
//    }
//}


