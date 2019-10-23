package com.wendydarby.mycard;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import se.anyro.sms.CompatibilitySmsManager;


public class NewContactController {
        private NewContact mNewContact;
        private MyCard mMyCard;
        private NewContactView mContactView;
        public static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    public NewContactController(NewContact model, NewContactView view, MyCard mECard) {
        this.mNewContact =model;
        this.mContactView = view;
        this.mMyCard = mECard;
    }

    public NewContact getNewContact() {
        return mNewContact;
    }

    public void setNewContact(NewContact mNewContact) {
        this.mNewContact = mNewContact;
    }

    public void saveContact() {
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

        this.mContactView.startActivityForResult(intent, 1);

        Log.d("MyCard", "Back from saving new contact in Contacts.");

        //Sharing info to be saved in other app like Evernote
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Contact info for "+ mNewContact.getContactName()+". Recieved from MyCard.\n"+
                "Email: "+mNewContact.getContactEmail()+"\n"+
                "Mobile: "+mNewContact.getContactMobile()+"\n"+
                "Met at "+mNewContact.getContactEvent()+".");

        shareIntent.setType("text/plain");
        this.mContactView.startActivity(shareIntent);

    }

    public void sendCardtoContact(String ecardURL) {

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
                +" I'd love to get together to see how we can help each other grow our businesses. \n" + mMyCard.getEcardUrl());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            this.mContactView.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this.mContactView, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSMSMessage() {

        //checkPermissions
        if (ContextCompat.checkSelfPermission(this.mContactView,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.mContactView,
                    Manifest.permission.SEND_SMS)) {
                Toast.makeText(this.mContactView.getApplicationContext(), "sendSMSMessage checked permissions. Should show Request Perm Rationale.",
                        Toast.LENGTH_LONG).show(); //I removed Perms and not seeing the request to user.
                //HAL HELP - test case where user remover SMS permissions in settings. I never see the request for permission come up..it looks like the listener fires in debugger but It doesn't step through it jumps back from sendSMSMessage;
            } else {
                ActivityCompat.requestPermissions(this.mContactView,
                        new String[]{Manifest.permission.SEND_SMS},
                        this.MY_PERMISSIONS_REQUEST_SEND_SMS);
                Log.d("MyCard", "RequestedPermissions...look for the Result callback.");
            }

        }else{
            //Send the Text
            // This library addresses the problem of having double messages being sent in error.
            CompatibilitySmsManager smsManager = CompatibilitySmsManager.getDefault();
            smsManager.sendTextMessage(mNewContact.getContactMobile(), null,
                    "Hi " + mNewContact.getContactName() +
                            ", I enjoyed meeting you! Here is the link to my e-card. " + mMyCard.getEcardUrl(), null, null);

            Toast.makeText(this.mContactView.getApplicationContext(), "SMS sent from end of sendMEssage.",
                    Toast.LENGTH_LONG).show();
            Log.d("MyCard", "Text message was sent.");
        }



    }


}
