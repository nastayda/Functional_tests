package Connection;

//import com.mysql.jdbc.Statement;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionToDB {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://vm-107-stu-dev.ursip.ru:3306";
    private static final String user = "root";
    private static final String password = "qwerty123";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query


            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of rows : " + count);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDB.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
    public static void execQuery(String query) throws SQLException {
        rs = stmt.executeQuery(query);
    }
}

