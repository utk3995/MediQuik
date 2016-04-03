package com.example.jit.test1;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Docmap extends FragmentActivity {
    double clat,clon;
    private GoogleMap mMap;
    String note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docmap);
        Bundle bundle = getIntent().getExtras();
        note = bundle.getString("bg");
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        ParseUser user = ParseUser.getCurrentUser();
        clat=user.getDouble("lat");
        clon=user.getDouble("long");

        mMap.addMarker(new MarkerOptions().position(new LatLng(clat, clon)).title("Me"));

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> Users, ParseException e) {
                if (e == null) {
                    String[] TO = new String[100];
                    String[] CC = {""};
                    int i = 0;
                    for (ParseObject product : Users) {
                        double lat = (double) product.get("lat");
                        double lon = (double) product.get("long");
                        String ishe = product.get("doc").toString();

                        if (ishe.equalsIgnoreCase("true") && distance(clat, clon, lat, lon, "K") <= 30 && clat != lat) {
                            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(product.get("username").toString()).snippet(product.get("email").toString()));
                            TO[i++] = product.get("email").toString();
                        }

                        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                            @Override
                            public View getInfoWindow(Marker arg0) {
                                return null;
                            }

                            @Override
                            public View getInfoContents(Marker marker) {

                                Context context = getApplicationContext();

                                LinearLayout info = new LinearLayout(context);
                                info.setOrientation(LinearLayout.VERTICAL);

                                TextView title = new TextView(context);
                                title.setTextColor(Color.BLACK);
                                title.setGravity(Gravity.CENTER);
                                title.setTypeface(null, Typeface.BOLD);
                                title.setText(marker.getTitle());

                                TextView snippet = new TextView(context);
                                snippet.setTextColor(Color.GRAY);
                                snippet.setText(marker.getSnippet());

                                info.addView(title);
                                info.addView(snippet);

                                return info;
                            }
                        });

                        LatLng latLng = new LatLng(clat, clon);
                        float zoomLevel = (float) 8.0; //This goes up to 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
                    }

                    // MAIL

                    Log.i("Send email", "");
                    ParseUser user=ParseUser.getCurrentUser();
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MediQuik - EMERGENCY DOCTOR NEEDED");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, " Name : "+user.get("username").toString()+"\n Phone Number : "+user.get("phone").toString()+"\n Note :\n\n"+note+"\n");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(Docmap.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                    // INFO WINDOW
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mMap = mapFragment.getMap();
                    //INF END
                    // CLICK LISTENER
                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {

                            Intent intent = new Intent(

                                    Docmap.this,
                                    profilepage.class);
                            intent.putExtra("email", marker.getSnippet());
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.d("msg", "f");
                }
            }

        });

    }
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
