package com.example.jit.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by jit on 03-04-2016.
 */
public class profilepage1 extends AppCompatActivity {
    EditText name, age, sex, email1, phone, bg,donor,doc;
    Button find;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage1);
        Bundle b = new Bundle();

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        sex = (EditText) findViewById(R.id.sex);
        email1 = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        bg = (EditText) findViewById(R.id.bg);
        doc=(EditText) findViewById(R.id.doc);
        donor=(EditText) findViewById(R.id.donor);

        ParseUser obj = ParseUser.getCurrentUser();

        name.setText(obj.get("username").toString());
        age.setText(obj.get("Age").toString());
        sex.setText(obj.get("sex").toString());
        email1.setText(obj.get("email").toString());
        phone.setText(obj.get("phone").toString());
        bg.setText(obj.get("bloodgroup").toString());
        if(obj.get("doc").toString().equalsIgnoreCase("true"))
            doc.setText("Yes");
        else
            doc.setText("No");
        if(obj.get("bv").toString().equalsIgnoreCase("true"))
            donor.setText("Yes");
        else
            donor.setText("No");
    }
    public void change(View view){
        ParseUser obj = ParseUser.getCurrentUser();

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        sex = (EditText) findViewById(R.id.sex);
        email1 = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        bg = (EditText) findViewById(R.id.bg);

        obj.put("username",name.getText().toString());
        obj.put("Age", age.getText().toString());
        obj.put("sex", sex.getText().toString());
        obj.put("email", email1.getText().toString());
        obj.put("phone", phone.getText().toString());
        obj.put("bloodgroup", bg.getText().toString());

        Intent intent = new Intent(
                profilepage1.this,
                homepage.class);
        startActivity(intent);
        finish();
    }
}