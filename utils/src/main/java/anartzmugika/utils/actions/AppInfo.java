package anartzmugika.utils.actions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/************************************************************
 * Created by anartzmugika on 24/11/16.
 ************************************************************/

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
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="
                    + pkg_name)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + pkg_name)));
        }
        ((Activity)context).overridePendingTransition(0, 0);

    }
}
