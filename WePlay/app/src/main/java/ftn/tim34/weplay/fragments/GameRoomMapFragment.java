package ftn.tim34.weplay.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.activities.GameRoomActivity;
import ftn.tim34.weplay.adapters.CustomGameRoomList;
import ftn.tim34.weplay.dialogs.LocationDialog;
import ftn.tim34.weplay.dto.GamingRoomMap;
import ftn.tim34.weplay.model.GamingRoom;
import ftn.tim34.weplay.service.ServiceUtils;
import ftn.tim34.weplay.sync.GamingRoomSqlSync;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GameRoomMapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    GoogleMap map;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private LocationManager locationManager;
    private String provider;
    private SupportMapFragment supportMapFragment;
    private AlertDialog alertDialog;
    private Marker myMarker;
    private List<Marker> grLocation = new ArrayList<>();
    private List<GamingRoomMap> grMap = new ArrayList<>();
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_room_map, container, false);
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.details_map);
            mapFrag.getMapAsync(this);
        }
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    private void createMapFragmentAndInflate() {
        Criteria criteria = new Criteria();

        provider = locationManager.getBestProvider(criteria, true);

        supportMapFragment = SupportMapFragment.newInstance();

        //lepnjenje fragmenta na view group
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.details_map, supportMapFragment).commit();

        supportMapFragment.getMapAsync(this);
    }

    private void showLocatonDialog() {
        if (alertDialog == null) {
            alertDialog = new LocationDialog(getActivity()).prepareDialog(1);
        } else {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        }

        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();

        createMapFragmentAndInflate();

        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean wifi = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(!gps && !wifi){
            showLocatonDialog();
        }else{
            if(checkLocationPermission()){
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    //Request location updates: - pokretanje procesa lociranja
                    locationManager.requestLocationUpdates(provider, 180, 100, this);
                   // Toast.makeText(getContext(), "ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                } else if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    //Request location updates:
                    locationManager.requestLocationUpdates(provider, 180, 100, this);
                    //Toast.makeText(getContext(), "ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle(getText(R.string.alert_title))
                        .setMessage(getText(R.string.alert_message))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        //ovo je callback koji ce biti pozvan kada se proces trazenja permisija zavrsi
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(provider, 180, 100, this);
                    }

                } else if (grantResults.length > 0
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(provider, 180, 100, this);
                    }

                }
                return;
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        Location location = null;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.map_style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        if (checkLocationPermission()) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                if(provider !=null){
                    location = locationManager.getLastKnownLocation(provider);
                }
            } else if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                if(provider !=null) {
                    location = locationManager.getLastKnownLocation(provider);
                }
            }
        }

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getId().equals(myMarker.getId())){
                    marker.showInfoWindow();
                    return true;
                }
                if(grMap.size() > 0){
                    for(GamingRoomMap gr : grMap){
                        if(marker.getId().equals(gr.getMarkerId())) {
                            openActivity(gr);
                            return true;
                        }
                    }
                }
                return true;
            }
        });

        if(location != null){
            addMarker(location);
            reloadData(location);
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        if(map != null){
            clearMap();
            grMap.clear();
            grLocation.clear();
            addMarker(location);
            reloadData(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getContext(), "UPALILI STE PROVAJDER HVALA", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getContext(), "Lokacija iskljucena.", Toast.LENGTH_SHORT).show();
    }

    public void reloadData(final Location location){
        int ratio = 0; //in meters
        prefs = getContext().getSharedPreferences("pref", getContext().MODE_PRIVATE);
        editor = getContext().getSharedPreferences("pref", getContext().MODE_PRIVATE).edit();
        String distance = prefs.getString("distance", "");
        if(distance.equals("")){
            editor.putString("distance","500m");
            editor.apply();
            ratio = 500;
        }else{
            if(distance.equals("500m")){
                ratio = 500;
            }else{
                ratio = Integer.parseInt(distance.substring(0,distance.length() - 2));
                ratio = ratio * 1000;
            }
        }
        final List<GamingRoom> grooms = GamingRoomSqlSync.getData(getActivity());
            for(GamingRoom gr : grooms) {

                float[] result = new float[1];
                Location.distanceBetween(location.getLatitude(), location.getLongitude(), gr.getLat(), gr.getLon(), result);
                float distanceInMeters = result[0];
                if (distanceInMeters < ratio) {
                    addMarkerGamingRoom(gr);
                }
            }
    }


    private void addMarker(Location location) {
        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

        if (myMarker != null) {
            myMarker.remove();
        }


        myMarker = map.addMarker(new MarkerOptions()
                .title((String) getText(R.string.myLoaction))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .position(loc));
        myMarker.setFlat(true);

        myMarker.isVisible();
        myMarker.showInfoWindow();


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(loc).zoom(14).build();
        map.moveCamera(CameraUpdateFactory.newLatLng(loc));

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void addMarkerGamingRoom(GamingRoom gr){
        LatLng loc = new LatLng(gr.getLat(), gr.getLon());
        GamingRoomMap grm = new GamingRoomMap(gr);
        Marker m = map.addMarker(new MarkerOptions()
                .title(gr.getName())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .position(loc));
        m.setFlat(true);

        grLocation.add(m);
        grm.setMarkerId(m.getId());
        grMap.add(grm);
    }

    private void openActivity(GamingRoomMap gr){
        Intent intent = new Intent(getContext(), GameRoomActivity.class);
        intent.putExtra("gameRoomId", gr.getId());
        getContext().startActivity(intent);
    }

    void clearMap(){
        for(Marker m : grLocation){
            m.remove();
        }
    }
}
