package ca.vickypatel.dagger_2.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vicky on 13/12/15.
 */
public class DatabaseAdapter {

    DatabaseHelper helper;
    Context context;


    public DatabaseAdapter(Context context) {
        this.context = context;
        helper = new DatabaseHelper(context);
    }

    public long insertIntoTest(String companyName, String position) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COMPANY_NAME, companyName);
        cv.put(DatabaseHelper.POSITION, position);
        long id = db.insert(DatabaseHelper.TEST_TABLE_NAME, null, cv);
        return id;
    }


    public static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "test.db";
        //Every time Change Version Value when database is modified
        private static final int DATABASE_VERSION = 2;

        //JOBS TABLE
        private static final String TEST_TABLE_NAME = "test";
        private static final String TEST_ID = "_id";
        private static final String COMPANY_NAME = "company_name";
        private static final String POSITION = "position";



        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            System.out.println("onCreate form database helper");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TEST_TABLE_NAME + " ("
                    + TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COMPANY_NAME + " VARCHAR(100), "
                    + POSITION + "  VARCHAR(100) "
                    + ");");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            System.out.println("onUpgrade form database helper");
            System.out.println("Old Version is " + oldVersion + " and New Version is " + newVersion);

            // Payment Start and End date column added
            if (oldVersion < 2) {
                db.execSQL("DROP TABLE " + TEST_TABLE_NAME + ";");
                onCreate(db);
            }


        }

    }
}
