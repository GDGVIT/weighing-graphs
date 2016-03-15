package com.gdgvitvellore.abhinav.clientside_weighinggraphs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class ChartActivity extends AppCompatActivity{

    RelativeLayout chartActivityLayout ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chartActivityLayout = (RelativeLayout) findViewById(R.id.activity_chart_relative) ;

        setContentView(R.layout.activity_chart);
        fetchData() ;

    }

    private void fetchData() {

        String API_URL = "http://url.temp" ;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        handleResponseData(response) ;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(MainApplication.TAG, "Failed to fetch Json" + error) ;
                Snackbar snackbar = Snackbar
                        .make(chartActivityLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);

                snackbar.show();

            }
        });

        VolleySingleton.getInstance().addToRequestQueue(jsonArrayRequest);

    }

    private void handleResponseData(JSONArray response) {


    }


}
