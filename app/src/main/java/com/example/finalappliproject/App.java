package com.example.finalappliproject;

import android.app.Application;

import com.example.finalappliproject.Utilitis.ImageLoader;


public class App extends Application{
    public void onCreate() {
        super.onCreate();
        ImageLoader.initImageLoader(this);
    }
}