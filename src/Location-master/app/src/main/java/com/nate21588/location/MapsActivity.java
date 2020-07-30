package com.nate21588.location;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private String ADDRESS_STRING;
    private double LAT;
    private double LONG;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;
        Geocoder coder = new Geocoder(this);
        try{
            Bundle bundle = getIntent().getExtras();
            String address = bundle.getString("address");
            List<Address> addresses = coder.getFromLocationName(address, 2);
            if (address != null){
                LAT = addresses.get(0).getLatitude();
                LONG = addresses.get(0).getLongitude();
            }
        }
        catch(IOException ioe){
            Toast.makeText(this, "Unable to find this location," +
                    "are you sure it's correct?", Toast.LENGTH_SHORT).show();
        }
        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(LAT, LONG);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, 15.5f);
        mMap.addMarker(new MarkerOptions().position(location).title("Your location"));
        mMap.moveCamera(update);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}
