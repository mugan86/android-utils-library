package anartzmugika.utils.actions;

import android.content.Context;
import android.content.Intent;

/**
 * Created by anartzmugika on 24/11/16.
 */

public class IntentsManage {


    public static void openIntent(Context from_, Class to_)
    {
        Intent openIntent = new Intent(from_, to_);
        (from_).startActivity(openIntent);
    }

    public static void openWebViewIntent(Context from_, Class to_, String url)
    {
        Intent openIntent = new Intent( from_, to_);
        openIntent.putExtra("url", url);
        (from_).startActivity(openIntent);
    }
}
