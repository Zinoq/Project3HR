import java.sql.*; //JDBC for databases
import java.math.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    String url;
    String username;
    String password;
    Connection db;

    public Database() {
        String url = "jdbc:postgresql://localhost:5432/public";
        String username = "postgres";
        String password = "Kappa123";
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Succes");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection db=  DriverManager.getConnection(url, username, password);
            System.out.println("Succes");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}