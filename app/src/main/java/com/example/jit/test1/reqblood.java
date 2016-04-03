package com.example.jit.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;


public class reqblood extends AppCompatActivity {
    EditText bg,unit,addr;
    Button find;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reqblood);
        bg = (EditText) findViewById(R.id.bg);
        unit = (EditText) findViewById(R.id.units);
        addr = (EditText) findViewById(R.id.loc);
        find = (Button) findViewById(R.id.find);
    }

    public void find(View view){

        Bundle b= new Bundle();
        b.putString("bg",bg.getText().toString());
        b.putString("unit", unit.getText().toString());
        b.putString("addr",addr.getText().toString());

        Intent intent = new Intent(

                reqblood.this,
                Donormap.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
