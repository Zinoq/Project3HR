import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>See
 * <a href="http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates#Java">
 * http://JanMatuschek.de/LatitudeLongitudeBoundingCoordinates#Java</a>
 * for the GeoLocation class referenced from this code.</p>
 *
 * @author Jan Philip Matuschek
 * @version 26 May 2010
 */
public class GeoLocationDemo {
    public ArrayList<String> Results = new ArrayList<>();

    /**
     * @param radius radius of the sphere.
     * @param location center of the query circle.
     * @param distance radius of the query circle.
     * @param connection an SQL connection.
     * @return places within the specified distance from location.
     */
    public static ResultSet findPlacesWithinDistance(double radius, GeoLocation location, double distance, Connection connection) throws java.sql.SQLException {

        GeoLocation[] boundingCoordinates =
                location.boundingCoordinates(distance, radius);
        boolean meridian180WithinDistance =
                boundingCoordinates[0].getLongitudeInRadians() >
                        boundingCoordinates[1].getLongitudeInRadians();

        java.sql.PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM parkeerautomaten WHERE (X_Coord >= ? AND X_Coord <= ?) AND (Y_Coord >= ? " + // X_Coord = Lat Y_Coord = Lon
                        (meridian180WithinDistance ? "OR" : "AND") + " Y_Coord <= ?) AND " +
                        "acos(sin(?) * sin(X_Coord) + cos(?) * cos(X_Coord) * cos(Y_Coord - ?)) <= ?");
        statement.setDouble(1, boundingCoordinates[0].getLatitudeInRadians());
        statement.setDouble(2, boundingCoordinates[1].getLatitudeInRadians());
        statement.setDouble(3, boundingCoordinates[0].getLongitudeInRadians());
        statement.setDouble(4, boundingCoordinates[1].getLongitudeInRadians());
        statement.setDouble(5, location.getLatitudeInRadians());
        statement.setDouble(6, location.getLatitudeInRadians());
        statement.setDouble(7, location.getLongitudeInRadians());
        statement.setDouble(8, distance / radius);
        return statement.executeQuery();

    }

    public static void main(String[] args) throws SQLException {

        double earthRadius = 6371.01;
        GeoLocation myLocation = GeoLocation.fromRadians(51.9225, 4.4845); // lat, long
        double distance = 1000;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/standard?" + "user=root&password=Kappa123");

        ResultSet resultSet = findPlacesWithinDistance(earthRadius, myLocation, distance, connection);


    }

}