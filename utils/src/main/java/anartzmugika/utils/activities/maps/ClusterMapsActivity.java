package anartzmugika.utils.activities.maps;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

import anartzmugika.utils.R;
import anartzmugika.utils.activities.maps.cluster.PersonClusterRenderer;
import anartzmugika.utils.models.MapModel;
import anartzmugika.utils.models.PersonModel;

public class ClusterMapsActivity extends FragmentActivity
        implements OnMapReadyCallback, ClusterManager.OnClusterClickListener<PersonModel>,
        ClusterManager.OnClusterInfoWindowClickListener<PersonModel>,
        ClusterManager.OnClusterItemClickListener<PersonModel>,
        ClusterManager.OnClusterItemInfoWindowClickListener<PersonModel> {

    private ClusterManager<PersonModel> mClusterManager;
    private ArrayList<PersonModel> person_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        person_list = new ArrayList<>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapModel map = new MapModel(googleMap, true, true);
        map.setMapType(1);

        new PersonModel().addItems(person_list);
        //Cluster Manage with click actions in cluster and inside cluster markers :)

        LatLng center_location = map.getMapCenterPosition(1, person_list);

        Location center_point = new Location("center_point");
        center_point.setLatitude(center_location.latitude);
        center_point.setLongitude(center_location.longitude);

        float max_distance = 0;
        for (PersonModel person : person_list) {

            System.out.println(person.getPosition());

            Location location_mountain = new Location("select_location");

            location_mountain.setLatitude(person.getPosition().latitude);
            location_mountain.setLongitude(person.getPosition().longitude);
            float distance = center_point.distanceTo(location_mountain);
            System.out.println(distance +  " to check....");
            if (distance > max_distance) {
                max_distance = distance;
                System.out.println(distance +  " new asign....");
            }
        }


        map.moveCamera(center_location, max_distance);
        mClusterManager = new ClusterManager<>(this, map.getGoogleMap());
        mClusterManager.setRenderer(new PersonClusterRenderer(this, map.getGoogleMap() ,mClusterManager, ClusterMapsActivity.this));
        map.getGoogleMap().setOnCameraChangeListener(mClusterManager);
        map.getGoogleMap().setOnMarkerClickListener(mClusterManager);
        map.getGoogleMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);
        for(PersonModel person:person_list)
        {
            mClusterManager.addItem(person);
        }

        mClusterManager.cluster();


        map.getGoogleMap().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker arg0) {

                for (PersonModel person : person_list) {
                    if (person.name.equals(arg0.getTitle())) {

                        String uri = "http://maps.google.com/maps?daddr="+arg0.getPosition().latitude+","+
                                arg0.getPosition().longitude;
                        System.out.println("Google maps url: "+uri);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public boolean onClusterClick(Cluster<PersonModel> cluster) {
        return false;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<PersonModel> cluster) {

    }

    @Override
    public boolean onClusterItemClick(PersonModel personModel) {
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(PersonModel personModel) {

    }

}
