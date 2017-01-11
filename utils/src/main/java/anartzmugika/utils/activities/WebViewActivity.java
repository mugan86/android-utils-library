package anartzmugika.utils.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import anartzmugika.utils.R;


/***************************************************************************************************
 * Created by anartzmugika on 8/7/16.
 * Activity to execute pass url to show in app internal browser
 **************************************************************************************************/

public class WebViewActivity extends AppCompatActivity {
    private WebView myWebView;
    private Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*****************************************************************
         *
         * IMPORTANT!!! To use webview to load urls necesary Internet
         * connection permission active in Manifest
         *
         *  <uses-permission android:name="android.permission.INTERNET"/>
         *****************************************************************/
        setContentView(R.layout.webview);


        if (Build.VERSION.SDK_INT >= 20) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.mendiak_buttons));
        }

        myWebView = (WebView) findViewById(R.id.myWebView);

        if( savedInstanceState == null ) {
            String url =
                    getIntent().getStringExtra("url").replace("webview://", "http://");
            // do something with this URL.
            loadURL(url);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
        }
    }



    private void loadURL(String url)
    {
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        System.out.println("Load URL: " + url);

        if (toolbar!= null) toolbar.setVisibility(View.VISIBLE);

        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }
        });

        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
            {
                WebView.HitTestResult result = view.getHitTestResult();
                String data = result.getExtra();
                Context context = view.getContext();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                context.startActivity(browserIntent);
                return false;
            }
        });


    }


}


