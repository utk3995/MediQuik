package com.example.jit.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

/**
 * Created by jit on 02-04-2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, homepage.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(MainActivity.this,
                    Startpage.class);
            startActivity(intent);
            finish();
        }

    }
}

