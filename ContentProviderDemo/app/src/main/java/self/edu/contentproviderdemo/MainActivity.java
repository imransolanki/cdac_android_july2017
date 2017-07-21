package self.edu.contentproviderdemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String TAG = "contentProviderDemo";

    private final int REQ_CODE_CONTACT_APP = 1;
    private final int REQ_CODE_DELETE_CONTACT = 2;

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_CONTACT_APP:
                if (resultCode == RESULT_CANCELED) {
                    return;
                }
                // Very important !!!
                Uri selectedContact = data.getData();
                processAndDisplaySelectedContact(selectedContact);
                Log.d(TAG, "onActivityResult: selectedContactUri=" + selectedContact);
                break;
            case REQ_CODE_DELETE_CONTACT:
                if (resultCode == RESULT_CANCELED) {
                    return;
                }
                Uri contactToDelete = data.getData();
                deleteContactAndDisplayResult(contactToDelete);
                break;
            default:
                Toast.makeText(this, "Don't Know what's this !", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_readContactBtn(View view) {
        launchContactApplication(REQ_CODE_CONTACT_APP);
    }

    public void onClick_deleteBtn(View view) {
        launchContactApplication(REQ_CODE_DELETE_CONTACT);
    }

    private void initUi() {
        resultText = (TextView) findViewById(R.id.result);
    }

    private void deleteContactAndDisplayResult(Uri contactToDelete) {
        Log.d(TAG, "deleteContactAndDisplayResult() called with: contactToDelete = [" + contactToDelete + "]");

        final long deletedContacts = getContentResolver().delete(contactToDelete, null, null);
        if (deletedContacts > 0) {
            resultText.setText(getString(R.string.contact_deleted_successfully));
        } else {
            resultText.setText(getString(R.string.contact_deletion_failed));
        }

    }

    private void processAndDisplaySelectedContact(Uri selectedContact) {
        Log.d(TAG, "processAndDisplaySelectedContact() called with: selectedContact = [" + selectedContact + "]");

        String[] columns = new String[]{
                ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME
        };

        Cursor contactCursor = getContentResolver().query(selectedContact, columns, null, null, null);
        contactCursor.moveToNext();

//        final int contactId = contactCursor.getInt(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));
//        final String name = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        final int contactId = contactCursor.getInt(0);
        final String name = contactCursor.getString(1);

        Log.d(TAG, "processAndDisplaySelectedContact: contactId = " + contactId + " name=" + name);

        resultText.setText("(" + contactId + "," + name + ")");
    }

    private void launchContactApplication(final int REQ_CODE) {
        Intent contactApp = new Intent(Intent.ACTION_PICK);
        contactApp.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (contactApp.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(contactApp, REQ_CODE);
        }
    }

}
