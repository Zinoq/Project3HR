
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


public class Database {
    public String Result;
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

    public String execute(String Query, String Selector) {
            try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery(Query); //met een statement kunnen we sql code runnen, die meegegeven wordt

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getString(Selector));
                System.out.println(myResultset.getString(Selector));
                Result = myResultset.getString(Selector);
                return myResultset.getString(Selector); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result;
    }

    public String getSpecific(ArrayList<String> List, int index) { //dit is voor als je query meerdere resultaten terug geeft en je wilt een specifieke gebruiken
//        System.out.println(Results.get(index));
        return List.get(index);
    }

    public ArrayList<Double> getMarkerLat() {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultset = myStatement.executeQuery("SELECT X_Coord FROM standard.parkeerautomaten");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getDouble("X_Coord"));
                XLong.add(myResultset.getDouble("X_Coord")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
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
            ResultSet myResultset = myStatement.executeQuery("SELECT Y_Coord FROM standard.parkeerautomaten");

            // output
            while (myResultset.next()) { //loop door alle resultaten als er meerdere zijn
//                System.out.println(myResultset.getDouble("Y_Coord"));
                YLat.add(myResultset.getDouble("Y_Coord")); //Alle resultaten worden in een lijst gezet, waar we later mee kunnen werken
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


