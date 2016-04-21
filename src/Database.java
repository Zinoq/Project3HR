import java.sql.*;
import java.util.ArrayList;

public class Database {
    private ArrayList<String> Result = new ArrayList<>();
    private Connection connection;
    private ArrayList<Double> XMLong = new ArrayList<>();
    private ArrayList<Double> YMLat = new ArrayList<>();

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/standard?" + "user=root&password=Kappa123"); //make connection with database hosted at localhost
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
                Result.add(myResultset.getString(Selector)); // add String of resultset to our result list
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
    } // always returns the Lat at the markers table

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
    } // always returns the Lon at the markers able


}