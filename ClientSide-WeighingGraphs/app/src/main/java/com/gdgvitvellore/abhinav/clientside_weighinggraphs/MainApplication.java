package com.gdgvitvellore.abhinav.clientside_weighinggraphs;

import android.app.Application;
import android.content.Context;

public class MainApplication  extends Application{

    private static MainApplication mainApplication ;
    public static final String TAG = MainApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();

        mainApplication = this ;

    }

    public static Context getAppContext(){
        return mainApplication.getApplicationContext() ;
    }


}
