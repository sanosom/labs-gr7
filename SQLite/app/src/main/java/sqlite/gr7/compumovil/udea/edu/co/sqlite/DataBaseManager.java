package sqlite.gr7.compumovil.udea.edu.co.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by santiago on 3/7/16.
 */
public class DataBaseManager {

    public static final String TABLE_NAME = "contacts";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "name";
    public static final String CN_PHONE = "phone";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_PHONE + " text );";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    private ContentValues generateContentValues(String name, String phone) {
        ContentValues values = new ContentValues();

        values.put(CN_NAME, name);
        values.put(CN_PHONE, phone);

        return values;
    }

    public void create(String name, String phone) {
        db.insert(TABLE_NAME, null, generateContentValues(name, phone));
    }

    public void create_string(String name, String phone) {
        db.execSQL("insert into " + TABLE_NAME + " values (null, '" + name + "', '" + phone + "');");
    }

    public void delete(String name) {
        db.delete(TABLE_NAME, CN_NAME + "=?", new String[]{name});
    }

    public void delete(String name1, String name2) {
        db.delete(TABLE_NAME, CN_NAME + " IN (?, ?)", new String[]{name1, name2});
    }

    public void update_phone(String name, String phone) {
        db.update(TABLE_NAME, generateContentValues(name, phone), CN_NAME + "=?", new String[]{name});
    }

    public Cursor load_contacts() {
        String[] columns = new String[]{CN_ID, CN_NAME, CN_PHONE};

        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    public Cursor find_contact(String name) {
        String[] columns = new String[]{CN_ID, CN_NAME, CN_PHONE};

        return db.query(TABLE_NAME, columns, CN_NAME + "=?", new String[]{name}, null, null, null);
    }

}
