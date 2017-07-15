package self.edu.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by imran on 13/7/17.
 */


// Step-1
public class DBHelper extends SQLiteOpenHelper {


    public static final String CREATE_TABLE_USER_DETAIL =
            "CREATE TABLE user_detail_tbl(email string PRIMARY KEY ,password string, phoneNumber string)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create tables
        sqLiteDatabase.execSQL(CREATE_TABLE_USER_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Nothing to do
    }
}
