package com.example.jit.test1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.parse.*;

import java.util.ArrayList;
import java.util.List;


public class motivation extends AppCompatActivity {
    Button y,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.motivation);
        y= (Button) findViewById(R.id.yes);
        n= (Button) findViewById(R.id.no);
    }
    public void yes(View view){
        ParseUser user=ParseUser.getCurrentUser();
        user.put("bv","true");
        user.saveInBackground();
        Intent intent = new Intent(
                motivation.this,
                doctor.class);
        startActivity(intent);
    }
    public void no(View view){
        Intent intent = new Intent(
                motivation.this,
                doctor.class);
        startActivity(intent);
    }
}