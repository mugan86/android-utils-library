package anartzmugika.utils.actions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import anartzmugika.utils.Constants;

/***************************************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 24/11/16.
 *
 * Open Google Play app page to show app info and offer possibility to user to valorate and comment
 * app
 ***************************************************************************************************/

public class AppInfo {

    private static String getPackageName(Context context)
    {
        return context.getPackageName();
    }

    public static void goToGooglePlayAppPage (Context context)
    {
        String pkg_name = getPackageName(context);
        System.out.println("Package name: " + pkg_name);
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(Constants.GOOGLE_PLAY_MARKET_REFERENCE_WITH_ID, pkg_name))));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(Constants.GOOGLE_PLAY_MAIN_PAGE_WITH_ID, pkg_name))));
        }
        ((Activity)context).overridePendingTransition(0, 0);

    }
}
