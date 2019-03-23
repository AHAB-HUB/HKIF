package HKR.HKIF.utilities;


import android.app.Notification;
import android.view.View;
import android.content.Context;


import HKR.HKIF.R;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static HKR.HKIF.utilities.Channel.CHANNEL_1_ID;
import static HKR.HKIF.utilities.Channel.CHANNEL_2_ID;

public class Notifications {

    private NotificationManagerCompat notificationManager;
    private String title;
    private String message;
    private String longMessage;
    private Context context;

    public Notifications(String title, String message, Context context){
            this.title = title;
            this.message = message;
            this.context = context;

            notificationManager = NotificationManagerCompat.from(context);
    }



    public void sendOnChannel1(View v){

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_admin_settings)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

    public void sendOnChannel2(View v){

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_admin_settings)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2, notification);
    }

}



