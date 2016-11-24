package anartzmugika.utils.actions;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/*****************************************************
 * Created by anartzmugika on 24/11/16.
 */

public class Check {
    public static boolean isConnectToInternet(Context context)
    {
        //Check Internet connection status
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected())
        {
            return true;
        }
        else
        {
            //No connection
            return false;
        }
    }

    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
