package hkr;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {


    public DatabaseConnector(){
        Connection conn = null;
        Properties properties = new Properties(); //I use Properties to make things easer.
        properties.setProperty("user", "root");
        properties.setProperty("password", "YourPassword");
        properties.setProperty("useSSL", "false"); //Set useSSL to false to solve the problem.

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", properties);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
