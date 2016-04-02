package com.example.jit.test1;

import android.app.Application;
import android.os.Message;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by jit on 02-04-2016.
 */
public class ParseApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "FQVhu2qB6oeILgOmMrGJBB9wxQFIe3XJHaDFLJkd";
    public static final String YOUR_CLIENT_KEY = "gLdE6VruiUimufUD5hf6XP7p0NS1EVJkEW20lnUM";
    @Override
    public void onCreate() {
        super.onCreate();
        //Parse.enableLocalDatastore(this);
       // ParseObject.registerSubclass(user.class);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);

    }
}
