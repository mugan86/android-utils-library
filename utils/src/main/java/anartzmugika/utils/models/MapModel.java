package anartzmugika.utils.models;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/***************************************************************************************************
 * Created by anartzmugika on 11/1/17.
 **************************************************************************************************/

public class MapModel{

    private GoogleMap googleMap;
    private boolean control_zoom, gestures;
    private UiSettings UiSettings;

    public MapModel(GoogleMap googleMap, boolean control_zoom, boolean gestures)
    {
        this.googleMap = googleMap;
        this.control_zoom = control_zoom;
        this.gestures = gestures;
        setUiSettings(googleMap, control_zoom, gestures);
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public boolean isControl_zoom() {
        return control_zoom;
    }

    public void setControl_zoom(boolean control_zoom) {
        this.control_zoom = control_zoom;
    }

    public boolean isGestures() {
        return gestures;
    }

    public void setGestures(boolean gestures) {
        this.gestures = gestures;
    }


    public void setUiSettings (GoogleMap map, boolean control_zoom, boolean gestures)
    {
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setCompassEnabled(false);
        //if (control_zoom)
        uiSettings.setZoomControlsEnabled(control_zoom);
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setAllGesturesEnabled(gestures);
        //uiSettings.setMapToolbarEnabled(true);
        this.UiSettings = uiSettings;
    }

    public void addMarker(LatLng location, String title)
    {
        getGoogleMap().addMarker(new MarkerOptions().position(location).title(title));
    }

    public void moveCamera(LatLng location)
    {
        getGoogleMap().moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    /**********************************************************************************************
     *
     * @param type Define select map type
     *             1: Normal
     *             2: Hybrid
     *             3: Satellite
     *             4: Terrain
     */
    public void setMapType(int type)
    {
        if (type == 1) getGoogleMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (type == 2) getGoogleMap().setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (type == 3) getGoogleMap().setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        if (type == 4) getGoogleMap().setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }



}
