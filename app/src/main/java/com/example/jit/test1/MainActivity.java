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
        final boolean _active = true;
        final int _splashTime = 10000;

        // WAIT
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while((waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();

                }
            }
        };
        splashTread.start();

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

