package compumovil.udea.edu.co.gr7.lab2apprun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by santiago on 3/18/16.
 */
public class EventsManager {

    private DBHelper helper;

    private SQLiteDatabase db;

    public static final String CN_ID = "_id";
    public static final String CN_DATE = "date";
    public static final String CN_NAME = "name";
    public static final String CN_PLACE = "place";
    public static final String CN_DISTANCE = "distance";
    public static final String CN_CONTACT_NAME = "contact_name";
    public static final String CN_CONTACT_EMAIL = "contact_email";
    public static final String CN_CONTACT_PHONE = "contact_phone";

    public static final String TABLE_NAME = "events";

    public EventsManager(Context context) {
        helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    private ContentValues generateContentValues(Date date, String name, String place,
                                                Integer distance, String contact_name,
                                                String contact_email, String contact_phone) {

        ContentValues values = new ContentValues();

        values.put(CN_DATE, date.getTime());
        values.put(CN_NAME, name);
        values.put(CN_PLACE, place);
        values.put(CN_DISTANCE, distance);
        values.put(CN_CONTACT_NAME, contact_name);
        values.put(CN_CONTACT_EMAIL, contact_email);
        values.put(CN_CONTACT_PHONE, contact_phone);

        return values;
    }

    public void create(Date date, String name, String place, Integer distance, String contact_name,
                       String contact_email, String contact_phone) {

        ContentValues values = generateContentValues(date, name, place, distance, contact_name,
                contact_email, contact_phone);

        db.insert(TABLE_NAME, null, values);
    }

    public Cursor read() {
        String[] columns = new String[] {
                CN_DATE,
                CN_NAME,
                CN_PLACE,
                CN_DISTANCE,
                CN_CONTACT_NAME,
                CN_CONTACT_EMAIL,
                CN_CONTACT_PHONE
        };

        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    public void update(Date date, String name, String place, Integer distance, String contact_name,
                       String contact_email, String contact_phone) {

        ContentValues values = generateContentValues(date, name, place, distance, contact_name,
                contact_email, contact_phone);

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
