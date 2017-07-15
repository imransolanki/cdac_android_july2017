package self.edu.databasedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView result;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initDatabase();
    }

    private void initDatabase() {
        // Step-2
        DBHelper databaseHelper = new DBHelper(this, "Whatsapp.db", null, 1);
        database = databaseHelper.getWritableDatabase();
    }

    private void initUi() {
        result = (TextView) findViewById(R.id.resultText);
    }

    public void onClick_insertBtn(View view) {
        ContentValues userRow = new ContentValues();

//        userRow.put("email", "imran@cdac.com");
//        userRow.put("password", "123456");
//        userRow.put("phoneNumber", "9763067364");

        userRow.put("email", "preetam@cdac.com");
        userRow.put("password", "654321");
        userRow.put("phoneNumber", "97214443");

        long rowsInserted = database.insert("user_detail_tbl", null, userRow);
        if (rowsInserted > 0) {
            displayResult("Record Inserted!");
        }
    }

    public void onClick_displayBtn(View view) {
        Cursor cursor = database.rawQuery("SELECT * FROM user_detail_tbl", null);
        String finalResult = "";
        while (cursor.moveToNext()) {
            String email = cursor.getString(0);
            String password = cursor.getString(1);
            String phoneNumber = cursor.getString(2);
            String result = email + " " + password + " " + phoneNumber + "\n";
            finalResult += result;
        }
        displayResult(finalResult);
    }

    public void onClick_deleteBtn(View view) {
        String[] whereArgument = new String[]{"imran@cdac.com"};
        long deletedRecords = database.delete("user_detail_tbl", "email=?", whereArgument);
        if (deletedRecords > 0) {
            displayResult("Successfully deleted " + deletedRecords + " records");
        } else {
            displayResult("No Records Deleted!");
        }
    }


    private void displayResult(String resultString) {
        result.setText(resultString);
    }
}
