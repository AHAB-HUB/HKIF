package HKR.HKIF.localDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HkifLocalDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "HistoryDatabase.db";

    private final String TABLE_ATTENDANCE_HISTORY = "attendance_history";

    private final String COLUMN_ATTENDANCE_HISTORY_ID = "id";
    private final String COLUMN_ATTENDANCE_HISTORY_USER_ID = "user_id";
    private final String COLUMN_ATTENDANCE_HISTORY_LOCATION = "location";
    private final String COLUMN_ATTENDANCE_HISTORY_DATE = "session_date";
    private final String COLUMN_ATTENDANCE_HISTORY_SPORT_NAME = "sport_name";


    private String DROP_ATTENDANCE_HISTORY_TABLE = "DROP TABLE IF EXISTS " + TABLE_ATTENDANCE_HISTORY;

    private String CREATE_ATTENDANCE_HISTORY_TABLE = "CREATE TABLE " + TABLE_ATTENDANCE_HISTORY + "("
            + COLUMN_ATTENDANCE_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ATTENDANCE_HISTORY_USER_ID + " TEXT,"
            + COLUMN_ATTENDANCE_HISTORY_LOCATION + " TEXT," +
            COLUMN_ATTENDANCE_HISTORY_DATE + " TEXT, " + COLUMN_ATTENDANCE_HISTORY_SPORT_NAME + " TEXT"
            + ")";


    public HkifLocalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_ATTENDANCE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_ATTENDANCE_HISTORY_TABLE);

        // Create tables again
        onCreate(db);
    }


    public void addAttendance(AttendanceHistory attendanceHistory) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ATTENDANCE_HISTORY_USER_ID, attendanceHistory.getUser_id());
        contentValues.put(COLUMN_ATTENDANCE_HISTORY_LOCATION, attendanceHistory.getLocation());
        contentValues.put(COLUMN_ATTENDANCE_HISTORY_DATE, attendanceHistory.getSession_date());
        contentValues.put(COLUMN_ATTENDANCE_HISTORY_SPORT_NAME, attendanceHistory.getSport_name());

        database.insert(TABLE_ATTENDANCE_HISTORY, null, contentValues);
        database.close();
    }


    public List<AttendanceHistory> getUserAtendance() {
        String[] columns =
                {
                        COLUMN_ATTENDANCE_HISTORY_ID,
                        COLUMN_ATTENDANCE_HISTORY_USER_ID,
                        COLUMN_ATTENDANCE_HISTORY_LOCATION,
                        COLUMN_ATTENDANCE_HISTORY_DATE,
                        COLUMN_ATTENDANCE_HISTORY_SPORT_NAME
                };


        List<AttendanceHistory> attendanceHistoryList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();


        //String selectionWhere  = "\"" + COLUMN_ATTENDANCE_HISTORY_USER_ID + " =?" + "\"";
        // String [] args = {userID};

        Cursor cursor = database.query(TABLE_ATTENDANCE_HISTORY,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                AttendanceHistory attendanceHistory = new AttendanceHistory();
                attendanceHistory.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ATTENDANCE_HISTORY_ID))));
                attendanceHistory.setUser_id(cursor.getString(cursor.getColumnIndex(COLUMN_ATTENDANCE_HISTORY_USER_ID)));
                attendanceHistory.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_ATTENDANCE_HISTORY_LOCATION)));
                attendanceHistory.setSession_date(cursor.getString(cursor.getColumnIndex(COLUMN_ATTENDANCE_HISTORY_DATE)));
                attendanceHistory.setSport_name(cursor.getString(cursor.getColumnIndex(COLUMN_ATTENDANCE_HISTORY_SPORT_NAME)));
                attendanceHistoryList.add(attendanceHistory);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return attendanceHistoryList;
    }

    public void deleteUserAttendanceHistory(String sportName) {
        String where = COLUMN_ATTENDANCE_HISTORY_SPORT_NAME + " =?";

        SQLiteDatabase database = this.getReadableDatabase();

        database.delete(TABLE_ATTENDANCE_HISTORY, where, new String[]{sportName});


    }

}
