/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package anartzmugika.utils.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by anartzmugika on 5/12/16.
 */

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
        //if (context.getClass() == Main.class) ((MapsActivity)context).changeMarker(lat, lng, "My location");
    }
}
