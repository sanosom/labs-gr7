package organizate.compumovil.udea.edu.co.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import organizate.compumovil.udea.edu.co.managers.EventManager;

/**
 * Created by santiago on 4/12/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_SCHEME_VERSION = 1;

    private static final String DB_NAME = "organizate.sqlite";

    private static final String TABLE_ACTIVITY = "create table " + EventManager.TABLE_NAME + " ("
            + EventManager.CN_ID + " integer primary key autoincrement,"
            + EventManager.CN_NAME + " text not null,"
            + EventManager.CN_DATE + " int,"
            + EventManager.CN_PLACE + " text not null,"
            + EventManager.CN_REPEAT + " text,"
            + EventManager.CN_DURATION + " int not null);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ACTIVITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
