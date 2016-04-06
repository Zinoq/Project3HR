import java.sql.*; //JDBC for databases
import java.math.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    String url = "jdbc:postgresql://localhost:5432/public";
    String username = "postgres";
    String password = "Kappa123";
    Class.forName("org.postgresql.Driver");

    Connection db = DriverManager.getConnection(url, username, password);

        }