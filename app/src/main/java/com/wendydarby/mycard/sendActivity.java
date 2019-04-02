package com.wendydarby.mycard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;

import static com.wendydarby.mycard.MainActivity.ECARD_URL;


public class sendActivity extends AppCompatActivity {
    private FloatingActionButton mSendContactButton;


    private EditText mEmailView;
    private EditText mContactnameView;
    private EditText mMobileView;
    private EditText mEventView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendtonewcontact);
        mSendContactButton = (FloatingActionButton) findViewById(R.id.sendcontact);

        mEmailView = (EditText) findViewById(R.id.contact_email);
        mContactnameView = (EditText) findViewById(R.id.newcontactname);
        mMobileView = (EditText) findViewById(R.id.contact_mobile);
        mEventView = (EditText) findViewById(R.id.contact_event);
//need to find out how to get enter to to traverse through tht enext field just like tab does

        Log.d("MyCard", "yes! We are making a new contact.");

        mSendContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyCard", "Let's start handling new contact info!");
                //want to get tricky? add scan ability through camera to get contact info off of a business card
                //should probably implement a clear for New Contact to make sure info isnt persisting


                NewContact mNewContact = new NewContact();

                mNewContact.setContactName(mContactnameView.getText().toString());
                mNewContact.setContactEmail(mEmailView.getText().toString());
                mNewContact.setContactMobile(mMobileView.getText().toString());
                mNewContact.setContactEvent(mEventView.getText().toString());


                //Save info to contacts, evernote, or both and send link to new contact via phone or email if mobile not provided.

                sendCardtoContact(mNewContact, ECARD_URL);
                saveContact(mNewContact);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(sendActivity.this, com.wendydarby.mycard.MainActivity.class);
        finish();
        startActivity(intent);
        super.onBackPressed();
    }

    private void saveContact(NewContact mNewContact) {
        //Need to save this info to EN and or Contacts
        Log.d("MyCard", "Roger! New Contact is "+ mNewContact.getContactName());
        Log.d("MyCard", "Their info is "+ mNewContact.getContactEmail()+"  "+mNewContact.getContactMobile());
        Log.d("MyCard", "We met them at "+mNewContact.getContactEvent());

        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, mNewContact.getContactName());
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, mNewContact.getContactEmail());
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, mNewContact.getContactMobile());
//        intent.putExtra(ContactsContract.Intents.Insert.PHONE,
//                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        intent.putExtra(ContactsContract.Intents.Insert.NOTES,
                "Met at "+mNewContact.getContactEvent()+". Sent them my ecard using MyCard.");

        intent.putExtra("finishActivityOnSaveCompleted", true);

        startActivityForResult(intent, 1);

        Log.d("MyCard", "Back from saving new contact in Contacts.");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(getApplicationContext(),"Result = "+resultCode, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(sendActivity.this, com.wendydarby.mycard.MainActivity.class);

        startActivity(intent);
    }

    private void sendCardtoContact(NewContact mNewContact, String ecardURL) {

        //We should validate email and or number before using and handle errors as appropriate
        Log.d("MyCard", "Sending "+mNewContact.getContactName()+" link "+ ecardURL);
        if( mNewContact.getContactMobile()!= null && !mNewContact.getContactMobile().isEmpty()){
            Log.d("MyCard", "Let's text our new contact at "+mNewContact.getContactMobile());

        }
        else{
            if(mNewContact.getContactEmail()!= null && !mNewContact.getContactEmail().isEmpty()){
                Log.d("MyCard", "Let's email our new contact at "+mNewContact.getContactEmail());

            }else {
                Log.d("MyCard", "Ohno there is no email or phone!");
            }


        }
    }
}
