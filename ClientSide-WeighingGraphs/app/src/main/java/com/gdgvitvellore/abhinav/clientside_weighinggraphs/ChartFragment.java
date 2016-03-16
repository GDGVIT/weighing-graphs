package com.gdgvitvellore.abhinav.clientside_weighinggraphs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class ChartFragment extends android.app.Fragment{

    PtrFrameLayout chartActivityLayout ;
    SwipeRefreshLayout swipeRefreshLayout ;

        /**
         * SET RANGE
         * */
        private String type ;
        private Integer startParam ;
        private Integer stopParam ;
        private Integer dateConstraint ;
        private Integer iterationValue;

    private List<ChartElement> elementsList ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_chart, container, false) ;

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh) ;

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchData() ;
            }
        });

        getTimeConfig() ;
        setDataInChart() ;

    }

    private void fetchData() {

        String API_URL = "http://gdgvit.cloudapp.net:5000/show" ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        handleResponseData(response) ;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(MainApplication.TAG, "Failed to fetch Json" + error) ;

                Snackbar snackbar = Snackbar
                        .make(chartActivityLayout, "Failed to fetch Json", Snackbar.LENGTH_LONG);

                snackbar.show();

            }
        });

        VolleySingleton.getInstance().addToRequestQueue(stringRequest);

    }


    private void getTimeConfig() {

        /**
        * Write code to set params for different constraints - startParams, stopParams, iterationValue
         * and constraints.
        * */


    }

    private void setDataInChart() {
        for(Integer i=startParam ; i<stopParam ; i+= iterationValue){
            for(ChartElement temp: elementsList){

                String tempDate = temp.getDate() ;
                String tempTime = temp.getTime() ;

                tempTime.substring(8,9) ;
                tempDate.substring(5,6) ;


                switch (type){
                    case "hourly":
                        if(tempDate == dateConstraint.toString()){
                            if(Integer.parseInt(temp.getTime()) >= startParam
                                    && Integer.parseInt(temp.getTime()) < (startParam+iterationValue)) ;

                            AddPointToChart() ;
                        }
                        break;
                    case "daily":
                        if(Integer.parseInt(tempDate) >= startParam &&
                                Integer.parseInt(tempDate) < startParam+iterationValue) {

                            AddPointToChart();

                        }
                        break;
                    case "weekly":


                        break;


                }
            }
        }

    }

    private void AddPointToChart() {


    }


    int i = 1;

    private void handleResponseData(String response) {

        try {
            JSONObject jsonResponse= new JSONObject(response) ;
            String array = jsonResponse.getString("data") ;
            Gson gson = new Gson() ;
            elementsList = gson.fromJson(array, new TypeToken<List<ChartElement>>(){}.getType());

            Log.d("TEST", "DATA" + elementsList) ;


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("TEST_FAIL", "FAIL") ;
        }



    }


}
