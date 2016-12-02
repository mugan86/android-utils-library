package anartzmugika.utils.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import anartzmugika.utils.R;


/**
 * Created by anartzmugika on 8/7/16.
 */

public class WebViewActivity extends AppCompatActivity {
    private WebView myWebView;
    private Toolbar toolbar;
    private LinearLayout loadDataLinearLayout;
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
        //loadDataLinearLayout = (LinearLayout) findViewById(R.id.loadDataLinearLayout);
        //loadDataLinearLayout.setVisibility(View.VISIBLE);
        //myWebView.setVisibility(View.GONE);

        if( savedInstanceState == null ) {
            String url =
                    getIntent().getStringExtra("url").replace("webview://", "http://");
            // do something with this URL.
            loadURL(url);

            //toolbar = ToolbarManage.getTitleSubtitleABToolbar(WebViewActivity.this, (Toolbar) findViewById(R.id.toolbar), "Mendiak", url, true, "");
        }
    }



    /*private void loadURL(String url)
    {
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        System.out.println("Load URL: " + url);

        /*

        toolbar.setVisibility(View.VISIBLE);

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


    }*/

    private void loadURL(final String url)
    {
        System.out.println("Load URL: " + url);

        final boolean[] loadingFinished = {true};
        final boolean[] redirect = {false};

        //toolbar.setVisibility(View.VISIBLE);

        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                if (!loadingFinished[0]) {
                    redirect[0] = true;
                }

                loadingFinished[0] = false;
                //myWebView.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(
                    WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(getApplicationContext(), "Start load page...", Toast.LENGTH_LONG).show();
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!redirect[0]){
                    loadingFinished[0] = true;
                }

                if(loadingFinished[0] && !redirect[0]){
                    //HIDE LOADING IT HAS FINISHED
                    Toast.makeText(getApplicationContext(), "Finish load page...", Toast.LENGTH_LONG).show();
                    //loadDataLinearLayout.setVisibility(View.GONE);
                    myWebView.setVisibility(View.VISIBLE);
                } else{
                    redirect[0] = false;
                }
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
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

}


