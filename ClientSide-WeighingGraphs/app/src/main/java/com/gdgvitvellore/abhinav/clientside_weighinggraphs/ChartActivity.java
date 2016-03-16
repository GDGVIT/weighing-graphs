package com.gdgvitvellore.abhinav.clientside_weighinggraphs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class ChartActivity extends android.app.Fragment{

    PtrFrameLayout chartActivityLayout ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chart, container, false) ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chartActivityLayout = (PtrFrameLayout) view.findViewById(R.id.store_house_ptr_frame) ;

        fetchData() ;


    }

    Snackbar snackbar = Snackbar
            .make(chartActivityLayout, "Failed to fetch Json", Snackbar.LENGTH_LONG);


    private void fetchData() {

        String API_URL = "http://gdgvit.cloudapp.net:5000/show" ;

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

                snackbar.show();

            }
        });

        VolleySingleton.getInstance().addToRequestQueue(jsonArrayRequest);

    }

    private void handleResponseData(JSONArray response) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try{
            Date date = sdf.parse("2014-02-15 05:18:08");
        }catch (ParseException pe){
            pe.printStackTrace();
        }

    }


}
