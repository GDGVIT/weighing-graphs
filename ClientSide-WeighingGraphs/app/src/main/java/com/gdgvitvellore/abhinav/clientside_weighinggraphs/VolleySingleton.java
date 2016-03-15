package com.gdgvitvellore.abhinav.clientside_weighinggraphs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {


    private SharedPreferences preferences;

    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MainApplication.getAppContext());
        preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
    }

    public static VolleySingleton getInstance() {
        if (instance == null) {
            instance = new VolleySingleton();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }
}
