package com.example.jit.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.parse.*;

import java.util.ArrayList;
import java.util.List;


public class Startpage extends AppCompatActivity {
    String usernametxt;
    String passwordtxt;

    EditText username,password;
    Button loginbutton,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        loginbutton = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
    }

    public void onclick(View view){
        usernametxt = username.getText().toString();
        passwordtxt = password.getText().toString();

        ParseUser.logInInBackground(usernametxt, passwordtxt,
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // If user exist and authenticated, send user to Homepage.class
                            Intent intent = new Intent(
                                    Startpage.this,
                                    homepage.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Logged in",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "No such user exist, please signup",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void onclick1(View view){
        Intent intent = new Intent(
                Startpage.this,
                registration.class);
        startActivity(intent);
        finish();
    }
}
