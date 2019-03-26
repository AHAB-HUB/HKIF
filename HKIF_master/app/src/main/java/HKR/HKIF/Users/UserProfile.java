package HKR.HKIF.Users;

public class UserProfile {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean userHasPaid;

    public UserProfile() {

    }

    public UserProfile(String firstName, String lastName, String phoneNumber, boolean userHasPaid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userHasPaid = userHasPaid;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUserHasPaid() {
        return userHasPaid;
    }

    public void setUserHasPaid(boolean userHasPaid) {
        this.userHasPaid = userHasPaid;
    }
}
