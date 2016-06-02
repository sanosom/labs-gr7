package compumovil.udea.edu.co.gr7.lab2apprun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by santiago on 3/18/16.
 */
public class UsersManager {

    private DBHelper helper;

    private SQLiteDatabase db;

    public static final String CN_ID = "_id";
    public static final String CN_EMAIL = "email";
    public static final String CN_USERNAME = "username";
    public static final String CN_PASSWORD = "password";
    public static final String CN_IS_LOGGED = "is_logged";

    public static final String TABLE_NAME = "users";

    public UsersManager(Context context) {
        helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    private ContentValues generateContentValues(String email, String username, String password,
                                                Boolean is_logged) {

        ContentValues values = new ContentValues();

        values.put(CN_EMAIL, email);
        values.put(CN_USERNAME, username);
        values.put(CN_PASSWORD, password);
        values.put(CN_IS_LOGGED, is_logged);

        return values;
    }

    public void create(String email, String username, String password, Boolean is_logged) {
        db.insert(TABLE_NAME, null, generateContentValues(email, username, password, is_logged));
    }

    public Cursor read() {
        String[] columns = new String[] {
            CN_ID,
            CN_EMAIL,
            CN_USERNAME,
            CN_IS_LOGGED
        };

        String query = CN_IS_LOGGED + "=?";

        String[] query_data = new String[] {
            "1"
        };

        return db.query(TABLE_NAME, columns, query, query_data, null, null, null);
    }

    public void update(String email, String username, String password, Boolean is_logged) {
        ContentValues values = generateContentValues(email, username, password, is_logged);

        String query = CN_USERNAME + "=?";

        String[] query_data = new String[] {
            username
        };

        db.update(TABLE_NAME, values, query, query_data);
    }

    public void delete(String username) {
        String query = CN_USERNAME + "=?";

        String[] query_data = new String[] {
            username
        };

        db.delete(TABLE_NAME, query, query_data);
    }

    public boolean is_logged() {
        String[] columns = new String[] {
            CN_ID,
            CN_USERNAME,
            CN_IS_LOGGED
        };

        String query = CN_IS_LOGGED + "=?";

        String[] query_data = new String[] {
            "1"
        };

        Cursor cursor = db.query(TABLE_NAME, columns, query, query_data, null, null, null);

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean login(String username, String password) {
        String[] columns = new String[] {
            CN_ID,
            CN_EMAIL,
            CN_USERNAME,
            CN_PASSWORD,
            CN_IS_LOGGED
        };

        String query = CN_USERNAME + "=? AND " + CN_PASSWORD + "=?";

        String[] query_data = new String[] {
            username,
            password
        };

        Cursor cursor = db.query(TABLE_NAME, columns, query, query_data, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            update(cursor.getString(1), cursor.getString(2), cursor.getString(3), true);

            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        String[] columns = new String[] {
            CN_ID,
            CN_EMAIL,
            CN_USERNAME,
            CN_PASSWORD,
            CN_IS_LOGGED
        };

        String query = CN_IS_LOGGED + "=?";

        String[] query_data = new String[] {
            "1"
        };

        Cursor cursor = db.query(TABLE_NAME, columns, query, query_data, null, null, null);



        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            update(cursor.getString(1), cursor.getString(2), cursor.getString(3), false);
        }
    }

}
