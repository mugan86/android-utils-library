package anartzmugika.utils.tools;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/***************************************************************************************************
 * Created by anartzmugika on 2016/12/05. Updated by Anartz Mugika on 2017/01/09
 * -------------------------------------------------------------------------------------------------
 * Class to make actions with relation about GPS Provider location.
 **************************************************************************************************/

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
