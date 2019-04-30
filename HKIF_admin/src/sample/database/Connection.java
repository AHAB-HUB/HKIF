package sample.database;

import javafx.scene.control.Alert;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {

    private java.sql.Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Connection(){

    }

    public void connect(){
        try {
            String url = "jdbc:mysql://localhost:3306/hkif?user=root&password=root";
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            System.out.println("connected!");
        } catch (SQLException ex1) {
            System.out.println("Connection failed");
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
}
