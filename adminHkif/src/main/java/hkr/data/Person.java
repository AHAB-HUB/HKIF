package hkr.data;

import hkr.database.DatabaseConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Person {
    private StringProperty firstName, lastName, email, phoneNumber, position, hasPaid;

    private Button updateBtn;

    public Person(String firstName, String lastName, String email, String phoneNumber,
                  String position, String hasPaid, Button updateBtn) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.position = new SimpleStringProperty(position);
        this.hasPaid = new SimpleStringProperty(hasPaid);
        this.updateBtn = updateBtn;

        this.updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new DatabaseConnector().updateMembers(getFirstName(), getLastName(),
                        getPhoneNumber(), getPosition(), getHasPaid(), getEmail());
            }
        });
    }

    public Person() {
    }

    public Button getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(Button updateBtn) {
        this.updateBtn = updateBtn;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public StringProperty positionProperty() {
        return position;
    }

    public String getHasPaid() {
        return hasPaid.get();
    }

    public void setHasPaid(String hasPaid) {
        this.hasPaid.set(hasPaid);
    }

    public StringProperty hasPaidProperty() {
        return hasPaid;
    }
}
