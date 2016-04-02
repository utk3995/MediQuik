package com.example.jit.test1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jit on 03-04-2016.
 */
public class profilepage extends AppCompatActivity {
    TextView name,age,sex,email1,phone,bg,spec,doc,donor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);
        Bundle b = new Bundle();

        name=(TextView) findViewById(R.id.name);
        age=(TextView) findViewById(R.id.age);
        sex=(TextView) findViewById(R.id.sex);
        email1=(TextView) findViewById(R.id.email);
        phone=(TextView) findViewById(R.id.phone);
        bg=(TextView) findViewById(R.id.bg);
        spec=(TextView) findViewById(R.id.spec);
        doc=(TextView) findViewById(R.id.doc);
        donor=(TextView) findViewById(R.id.donor);


        b = getIntent().getExtras();
        String email = b.getString("email");


        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("email", email);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> Users, ParseException e) {
                if (e == null) {
                    if(Users.size()>0) {
                        ParseObject obj = Users.get(0);

                        name.setText(obj.get("username").toString());
                        age.setText(obj.get("Age").toString());
                        sex.setText(obj.get("sex").toString());
                        email1.setText(obj.get("email").toString());
                        phone.setText(obj.get("phone").toString());
                        bg.setText(obj.get("bloodgroup").toString());
                        spec.setText(obj.get("spec").toString());
                        if(obj.get("doc").toString().equalsIgnoreCase("true"))
                            doc.setText("Yes");
                        else
                            doc.setText("No");
                        if(obj.get("bv").toString().equalsIgnoreCase("true"))
                            donor.setText("Yes");
                        else
                            donor.setText("No");
                    }
                } else {
                    Log.d("msg1", "f");
                }
            }
        });
    }
    public void bth(View view){
        Intent intent = new Intent(

                profilepage.this,
                Donormap.class);
        startActivity(intent);
        finish();
    }
    public void call(View view){
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+phone.getText().toString()));
            startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Call", "Call failed", e);
        }
    }
}