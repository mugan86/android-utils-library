package anartzmugika.utils.actions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/***************************************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 24/11/16.
 ***************************************************************************************************/

public class IntentsManage {

    /*
    Open Intent Activity without pass bundle arguments
     */
    public static void openIntent(Context from_, Class to_)
    {
        Intent openIntent = new Intent(from_, to_);
        (from_).startActivity(openIntent);
    }

    /*
    Open Intent Activity with pass bundle arguments
     */
    public static void openIntent(Context from_, Class to_, Bundle bundle)
    {
        Intent openIntent = new Intent(from_, to_);
        openIntent.putExtras(bundle);
        (from_).startActivity(openIntent);
    }

    /*
    Open Library WebViewActivity
     */
    public static void openWebViewIntent(Context from_, Class to_, String url)
    {
        Intent openIntent = new Intent( from_, to_);
        openIntent.putExtra("url", url);
        (from_).startActivity(openIntent);
    }
}
