package anartzmugika.library;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import anartzmugika.utils.Constants;
import anartzmugika.utils.actions.DateTime;
import anartzmugika.utils.actions.Location;
import anartzmugika.utils.actions.NetworkUtil;
import anartzmugika.utils.layouts.MyView;
import anartzmugika.utils.location.GPSNetworkTracker;

public class MainActivity extends AppCompatActivity {

    private ToggleButton locationGetStartStopToggleButton;
    private Intent intent;
    private CurrentLocationReceiver broadcastReceiver;
    private boolean active;
    private Context context;
    @Override
    public void onResume() {
        super.onResume();

        //Return from GPS Network Tracker localization info (Need to register receiver in manifest)
        if (broadcastReceiver == null) {
            broadcastReceiver = new CurrentLocationReceiver();
        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.context = MainActivity.this;

        MyView view = new MyView(this);

        //Load custom view to reuse
        setContentView(view);

        locationGetStartStopToggleButton = (ToggleButton) findViewById(R.id.locationGetStartStopToggleButton);
        active = false;
        locationGetStartStopToggleButton.setChecked(active);
        intent = new Intent(MainActivity.this, GPSNetworkTracker.class);

        locationGetStartStopToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check localization permission
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    locationGetStartStopToggleButton.setChecked(false);
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            Constants.REQUEST_TO_LOCALIZATION_DEVICE);
                } else {

                    if (Location.CheckGpsStatus(MainActivity.this)) {
                        active = !active;
                        locationGetStartStopToggleButton.setChecked(true);
                        toggleLocationUpdates(active);
                    } else {
                        String message_title = "Location configuration";
                        String message = "Active GPS to check your current location. ";
                        String config_btn = "Configurate";
                        Location.showSettingsAlert(MainActivity.this, message_title, message, config_btn);
                        locationGetStartStopToggleButton.setChecked(false);
                    }
                }
            }
        });

        System.out.println(NetworkUtil.getNetworkInfo(this));
        System.out.println(NetworkUtil.isConnectToInternet(this));
        System.out.println(DateTime.getCurrentData());
        System.out.println(DateTime.isFirstMonthDay());

        //view.addText("Test with library!!!");
    }

    private void toggleLocationUpdates(boolean enable) {


        if (enable) {

            //enableLocationUpdates();
            startService(intent);
            locationGetStartStopToggleButton.setChecked(true);
            Toast.makeText(MainActivity.this, "Start to Find Locations...", Toast.LENGTH_LONG).show();
        } else {
            stopService(intent);
            //disableLocationUpdates();
            Toast.makeText(getApplicationContext(), "Stop service...", Toast.LENGTH_LONG).show();
            locationGetStartStopToggleButton.setChecked(false);
        }

    }

    public void receiveLocation(double lat, double lng) {
        System.out.println("Receive with Current Location Receiver: " + lat + " / " + lng);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        stopService(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == Constants.REQUEST_TO_LOCALIZATION_DEVICE) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permission granted to get location
                active = !active;
                //updateButton.setT
                toggleLocationUpdates(true);


            } else {
                Log.e("locations", "Permission denied, check your app configuration please!!");
            }
        }
    }

    public Context getContextMainActivity()
    {
        return this.context;
    }
}
