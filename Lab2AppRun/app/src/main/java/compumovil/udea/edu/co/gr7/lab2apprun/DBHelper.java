package compumovil.udea.edu.co.gr7.lab2apprun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by santiago on 3/18/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_SCHEME_VERSION = 1;

    private static final String DB_NAME = "lab2apprun.sqlite";

    private static final String TABLE_USERS = "create table " + UsersManager.TABLE_NAME + " ("
            + UsersManager.CN_ID + " integer primary key autoincrement,"
            + UsersManager.CN_EMAIL + " text not null,"
            + UsersManager.CN_USERNAME + " text not null,"
            + UsersManager.CN_PASSWORD + " text not null,"
            + UsersManager.CN_IS_LOGGED + " integer);";

    public static final String TABLE_EVENTS = "create table " + EventsManager.TABLE_NAME + " ("
            + EventsManager.CN_ID + " integer primary key autoincrement,"
            + EventsManager.CN_DATE + " int not null,"
            + EventsManager.CN_NAME + " text not null,"
            + EventsManager.CN_PLACE + " text not null,"
            + EventsManager.CN_DISTANCE + " integer not null,"
            + EventsManager.CN_CONTACT_NAME + " text not null,"
            + EventsManager.CN_CONTACT_EMAIL + " text not null,"
            + EventsManager.CN_CONTACT_PHONE + " text not null);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USERS);
        db.execSQL(TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
