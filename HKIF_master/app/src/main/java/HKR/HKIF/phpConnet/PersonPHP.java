package HKR.HKIF.phpConnet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonPHP {
    @Expose
    @SerializedName("person_id") private int person_id;

    @Expose
    @SerializedName("first_name") private String first_name;

    @Expose
    @SerializedName("last_name") private String last_name;

    @Expose
    @SerializedName("email") private String email;

    @Expose
    @SerializedName("password") private String password;

    @Expose
    @SerializedName("phone_number") private String phone_number;

    @Expose
    @SerializedName("position") private String position;

    @Expose
    @SerializedName("has_paid") private String has_paid;

    @Expose
    @SerializedName("profile_create_time") private String profile_create_time;

    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHas_paid() {
        return has_paid;
    }

    public void setHas_paid(String has_paid) {
        this.has_paid = has_paid;
    }

    public String getProfile_create_time() {
        return profile_create_time;
    }

    public void setProfile_create_time(String profile_create_time) {
        this.profile_create_time = profile_create_time;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
