package HKR.HKIF.utilities;


import android.content.Context;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.provider.CalendarContract;


public class CalendarNewEvent {


    public CalendarNewEvent(Context context, String sportName, String location, String start, String end, String date) {
        String[] startTime = start.split(":");
        String[] endTime = end.split(":");
        String[] getDate = date.split("/");


        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, sportName);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "HKIF events:\nThis is a reminder for sport: " + sportName);

        // Setting dates
        GregorianCalendar begin = new GregorianCalendar(Integer.valueOf(getDate[2]), 3, Integer.valueOf(getDate[0]), Integer.valueOf(startTime[0]), Integer.valueOf(startTime[1]));
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTimeInMillis());

        GregorianCalendar finish = new GregorianCalendar(Integer.valueOf(getDate[2]), 3, Integer.valueOf(getDate[0]), Integer.valueOf(endTime[0]), Integer.valueOf(endTime[1]));
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, finish.getTimeInMillis());

        // make it a full day event
//        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // make it a recurring Event
        // intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

        // Making it private and shown as busy
        intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        context.startActivity(intent);
    }

}
