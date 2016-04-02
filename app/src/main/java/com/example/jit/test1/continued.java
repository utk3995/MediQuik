package com.example.jit.test1;


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.parse.*;

import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;


public class continued extends AppCompatActivity {
    EditText age,sex,dob,bg,addr;
    Button done;
    String age1,sex1,dob1,bg1,addr1;
    double lat = -1,lon=-1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continued);
        age = (EditText) findViewById(R.id.age);
        sex = (EditText) findViewById(R.id.sex);
        dob = (EditText) findViewById(R.id.dob);
        bg = (EditText) findViewById(R.id.bg);
        addr = (EditText) findViewById(R.id.add);
        done = (Button) findViewById(R.id.done);

        // LOcation thingy

        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)getSystemService(context);

        Criteria crta = new Criteria();
        crta.setAccuracy(Criteria.ACCURACY_FINE);
        crta.setAltitudeRequired(false);
        crta.setBearingRequired(false);
        crta.setCostAllowed(true);
        crta.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(crta, true);

        // String provider = LocationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);

        locationManager.requestLocationUpdates(provider, 1000, 0, locationListener);
        //Location thingy ends

    }
    public void onclick(View view){
        age1 = age.getText().toString();
        sex1 = sex.getText().toString();
        addr1 = addr.getText().toString();
        bg1 = bg.getText().toString();
        dob1 = dob.getText().toString();

        ParseUser user = ParseUser.getCurrentUser();
        user.put("Age", age1);
        user.put("sex",sex1);
        user.put("address",addr1);
        user.put("bloodgroup",bg1);
        user.put("dob", dob1);
        user.put("lat",lat);
        user.put("long",lon);
        user.saveInBackground();

        Intent intent = new Intent(
                continued.this,
                motivation.class);
        startActivity(intent);
        finish();

    }
    // locn thingy cnt
    private final LocationListener locationListener = new LocationListener()
    {

        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    };
    private void updateWithNewLocation(Location location) {


        if(location!=null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
        } else {
            Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();


        }
    }

    // locn thingy ends
}
