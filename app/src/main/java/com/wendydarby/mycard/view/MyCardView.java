package com.wendydarby.mycard.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wendydarby.mycard.R;
import com.wendydarby.mycard.model.MyCard;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class MyCardView extends AppCompatActivity {
    private FloatingActionButton mSendButton;
    private FloatingActionButton mBackButton;
    private WebView mWebView;
    private MyCard mMyCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webview);
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        // I liked 200 for landscape view
        mWebView.setInitialScale(230);
        mWebView.loadUrl(mMyCard.getEcardUrl());

        mSendButton = findViewById(R.id.send);
// Placing wishlist of feature ideas here even if they should be implemented elsewhwere so that the list can be easily curated.
        //TODo look into searching for MyCard contacts on Linked In and asking user if he/she
        // wants to automatically link with them on LinkedIn

        //TODO look into using the camera to auto populate the email and  phone fields from a business card

        //TODO allow multiple e-cards to be shown and user to select

        //TODO allow configuration so new users(not Wendy) can use
        //TODO in a Premium version allow outgoing messages to be customized and LinkedIn feature to be enabled
        //TODO premium feature, allow reminders to be set for followup in 3 days, or chosen time frame


        //TODO create an android wear app that allows new contacts to be created and ecard be sent from Gear
        //TODO allow user to store info in their preffered CRM - look into automating a batch export/import or forwarding an intnet
        //Look into creating my own user accounts on Firbase and setting up my own ecard creator.

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyCard", "Let's share content with user!");
                //Change to new textview for entering info.
                Intent intent = new Intent(MyCardView.this, NewContactView.class);
                finish();
                startActivity(intent);
            }
        });


        //Todo disable the back button if main url is shown, if the user has navigated forward enable button to go back.


    }
    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()) {
            Log.d("MyCard", "Back Button says Navigating back in the WebView is possible.");
            mWebView.goBack();
        } else {
            Log.d("MyCard", "Back Button pressed to navigate up the hierarchy");
            super.onBackPressed();
        }
    }

    public class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }
}
