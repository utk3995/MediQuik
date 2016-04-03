package com.example.jit.test1;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.parse.ParseUser;


public class homepage  extends AppCompatActivity {
    Button don,reqb,vdoc,fdoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        don = (Button) findViewById(R.id.don);
        reqb = (Button) findViewById(R.id.reqblood);
        vdoc = (Button) findViewById(R.id.vdoc);
        fdoc = (Button) findViewById(R.id.fdoc);
    }
    public void fdoc(View view){
        Intent intent = new Intent(
                homepage.this,
                fdoc.class);
        startActivity(intent);
    }
    public void vdoc(View view){
        Intent intent = new Intent(
                homepage.this,
                voldoc.class);
        startActivity(intent);
    }
    public void donor (View view){
        Intent intent = new Intent(
                homepage.this,
                donor.class);
        startActivity(intent);
    }

    public void reqblood(View view){
        Intent intent = new Intent(
                homepage.this,
                reqblood.class);
        startActivity(intent);
    }
    public void pp(View view){
        Intent intent = new Intent(
                homepage.this,
                profilepage1.class);
        startActivity(intent);
    }
    public void logout(View view){
        ParseUser.logOut();
        Intent intent = new Intent(
                homepage.this,
                Startpage.class);
        startActivity(intent);
    }
}
