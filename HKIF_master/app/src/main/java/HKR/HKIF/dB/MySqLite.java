package HKR.HKIF.dB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import HKR.HKIF.data.NotificationData;
import HKR.HKIF.data.ScheduleItem;
import androidx.annotation.Nullable;

public class MySqLite extends SQLiteOpenHelper {


    public MySqLite(@Nullable Context context) {
        super(context, "hkif.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table schedule(cancel varchar(250), day varchar(250), 'from' varchar(250), going varchar(250)," +
                " id varchar(250), leader_name varchar(250), location varchar(250), location_date varchar(250)," +
                " sport_name varchar(250), 'to' varchar(250));");

        db.execSQL("create table notifications(title varchar(250), message varchar(250));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public List<NotificationData> getMessages() {
        List<NotificationData> messagesList = new ArrayList<>();

        String[] columns = {
                "title",
                "message"
        };

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("notifications", //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

         // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NotificationData message = new NotificationData(cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("message")));
                messagesList.add(message);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return messagesList;
    }

    public List<ScheduleItem> getSchedule() {
        List<ScheduleItem> messagesList = new ArrayList<>();

      //   array of columns to fetch
        String[] columns = {
                "cancel",
                "day",
                "from",
                "going",
                "id",
                "leader_name",
                "location",
                "location_date",
                "sport_name",
                "to"
        };

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("schedule", //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order



        return messagesList;
    }

    public void setMessages(ArrayList<NotificationData> list){

        clearMessages();

        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < list.size(); i++) {

            ContentValues values = new ContentValues();

            values.put("title", list.get(i).getTitle());
            values.put("message", list.get(i).getMessage());

            // Inserting Row
            db.insert("notifications", null, values);

        }
        db.close();
    }

    public void setSchedule(ArrayList<ScheduleItem> list){

        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < list.size(); i++) {

            ContentValues values = new ContentValues();

            values.put("canceled", list.get(i).getCanceled());
            values.put("day", list.get(i).getDay());
            values.put("from", list.get(i).getFrom());
            values.put("going", list.get(i).getGoing());
            values.put("id", list.get(i).getId());
            values.put("leader_name", list.get(i).getLeader_name());
            values.put("location", list.get(i).getLocation());
            values.put("location_date", list.get(i).getLocation_date());
            values.put("sport_name", list.get(i).getSport_name());
            values.put("to", list.get(i).getTo());

            // Inserting Row
            db.insert("notifications", null, values);
            db.close();
        }
    }

    public void clearMessages(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("notifications", null, null);
        db.close();

    }

    public void clearSchedule(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("schedule", null, null);

    }
}