package HKR.HKIF.data;

public class NotificationData {

    private String title;
    private String message;


    public NotificationData() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationData(String title, String message) {

        this.title = title;
        this.message = message;


    }


    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

}
