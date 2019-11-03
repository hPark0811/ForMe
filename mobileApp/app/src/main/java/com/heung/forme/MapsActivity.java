package com.heung.forme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.heung.forme.custom.APIManager;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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
        Map<String, Double> longlat;

        try {
            longlat = APIManager.getClosestBranch(MainActivity.currentUserID);
        } catch (Exception e) {
            System.out.println("ERROR GETTING ADDRESS");
            longlat = new HashMap<String, Double>(){
                {
                    put("customerLat", 43.655568);
                    put("customerLong", -79.385236);
                    put("branchLat", 43.650864);
                    put("branchLong", -79.386755);
                }
            };
        }

        mMap = googleMap;
        mMap.setMinZoomPreference(10f);
        mMap.setMaxZoomPreference(25f);

        // Add a marker in Sydney and move the camera
        LatLng branch = new LatLng(longlat.get("branchLat"), longlat.get("branchLong"));
        System.out.println("branch location:" + longlat.get("branchLat") + " and " +  longlat.get("branchLong"));
        LatLng home = new LatLng(longlat.get("customerLat"), longlat.get("customerLong"));
        System.out.println("home location:" + longlat.get("customerLat") + " and " +  longlat.get("customerLong"));
        mMap.addMarker(new MarkerOptions().position(branch).title("Nearest Branch").icon(
                BitmapDescriptorFactory.fromBitmap(resizeMapIcons("td_canada_logo", 100, 100))
        ));
        mMap.addMarker(new MarkerOptions().position(home).title("Home").icon(
                BitmapDescriptorFactory.fromBitmap(resizeMapIcons("home_logo_icon", 100, 100))
        ));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(branch, 15f));
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
}
