package com.example.jit.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

/**
 * Created by jit on 02-04-2016.
 */
public class donor  extends AppCompatActivity {
    Button y,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor);
        y= (Button) findViewById(R.id.yes);
        n= (Button) findViewById(R.id.no);
    }
    public void yes(View view){
        ParseUser user=ParseUser.getCurrentUser();
        user.put("bv", "true");
        user.saveInBackground();
        Intent intent = new Intent(
                donor.this,
                homepage.class);
        startActivity(intent);
    }
    public void no(View view){
        ParseUser user=ParseUser.getCurrentUser();
        user.put("bv", "false");
        user.saveInBackground();
        Intent intent = new Intent(
                donor.this,
                homepage.class);
        startActivity(intent);
        finish();
    }
}