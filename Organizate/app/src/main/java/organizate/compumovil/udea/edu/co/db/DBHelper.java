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
            + EventManager.CN_ID + " integer primary key autoincrement, "
            + EventManager.CN_NAME + " text not null, "
            + EventManager.CN_DATE + " integer, "
            + EventManager.CN_PLACE + " text not null, "
            + EventManager.CN_REPEAT + " text, "
            + EventManager.CN_CATEGORY + " int not null, "
            + EventManager.CN_DURATION + " int not null);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ACTIVITY);

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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
