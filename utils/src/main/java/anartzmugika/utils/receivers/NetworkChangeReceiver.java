package anartzmugika.utils.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import anartzmugika.utils.Constants;
import anartzmugika.utils.tools.PreferenceOptions;
import anartzmugika.utils.tools.NetworkUtil;


/***************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 22/6/16.
 * Receive change when detect network status change
 ***************************************************************************/

public class NetworkChangeReceiver extends BroadcastReceiver {
    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        String status = NetworkUtil.getConnectivityStatusString(context);

        Log.e("Receiver ", "" + status);

        switch (status) {
            case Constants.NOT_CONNECT:
                Log.e("Receiver ", "not connection");// your code when internet lost

                break;
            case Constants.CONNECT_TO_WIFI:
                Log.e("Receiver ", "connected to internet (WIFI)");// your code when internet lost
                break;
            default:
                Log.e("Receiver ", "connected to internet (NO WIFI)");//your code when internet connection come back
                break;
        }

        System.out.println("Last known connection (" + PreferenceOptions.getPreferenceValue(context, Constants.MOMENT_CONNECTION) + ") replace with "
                + status);
        PreferenceOptions.setPreferenceValues(context, new String [] {Constants.MOMENT_CONNECTION},new String[]{status});
    }
}
