package com.example.jit.test1;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class registration  extends AppCompatActivity {
    EditText name,phone,email,password;
    Button start;
    String user1,pass,email1,phone1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        start = (Button) findViewById(R.id.start);


    }

    public void clickoff(View view){

        user1 = name.getText().toString();
        pass = password.getText().toString();
        email1 = email.getText().toString();
        phone1 = phone.getText().toString();


        if (user1.equals("") || pass.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please complete the sign up form",
                    Toast.LENGTH_LONG).show();

        } else {
            // Save new user data into Parse.com Data Storage
            ParseUser user = new ParseUser();
            user.setUsername(user1);
            user.setPassword(pass);
            user.setEmail(email1);
            user.put("phone", phone1);
            user.put("Age", "");
            user.put("sex", "");
            user.put("address", "");
            user.put("bloodgroup","");
            user.put("dob","");
            user.put("degree","");
            user.put("spec","None");
            user.put("bv","false");
            user.put("doc","false");
            user.put("lat",10);
            user.put("long",-10);

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(),
                                "Successfully Signed up",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Sign up Error", Toast.LENGTH_LONG)
                                .show();
                        e.printStackTrace();
                    }
                }
            });


        }
        Intent intent = new Intent(
                registration.this,
                continued.class);
        startActivity(intent);
        finish();
    }

}
