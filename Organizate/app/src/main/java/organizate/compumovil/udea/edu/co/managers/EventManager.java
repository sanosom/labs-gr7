package organizate.compumovil.udea.edu.co.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

import organizate.compumovil.udea.edu.co.db.DBHelper;

/**
 * Created by santiago on 4/12/16.
 */
public class EventManager {

    private DBHelper helper;

    private SQLiteDatabase db;

    public static final String CN_ID = "_id";
    public static final String CN_DATE = "date";
    public static final String CN_NAME = "name";
    public static final String CN_PLACE = "place";
    public static final String CN_REPEAT = "repeat";
    public static final String CN_DURATION = "duration";

    public static final String TABLE_NAME = "events";

    public EventManager(Context context) {
        helper = new DBHelper(context);

        db = helper.getWritableDatabase();

        // Uncomment this if you want to create hardcoded events
        /*
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            create(new Date(format.parse("2016-06-01").getTime()), "Actividad 1", "", "", 60);
            create(new Date(0), "Actividad 2", "", "", 60);
            create(new Date(format.parse("2016-06-02").getTime()), "Actividad 3", "", "", 60);
            create(new Date(format.parse("2015-06-01").getTime()), "Actividad 4", "", "", 60);
            create(new Date(-1), "Actividad 5", "", "", 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
    }

    private ContentValues generateContentValues(Date date, String name, String place,
                                                String repeat, Integer duration) {

        ContentValues values = new ContentValues();

        values.put(CN_DATE, date.getTime() / 1000);
        values.put(CN_NAME, name);
        values.put(CN_PLACE, place);
        values.put(CN_REPEAT, repeat);
        values.put(CN_DURATION, duration);

        return values;
    }

    public void create(Date date, String name, String place, String repeat, Integer duration) {
        ContentValues values = generateContentValues(date, name, place, repeat, duration);

        db.insert(TABLE_NAME, null, values);
    }

    public Cursor read() {
        String[] columns = new String[] {
                CN_ID,
                CN_DATE,
                CN_NAME,
                CN_PLACE,
                CN_REPEAT,
                CN_DURATION
        };

        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    public void update(Date date, String name, String place, String repeat, Integer duration) {

        ContentValues values = generateContentValues(date, name, place, repeat, duration);

        String[] query = new String[] {
                name
        };

        db.update(TABLE_NAME, values, CN_NAME + "=?", query);
    }

    public void delete(String name) {
        String[] query = new String[] {
                name
        };

        db.delete(TABLE_NAME, CN_NAME + "=?", query);
    }

}
