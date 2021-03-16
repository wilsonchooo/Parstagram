package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("s77Ju0Em41DgKgl8CfPKJZnnGCGqOzCh0ikBURa5")
                .clientKey("vfGWIDIUdvo4slOPySZgElJCQzChsspN1xLpigDM")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
