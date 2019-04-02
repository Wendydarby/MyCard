package com.wendydarby.mycard;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class MainActivity extends AppCompatActivity {
    private FloatingActionButton mSendButton;
    private FloatingActionButton mBackButton;
    private WebView mWebView;
    static public String ECARD_URL = "https://ClickMy.info/i/8lGZ/Wendy_Darby";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webview);
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        // I liked 200 for landscape view
        mWebView.setInitialScale(230);
        mWebView.loadUrl(ECARD_URL);

        mSendButton = (FloatingActionButton) findViewById(R.id.send);

        //TODo implement the send button to bring up New COntact View
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyCard", "Let's share content with user!");
                //Change to new textview for entering info.
                Intent intent = new Intent(MainActivity.this, com.wendydarby.mycard.sendActivity.class);
                finish();
                startActivity(intent);
            }
        });


        //Todo disable the back button if main url is shown, if the user has navicated forward enable button to go back.


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
