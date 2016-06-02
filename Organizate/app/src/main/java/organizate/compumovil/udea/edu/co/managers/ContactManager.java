package organizate.compumovil.udea.edu.co.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import organizate.compumovil.udea.edu.co.db.DBHelper;

/**
 * Created by santiago on 6/1/16.
 */
public class ContactManager {

    private DBHelper helper;

    private SQLiteDatabase db;

    public static final String CN_ID = "_id";
    public static final String CN_UID = "uid";
    public static final String CN_NAME = "name";
    public static final String CN_EMAIL = "email";

    public static final String TABLE_NAME = "contacts";

    public ContactManager(Context context) {
        helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    private ContentValues generateContentValues(String uid, String name, String email) {
        ContentValues values = new ContentValues();

        values.put(CN_UID, uid);
        values.put(CN_NAME, name);
        values.put(CN_EMAIL, email);

        return values;
    }

    public void create(String uid, String name, String email) {
        ContentValues values = generateContentValues(uid, name, email);

        db.insert(TABLE_NAME, null, values);
    }

    public Cursor read() {
        String[] columns = new String[] {
                CN_ID,
                CN_UID,
                CN_NAME,
                CN_EMAIL
        };

        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    public void update(String id, String uid, String name, String email) {
        ContentValues values = generateContentValues(uid, name, email);

        String[] query = new String[] {
                id
        };

        db.update(TABLE_NAME, values, CN_ID + "=?", query);
    }

    public void delete(String id) {
        String[] query = new String[] {
                id
        };

        db.delete(TABLE_NAME, CN_ID + "=?", query);
    }

}
