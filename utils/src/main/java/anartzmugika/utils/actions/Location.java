/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package anartzmugika.utils.actions;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by anartzmugika on 5/12/16.
 */

public class Location {

    public static boolean CheckGpsStatus(Context context) {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static  void showSettingsAlert(final Context context, String message_title, String message, String config_btn) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle(message_title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // On pressing Settings button
        alertDialog.setPositiveButton(config_btn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                //mContext.startActivity(intent);
                ((AppCompatActivity)context).startActivityForResult(intent, 0);
                ((AppCompatActivity)context).overridePendingTransition(0, 0);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Ez dut nahi nire kokapena erabili", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("OUR LOCATION NOT FOUND, USE EIBAR LOCATION");

                dialog.cancel();


                Intent intent = ((AppCompatActivity)context).getIntent();
                ((AppCompatActivity)context).finish();
                (context).startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(0, 0);

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
