package organizate.compumovil.udea.edu.co.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

import organizate.compumovil.udea.edu.co.db.DBHelper;

/**
 * Created by santiago on 6/1/16.
 */
public class CategoryManager {

    private DBHelper helper;

    private SQLiteDatabase db;

    public static final String CN_ID = "_id";
    public static final String CN_NAME = "name";
    public static final String CN_COLOR = "color";
    public static final String CN_IS_DEFAULT = "is_default";

    public static final String TABLE_NAME = "categories";

    public CategoryManager(Context context) {
        helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    private ContentValues generateContentValues(String name, String color, Boolean isDefault) {
        ContentValues values = new ContentValues();

        values.put(CN_NAME, name);
        values.put(CN_COLOR, color);
        values.put(CN_IS_DEFAULT, isDefault);

        return values;
    }

    public void create(String name, String color, Boolean isDefault) {
        ContentValues values = generateContentValues(name, color, isDefault);

        db.insert(TABLE_NAME, null, values);
    }

    public Cursor read() {
        String[] columns = new String[] {
                CN_ID,
                CN_NAME,
                CN_COLOR,
                CN_IS_DEFAULT
        };

        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    public void update(String id, String name, String color, Boolean isDefault) {
        ContentValues values = generateContentValues(name, color, isDefault);

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
