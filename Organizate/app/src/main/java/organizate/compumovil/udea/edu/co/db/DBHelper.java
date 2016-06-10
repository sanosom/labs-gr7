package organizate.compumovil.udea.edu.co.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.managers.CategoryManager;
import organizate.compumovil.udea.edu.co.managers.ContactManager;
import organizate.compumovil.udea.edu.co.managers.EventManager;

/**
 * Created by santiago on 4/12/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_SCHEME_VERSION = 1;

    private static final String DB_NAME = "organizate.sqlite";

    private static final String TABLE_CONTACT = "create table " + ContactManager.TABLE_NAME + " ("
            + ContactManager.CN_ID + " integer primary key autoincrement, "
            + ContactManager.CN_UID + " text not null, "
            + ContactManager.CN_NAME + " text not null, "
            + ContactManager.CN_EMAIL + " text not null);";

    private static final String TABLE_ACTIVITY = "create table " + EventManager.TABLE_NAME + " ("
            + EventManager.CN_ID + " integer primary key autoincrement, "
            + EventManager.CN_NAME + " text not null, "
            + EventManager.CN_DATE + " integer, "
            + EventManager.CN_PLACE + " text not null, "
            + EventManager.CN_REPEAT + " text, "
            + EventManager.CN_CATEGORY + " int not null, "
            + EventManager.CN_DURATION + " int not null);";

    public static final String TABLE_CATEGORY = "create table " + CategoryManager.TABLE_NAME + " ("
            + CategoryManager.CN_ID + " integer primary key autoincrement, "
            + CategoryManager.CN_NAME + " text not null, "
            + CategoryManager.CN_COLOR + " text not null, "
            + CategoryManager.CN_IS_DEFAULT + " integer);";

    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);

        this.context = context;
    }

    @Override
    @SuppressWarnings("ResourceType")
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CONTACT);
        db.execSQL(TABLE_ACTIVITY);
        db.execSQL(TABLE_CATEGORY);

        /*
        CategoryManager categoryManager = new CategoryManager(context);

        categoryManager.create(context.getString(R.string.category_work), context.getString(R.color.cyan), true);
        categoryManager.create(context.getString(R.string.category_study), context.getString(R.color.lime), true);
        categoryManager.create(context.getString(R.string.category_hoobie), context.getString(R.color.pink), true);
        categoryManager.create(context.getString(R.string.category_others), context.getString(R.color.purple), true);
        */

        // Uncomment this if you want to create hardcoded events
        /*
        try {
            EventManager eventManager = new EventManager(context);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            eventManager.create(new Date(format.parse("2016-06-01").getTime()), "Actividad 1", "", "", 1, 60);
            eventManager.create(new Date(0), "Actividad 2", "", "", 2, 60);
            eventManager.create(new Date(format.parse("2016-06-02").getTime()), "Actividad 3", "", "", 1, 60);
            eventManager.create(new Date(format.parse("2015-06-01").getTime()), "Actividad 4", "", "", 1, 60);
            eventManager.create(new Date(-1), "Actividad 5", "", "", 3, 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
