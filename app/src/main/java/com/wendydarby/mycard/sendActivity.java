package com.wendydarby.mycard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
//import android.telephony.SmsManager;

import se.anyro.sms.CompatibilitySmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.login.EvernoteLoginActivity;

import static com.wendydarby.mycard.BuildConfig.EVERNOTE_CONSUMER_KEY;
import static com.wendydarby.mycard.BuildConfig.EVERNOTE_CONSUMER_SECRET;
import static com.wendydarby.mycard.MainActivity.ECARD_URL;


public class sendActivity extends AppCompatActivity {
    private FloatingActionButton mSendContactButton;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    private EditText mEmailView;
    private EditText mContactnameView;
    private EditText mMobileView;
    private EditText mEventView;
    private NewContact mNewContact= new NewContact();


    private static final boolean SUPPORT_APP_LINKED_NOTEBOOKS = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendtonewcontact);
        mSendContactButton = findViewById(R.id.sendcontact);

        mEmailView = findViewById(R.id.contact_email);
        mContactnameView = findViewById(R.id.newcontactname);
        mMobileView = findViewById(R.id.contact_mobile);
        mEventView = findViewById(R.id.contact_event);
//need to find out how to get enter to to traverse through tht enext field just like tab does

        Log.d("MyCard", "yes! We are making a new contact.");

       /* String consumerKey;
        if ("Your consumer key".equals(EVERNOTE_CONSUMER_KEY)) {
            consumerKey = EVERNOTE_CONSUMER_KEY;
        } else {
            // isn't the default value anymore
            consumerKey = EVERNOTE_CONSUMER_KEY;
        }

        String consumerSecret;
        if ("Your consumer secret".equals(EVERNOTE_CONSUMER_SECRET)) {
            consumerSecret = BuildConfig.EVERNOTE_CONSUMER_SECRET;
        } else {
            // isn't the default value anymore
            consumerSecret = EVERNOTE_CONSUMER_SECRET;
        }

        //Set up the Evernote singleton session, use EvernoteSession.getInstance() later
        new EvernoteSession.Builder(this)
                .setEvernoteService(EVERNOTE_SERVICE)
                .setSupportAppLinkedNotebooks(SUPPORT_APP_LINKED_NOTEBOOKS)
                .setForceAuthenticationInThirdPartyApp(true)
//                .setLocale(Locale.SIMPLIFIED_CHINESE)
                .build(consumerKey, consumerSecret)
                .asSingleton();

        //registerActivityLifecycleCallbacks(new LoginChecker());
        registerComponentCallbacks(new EvernoteLoginActivity());*/


        mSendContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyCard", "Let's start handling new contact info!");
                //want to get tricky? add scan ability through camera to get contact info off of a business card
                //should probably implement a clear for New Contact to make sure info isnt persisting



                mNewContact.setContactName(mContactnameView.getText().toString());
                mNewContact.setContactEmail(mEmailView.getText().toString());
                mNewContact.setContactMobile(mMobileView.getText().toString());
                mNewContact.setContactEvent(mEventView.getText().toString());


                //Save info to contacts, evernote, or both and send link to new contact via phone or email if mobile not provided.

                sendCardtoContact(ECARD_URL);
                saveContact();


            }
        });
    }

    private void shareContact() {



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(sendActivity.this, com.wendydarby.mycard.MainActivity.class);
        finish();
        startActivity(intent);
        super.onBackPressed();
    }

    private void saveContact() {
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

        //Sharing info to be saved in other app like Evernote
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Contact info for "+ mNewContact.getContactName()+". Recieved from MyCard.\n"+
             "Email: "+mNewContact.getContactEmail()+"\n"+
             "Mobile: "+mNewContact.getContactMobile()+"\n"+
             "Met at "+mNewContact.getContactEvent()+".");

        shareIntent.setType("text/plain");
        startActivity(shareIntent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(getApplicationContext(),"Result = "+resultCode, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, com.wendydarby.mycard.MainActivity.class);

        startActivity(intent);
    }

    private void sendCardtoContact(String ecardURL) {

        //We should validate email and or number before using and handle errors as appropriate
        Log.d("MyCard", "Sending "+mNewContact.getContactName()+" link "+ ecardURL);
        if( mNewContact.getContactMobile()!= null && !mNewContact.getContactMobile().isEmpty()){
            Log.d("MyCard", "Let's text our new contact at "+mNewContact.getContactMobile());

            sendSMSMessage();
        }
        else{
            if(mNewContact.getContactEmail()!= null && !mNewContact.getContactEmail().isEmpty()){
                Log.d("MyCard", "Let's email our new contact at "+mNewContact.getContactEmail());
                sendMIMEMessage();

            }else {
                Log.d("MyCard", "Ohno there is no email or phone!");
            }
        }
    }

    private void sendMIMEMessage() {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("message/rfc822");
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mNewContact.getContactEmail()});
        i.putExtra(Intent.EXTRA_SUBJECT, "Let's connect!");
        i.putExtra(Intent.EXTRA_TEXT   , mNewContact.getContactName()+", I enjoyed meeting you today at "+
                mNewContact.getContactEvent()+". Here is the link to my e-card."
                +" I'd love to get together to see how we can help each other grow our businesses. \n" + ECARD_URL);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMSMessage() {

        //checkPermissions
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
                Toast.makeText(getApplicationContext(), "sendSMSMessage checked permissions. Should show Request Perm Rationale.",
                        Toast.LENGTH_LONG).show(); //I removed Perms and not seeing the request to user.
                //HAL HELP - test case where user remover SMS permissions in settings. I never see the request for permission come up..it looks like the listener fires in debugger but It doesn't step through it jumps back from sendSMSMessage;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                Log.d("MyCard", "RequestedPermissions...look for the Result callback.");
            }

        }else{
            //Send the Text
            // This library addresses the problem of having double messages being sent in error.
            CompatibilitySmsManager smsManager = CompatibilitySmsManager.getDefault();
            smsManager.sendTextMessage(mNewContact.getContactMobile(), null,
                    "Hi " + mNewContact.getContactName() +
                            ", (SENDMSG)I enjoyed meeting you! Here is the link to my e-card. " + ECARD_URL, null, null);

            Toast.makeText(getApplicationContext(), "SMS sent from end of sendMEssage.",
                    Toast.LENGTH_LONG).show();
            Log.d("MyCard", "Text message was sent.");
        }



    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //HAL HELP do I need to do that super up there? before or after my code? it was autogenerated but the code below was in atutorial without mention of the super I think
        Log.d("MyCard", "RequestPermissionsResult...is firing.");

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                   sendSMSMessage(); //Jumping back to check perms again and send messasge now that permission has been requested
                    //TODO Remove the toast below once happy with the result

                    Toast.makeText(getApplicationContext(), "SMS sent from Permission request.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    // Save info to Evernote
    /*
     * Initial development is done on Evernote's testing service, the sandbox.
     *
     * Change to PRODUCTION to use the Evernote production service
     * once your code is complete.
     */
    private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;

}
