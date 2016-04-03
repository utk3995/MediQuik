package com.example.jit.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class fdoc  extends AppCompatActivity {
    Button find;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fdoc);
        find = (Button) findViewById(R.id.find);
        note = (EditText) findViewById(R.id.note);

    }
    public void find(View view){
        Bundle b= new Bundle();
        b.putString("bg", note.getText().toString());
        Intent intent = new Intent(

                fdoc.this,
                Docmap.class);
        intent.putExtras(b);
        startActivity(intent);

    }
}
