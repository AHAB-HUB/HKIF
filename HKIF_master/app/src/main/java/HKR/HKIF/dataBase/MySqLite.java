package HKR.HKIF.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import HKR.HKIF.data.NotificationData;

public class MySqLite extends SQLiteOpenHelper {


    public MySqLite(@Nullable Context context) {
        super(context, "hkif.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    public void setMessages(ArrayList<NotificationData> list) {

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


    public void clearMessages() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("notifications", null, null);
        db.close();
    }
}