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
            String url = "jdbc:mysql://den1.mysql4.gear.host/adminhkif?user=adminhkif&password=Bq44E?A_y45d&useSSL=false";
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

    public void updateMembers(String firstName, String lastName, String phoneNumber, String position, String hasPaid, String email){
        String updateQuery = "UPDATE person SET first_name = ?, last_name = ?, " +
                "phone_number = ?, position = ?, has_paid = ? WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, position);
            preparedStatement.setString(5, hasPaid);
            preparedStatement.setString(6, email);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateSport(String description, String available, String locationName, String sportName){
        String updateSportQuery = "UPDATE sport SET sport_description = ?, sport_available = ?, " +
                "location_location_id = ? WHERE sport_name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSportQuery)){
            preparedStatement.setString(1,description);
            preparedStatement.setString(2,available);
            if (locationName.equals("Campus")){
                preparedStatement.setInt(3, 1);
            }
            else {
                preparedStatement.setInt(3, 2);
            }

            preparedStatement.setString(4,sportName);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertNewValuesIntoSport(String sportName, String sportDescription, String sportAvailable, String locationName){
        String insertSportQuery = "INSERT INTO sport(sport_name, sport_description, sport_available, location_location_id)" +
                " VALUES(?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSportQuery)){
            preparedStatement.setString(1, sportName);
            preparedStatement.setString(2, sportDescription);
            preparedStatement.setString(3, sportAvailable);
            if (locationName.equals("Campus")){
                preparedStatement.setInt(4, 1);
            }
            else {
                preparedStatement.setInt(4,2);
            }

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteSportRow(String sportName){
        String deleteSportQuery = "DELETE FROM sport WHERE sport_name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSportQuery)){
            preparedStatement.setString(1, sportName);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

