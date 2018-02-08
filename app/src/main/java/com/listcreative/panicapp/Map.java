package com.listcreative.panicapp;

/**
 * Created by Mushlihun on 08/02/2018.
 */


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.logging.Handler;

//import com.listcreative.panicapp.models.rumahsakit;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker myMarker;
    private TextView txt_rs;
    private TextView txt_time;
    int a = 0;
    String choice;

    private double[] lg = {-6.171842, -6.173485, -6.174971, -6.175888, -6.177420, -6.177607, -6.177623, -6.177387};
    private double[] lt = {106.821695, 106.821480, 106.820860, 106.820366, 106.819430, 106.819315, 106.819166, 106.819095};
    private String[] time = {"12 menit", "10 menit", "9 menit", "7 menit", "5 menit", "3 menit", "1 menit", "0 menit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent i = getIntent();
        choice = i.getStringExtra("choice");
        txt_rs = (TextView)findViewById(R.id.txt_status);
        txt_time = (TextView)findViewById(R.id.txt_time);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txt_rs.setText(choice+" on the way");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Jakarta, Bogor, and move the camera.
        getData();
    }

    public void getData(){
        int i = 1;
        final MarkerOptions marker = new MarkerOptions().position(new LatLng(-6.177387, 106.819095)).title("Your Location");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.myloc));
        mMap.addMarker(marker).setTag(i);
        LatLng jkt = new LatLng(-6.177387, 106.819095);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jkt));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14.5f));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });

        final MarkerOptions marker2 = new MarkerOptions();
        if (choice.equals("Police")) {
            marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.police_b));
        } else {
            marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_rs));
        }
        marker2.position(new LatLng(lg[0], lt[0])).title(choice);
        myMarker = mMap.addMarker(marker2);
        myMarker.setTag(0);
        final android.os.Handler handler = new android.os.Handler();
        Runnable task = new Runnable() {
            @Override
            public void run() {
//                if (a>0) {
                Log.d("loop", " on");
                myMarker.setPosition(new LatLng(lg[a], lt[a]));
                txt_time.setText(time[a]);
                handler.postDelayed(this, 2000);
                if (a == 7) {
                    handler.removeCallbacks(this);
                    txt_rs.setText(choice+" has arrived at the location");
                    Toast.makeText(Map.this, choice+" has arrived at your location", Toast.LENGTH_SHORT).show();
                }
//                }
                a++;
            }
        };
        handler.post(task);
//        Api api = new Api();
//        api.getDataRS(Map.this, new Api.VolleyCallback() {
//            @Override
//            public void onSuccess(final ArrayList<rumahsakit> result) {
//                for (int i=0; i<result.size(); i++){
//                    MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(result.get(i).getLat()), Double.parseDouble(result.get(i).getLang()))).title(result.get(i).getNama());
//                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_rs));
//                    mMap.addMarker(marker).setTag(i);
//                    LatLng jkt = new LatLng(-6.211980, 106.845987);
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(jkt));
//                    mMap.moveCamera(CameraUpdateFactory.zoomTo(12.0f));
//                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                        @Override
//                        public boolean onMarkerClick(Marker marker) {
//                            int position = (int)(marker.getTag());
//                            txt_rs.setText(result.get(position).getAlamat());
//                            //Using position get Value from arraylist
//                            return false;
//                        }
//                    });
//                }
//            }
//        });
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        if (marker.equals(myMarker)){
//            txt_rs.setText();
//        }
//        return false;
//    }
}
