/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package anartzmugika.library;

import android.content.Context;

import anartzmugika.utils.location.LocationReceiver;

/***************************************************************************************************
 * Created by anartzmugika on 5/12/16. Update: 9/1/2017
 * ---------------------------------------------------------
 * Use Location Receiver to get current user location and extends this functions and override
 * senDataToCurrentActivity to adapt our requirements.
 **************************************************************************************************/

public class CurrentLocationReceiver extends LocationReceiver {

    @Override
    public void sendDataToCurrentActivity(double lat, double lng, Context context)
    {
        if (context.getClass() == MainActivity.class) ((MainActivity)context).receiveLocation(lat, lng);
    }
}
