package anartzmugika.utils.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/***************************************************************************************************
 * Created by anartzmugika on 2016/12/05. Update: 2017/01/09
 * ---------------------------------------------------------
 * Receiver to get current user location
 ***************************************************************************************************/

public class LocationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("Data receive!...");

        sendDataToCurrentActivity(Double.parseDouble(intent.getExtras().get("lat").toString()),
                Double.parseDouble(intent.getExtras().get("lng").toString()), context);

        System.out.println("Current location " + intent.getExtras().get("coordinates").toString());

    }

    public void sendDataToCurrentActivity(double lat, double lng, Context context)
    {
        //USE to extends and use this function and programming our requeriments
        Toast.makeText(context, String.format("Location: %s - %s", lat, lng), Toast.LENGTH_LONG ).show();
    }
}
