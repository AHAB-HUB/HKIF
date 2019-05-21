package hkr.database;


import hkr.data.Schedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public boolean insertNewValuesIntoSport(String sportName, String sportDescription, String sportAvailable, String locationName){
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
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
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

    public int getSportId(String sportName){
        String sportIdQuery = "SELECT sport_id FROM sport WHERE sport_name = " + "\'" + sportName + "\'";
        int sportID = 0;

        try {
            resultSet = statement.executeQuery(sportIdQuery);

            while (resultSet.next()) {
                sportID = resultSet.getInt("sport_id");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            sportID = 0;
        }

        return sportID;
    }

    public ObservableList<String> getAllSportNames(){
        ObservableList<String> nameList = FXCollections.observableArrayList();
        String sportNameQuery = "SELECT sport_name FROM sport";

        try {
            resultSet = statement.executeQuery(sportNameQuery);

            while (resultSet.next()){
                nameList.add(resultSet.getString("sport_name"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            nameList.add(null);
        }

        return nameList;

    }

    public ObservableList<String> getAlleventNames(){
        ObservableList<String> nameList = FXCollections.observableArrayList();
        String eventNameQuery = "SELECT event_name FROM event";

        try {
            resultSet = statement.executeQuery(eventNameQuery);

            while (resultSet.next()){
                nameList.add(resultSet.getString("event_name"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            nameList.add(null);
        }

        return nameList;

    }

    public void insertValueIntoEvent(String eventName, String eventDescription, String eventLocation, Date eventDate,
                                     Time eventStart, Time eventEnd){

        String eventQuery = "INSERT INTO event(event_name, event_description, event_location, event_date, " +
                "event_start, event_end) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(eventQuery)){
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, eventDescription);
            preparedStatement.setString(3, eventLocation);
            preparedStatement.setDate(4, eventDate);
            preparedStatement.setTime(5, eventStart);
            preparedStatement.setTime(6, eventEnd);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertValueIntoSportHasEvent(int eventID, int sportId){
        String sportHasEventQuery = "INSERT INTO sport_has_event (sport_sport_id, event_event_id) VALUES(?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sportHasEventQuery)){
            preparedStatement.setInt(1, sportId);
            preparedStatement.setInt(2, eventID);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int getEventId(String eventName){
        String eventIdQuery = "SELECT event_id FROM event WHERE event_name = " + "\'" + eventName + "\'";
        int eventID = 0;

        try {
            resultSet = statement.executeQuery(eventIdQuery);
            while (resultSet.next()){
                eventID = resultSet.getInt("event_id");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());

            eventID = 0;
        }

        return eventID;
    }

    public ObservableList<Schedule> getScheduleInformation(String day, String date){

        ObservableList<Schedule> scheduleData = FXCollections.observableArrayList();

        String scheduleQuery = "SELECT sport.sport_name, schedule.schedule_day, schedule.schedule_date, " +
                "schedule_has_sport.session_start, schedule_has_sport.session_end " +
                " FROM sport, schedule, schedule_has_sport " +
                " WHERE " +
                " schedule_has_sport.schedule_schedule_id = schedule.schedule_id " +
                " AND schedule_has_sport.sport_sport_id = sport.sport_id " +
                "AND schedule.schedule_day = " + " \'" + day + "\'" +
                " AND schedule.schedule_date = " + " \'" + date + "\'" +
                " ORDER BY schedule_has_sport.session_start";

        try {
             resultSet =  connection.createStatement().executeQuery(scheduleQuery);

            while (resultSet.next()){
                scheduleData.add(new Schedule(resultSet.getString("sport_name"),
                        resultSet.getString("schedule_day"), resultSet.getString("schedule_date"),
                        resultSet.getString("session_start"),
                        resultSet.getString("session_end")));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return scheduleData;
    }

    public ObservableList<String> getDatesOfDay(String day){
        ObservableList<String> dates = FXCollections.observableArrayList();

        String queryDates = "SELECT schedule.schedule_date FROM schedule WHERE schedule_day = " + "\'" +day + "\'";

        try {
            resultSet = connection.createStatement().executeQuery(queryDates);

            while (resultSet.next()){

                dates.add(resultSet.getString("schedule_date"));

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return dates;
    }

    public int getScheduleLastID(){
        int lastID = 0;

        String query = "SELECT MAX(schedule.schedule_id) FROM schedule";

        try {
            resultSet = connection.createStatement().executeQuery(query);

            while (resultSet.next()){
                lastID = resultSet.getInt("Max(schedule.schedule_id)");
            }
        }catch (SQLException e){
            lastID = 0;
            e.printStackTrace();
        }

        return lastID;
    }

    public void callUpdateScheduleProc(int dayNumber){
        try {
            CallableStatement callableStatement = connection.prepareCall("{call updateScheduleTable(?)}");

            callableStatement.setInt(1,dayNumber);

            callableStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void callUpdateScheduleHasSportPro(int dayNumber){
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall("{call updateScheduleHasSport(?)}");

            callableStatement.setInt(1, dayNumber);
            callableStatement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}


