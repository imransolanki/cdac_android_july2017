package self.edu.activitydemo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final int REQ_CODE_REGISTER_SCREEN = 1;
    public static final int REQ_CODE_DELETE_SCREEN = 2;
    public static final int REQ_CODE_CONTACT_SCREEN = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_registerMeBtn(View view) {
        Intent registerScreen = new Intent(this, RegisterActivity.class);
        //startActivity(registerScreen);
        startActivityForResult(registerScreen, REQ_CODE_REGISTER_SCREEN);
    }

    public void onClick_deleteMeBtn(View view) {
        Intent registerScreen = new Intent(this, DeleteActivity.class);
        startActivityForResult(registerScreen, REQ_CODE_DELETE_SCREEN);
    }

    public void onClick_contactListMeBtn(View view) {
        Intent contactIntent = new Intent(Intent.ACTION_PICK);
        contactIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        //contactIntent.setType("image/*");

        if (contactIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(contactIntent, REQ_CODE_CONTACT_SCREEN);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_REGISTER_SCREEN:
                if (requestCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Success-Register Screen", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Failure-Register Screen", Toast.LENGTH_LONG).show();
                }
                break;
            case REQ_CODE_DELETE_SCREEN:
                Toast.makeText(this, "Delete Screen", Toast.LENGTH_LONG).show();
                break;
            case REQ_CODE_CONTACT_SCREEN:
                if (resultCode == Activity.RESULT_OK) {
                    // contact is selected
                    // TODO retrieve data i.e. contact name
                    Toast.makeText(this, "contact is selected", Toast.LENGTH_LONG).show();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // contact is not selected
                    Toast.makeText(this, "contact is not selected", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                Toast.makeText(this, "Some other screen...", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
