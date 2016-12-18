package anuson.komkid.permitgeographypro;

import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

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
    }//Mian


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

        LatLng latLng = new LatLng(13.842958, 100.491554);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

        try {

            SynFarmer synFarmer = new SynFarmer(MapsActivity.this);
            synFarmer.execute();
            String s = synFarmer.get();
            Log.d("18DecV2","JSON==>" + s);

            JSONArray jsonArray = new JSONArray(s);
            String[] titleString = new String[jsonArray.length()];
            String[] typeStrings = new String[jsonArray.length()];
            double[] latDoubles = new double[jsonArray.length()];
            double[] lngDoubles = new double[jsonArray.length()];

            for(int i=0;i<jsonArray.length();i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                titleString[i] = jsonObject.getString("mem_farm_name");
                typeStrings[i] = jsonObject.getString("mem_farm_type");
                latDoubles[i] = Double.parseDouble(jsonObject.getString("mem_farm_latitude"));
                lngDoubles[i] = Double.parseDouble(jsonObject.getString("mem_farm_longtitude"));

                LatLng latLng1 = new LatLng(latDoubles[i],lngDoubles[i]);
                mMap.addMarker(new MarkerOptions()
                        .position(latLng1)
                        .title(titleString[i])
                        .snippet(typeStrings[i]));
            }//for


        }catch (Exception e){
            Log.d("18DecV2","e ==>" + e.toString());
        }//try

    }//onMapReady
}//Main Class
