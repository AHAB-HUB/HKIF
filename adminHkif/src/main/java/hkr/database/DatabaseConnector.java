package hkr.database;


import javafx.scene.control.Alert;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

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


    public boolean login(final String user, final String password){

        String execute = "SELECT person.email, person.password FROM person WHERE email =  \'" + user + "\' and password = \'" + password + "\'";

        try {
            resultSet = statement.executeQuery(execute);

            if (resultSet.next())
                return true;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User Not Found");
            alert.setContentText("Please try another name.");
            alert.showAndWait();
        }
        return false;
    }


    public void disconnect(){
        try {
            if (connection != null)
                connection.close();

            if (statement != null)
                statement.close();

            if (resultSet != null)
                resultSet.close();
        } catch (SQLException ex3) {
            System.out.println("Failed to disconnect");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
