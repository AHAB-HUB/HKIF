package HKR.HKIF.data;

public class SportLeaders {

    private String sportID;
    private String personID;

    public SportLeaders() {
    }

    public SportLeaders(String sportID, String personID) {
        this.sportID = sportID;
        this.personID = personID;
    }

    public String getSportID() {
        return sportID;
    }

    public void setSportID(String sportID) {
        this.sportID = sportID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
