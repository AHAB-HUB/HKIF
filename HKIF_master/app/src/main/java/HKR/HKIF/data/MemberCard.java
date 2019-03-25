package HKR.HKIF.data;


public class MemberCard {
    private String name;
    private String position;
    private String email;


    public MemberCard() {
    }

    public MemberCard(String name, String email, String position) {
        this.name = name;
        this.email = email;
        this.position = position;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}