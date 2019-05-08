package hkr;


import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {

    private Connection connection;
    private Statement statement;

    public DatabaseConnector(){
        dbConnection();
    }

    private void dbConnection(){
        try {
            String url = "jdbc:mysql://localhost:3306/admin_hkif?user=root&password=root";
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            System.out.println("Connected");
        }catch (SQLException ex){
            System.out.println("Error in connection");
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
