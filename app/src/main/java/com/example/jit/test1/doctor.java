package com.example.jit.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

/**
 * Created by jit on 02-04-2016.
 */
public class doctor  extends AppCompatActivity {
    EditText degree, spec;
    String deg1,spec1;
    Button yes,nope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);
        degree = (EditText) findViewById(R.id.deg);
        spec = (EditText) findViewById(R.id.spec);
        yes = (Button) findViewById(R.id.yes);
        nope = (Button) findViewById(R.id.no);
    }
    public void volunteer(View view){
        deg1 = degree.getText().toString();
        spec1 = spec.getText().toString();
        ParseUser user= ParseUser.getCurrentUser();
        user.put("doc","true");
        user.put("degree",deg1);
        user.put("spec",spec1);
        user.saveInBackground();
        Intent intent = new Intent(
                doctor.this,
                homepage.class);
        startActivity(intent);
        finish();
    }

    public void nope(View view){
        ParseUser user= ParseUser.getCurrentUser();
        user.put("doc","false");
        user.saveInBackground();
        Intent intent = new Intent(
                doctor.this,
                homepage.class);
        startActivity(intent);
        finish();
    }
}
