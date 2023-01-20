package opulence.dbConnect;

import java.sql.*;

public class dbConnect {
    public static Connection connect2db() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/opulence",
                "root",
                "Anurag12");
    }
}
