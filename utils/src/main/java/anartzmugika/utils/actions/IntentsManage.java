package anartzmugika.utils.actions;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import anartzmugika.utils.activities.WebViewActivity;

/**
 * Created by anartzmugika on 24/11/16.
 */

public class IntentsManage {


    public static void openIntent(Context from_, Class to_)
    {
        Intent openIntent = new Intent((Context) from_, to_);
        ((Context)from_).startActivity(openIntent);
    }

    public static void openWebViewIntent(Context from_, Class to_, String url)
    {
        Intent openIntent = new Intent((Context) from_, to_);
        openIntent.putExtra("url", url);
        ((Context)from_).startActivity(openIntent);
    }
}
