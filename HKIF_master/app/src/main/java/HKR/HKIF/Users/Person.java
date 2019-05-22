package HKR.HKIF.Users;

public class Person {

    public enum POSITION {
        ADMIN,
        TEAM_LEADER,
        MEMBER;
    }

    private String personID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String position;
    private boolean hasPaid;
    private String profileCreated;
    private String fullName;
    //private String hasPaid;

    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";


    public Person() {

    }

    public Person(String personID, String firstName, String lastName, String email, String password,
                  String phoneNumber, String position, boolean hasPaid, String profileCreated) {

        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.hasPaid = hasPaid;
        this.profileCreated = profileCreated;
        this.position = position;
        //this.hasPaid = hasPaid;


    }


    public String getPosition() {
        return position;
    }


    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public String getProfileCreated() {
        return profileCreated;
    }

    public void setProfileCreated(String profileCreated) {
        this.profileCreated = profileCreated;
    }

    /*public String getHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(String hasPaid) {
        this.hasPaid = hasPaid;
    }*/

    @Override
    public String toString() {
        return "Person{" +
                "personID='" + personID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", hasPaid=" + hasPaid +
                ", profileCreated='" + profileCreated + '\'' +
                '}';
    }
}
