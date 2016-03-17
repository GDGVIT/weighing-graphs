package com.gdgvitvellore.abhinav.clientside_weighinggraphs;

import android.content.Context;
import android.content.SharedPreferences;
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

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class ChartFragment extends android.app.Fragment{

    PtrFrameLayout chartActivityLayout ;
    SwipeRefreshLayout swipeRefreshLayout ;

        /**
         * SET RANGE
         * */
        private String typeString;
        private Integer startParam ;
        private Integer stopParam ;
        private Integer dateConstraint ;
        private Integer iterationValue = 1 ;

    private ChartElementsList chartElementsList = new ChartElementsList() ;


    private ValueLineSeries series ;
    private ValueLineChart mCubicValueLineChart ;

    private static final String TAG = "ChartFragment";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_chart, container, false) ;

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh) ;
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTimeConfig() ;
        fetchData() ;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData() ;
            }
        });
    }

    private void fetchData() {
        Log.d(TAG, "fetchData() called with: " + "");

        String API_URL = "http://gdgvit.cloudapp.net:5000/show" ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse() called with: " + "response = [" + response + "]");
                        handleResponseData(response) ;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "Failed to fetch Json" + error) ;
                Snackbar snackbar = Snackbar
                        .make(chartActivityLayout, "Failed to fetch Json", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    private void getTimeConfig() {

        /**
        * Write code to set params for different constraints - startParams, stopParams, iterationValue
         * and constraints.
        * */

        Calendar calendar = Calendar.getInstance();

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("DateData",Context.MODE_PRIVATE);

        int type = sharedPreferences.getInt("Type", 2) ;
        int getDate = sharedPreferences.getInt("DateSelected", calendar.get(Calendar.DATE)) ;
        int getMonth = sharedPreferences.getInt("MonthSelected", calendar.get(Calendar.MONTH)) ;

        switch (type){
            case 0:
                Snackbar snackbarInvalidSelection = Snackbar
                        .make(chartActivityLayout, "Please select x-axis params", Snackbar.LENGTH_LONG);

                snackbarInvalidSelection.show();
                break;
            case 1:
                dateConstraint = getDate ;
                startParam = 10 ;
                stopParam = 24 ;
                typeString = "hourly" ;
                break;
            case 2:
                startParam = getDate ;
                stopParam = getDate + 10 ;
                typeString = "daily" ;
                break;
            case 3:
                break;
        }




    }

    private void setDataInChart() {


        for(Integer i = startParam ; i<stopParam ; i+= iterationValue){
            for(ChartElementsList.ChartElement temp: chartElementsList.getChartElements()){

                String tempDate = temp.getDate() ;
                String tempTime = temp.getTime() ;

                tempTime.substring(11,12) ;
                tempDate.substring(8,9) ;


                switch (typeString){
                    case "hourly":
                        if(tempDate == dateConstraint.toString()){
                            if(Integer.parseInt(temp.getTime()) >= i
                                    && Integer.parseInt(temp.getTime()) < (i + iterationValue)) ;

                            AddPointToChart(temp.getAirQuality(), Integer.parseInt(tempTime)) ;
                        }
                        break;
                    case "daily":
                        if(Integer.parseInt(tempDate) >= i  &&
                                Integer.parseInt(tempDate) < i + iterationValue) {

                            AddPointToChart(temp.getAirQuality(), Integer.parseInt(tempDate));

                        }
                        break;
                    case "weekly":
                        break;


                }
            }
        }

        buildChart() ;

    }

    private void initChart(View view) {

        mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.cubiclinechart);

        series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

    }

    private void AddPointToChart(String data, Integer value) {
        series.addPoint(new ValueLinePoint(data, (float)value));
    }

    private void buildChart() {
        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }




    private void handleResponseData(String response) {

        Log.d(TAG, "handleResponseData() called with: " + "response = [" + response + "]");

            /*JSONObject jsonResponse= new JSONObject(response) ;
            String array = jsonResponse.getString("data") ;*/

            Gson gson = new Gson() ;

            ChartElementsList elementsList = gson.fromJson(response, ChartElementsList.class);

            for (int i = 0 ; i< elementsList.getChartElements().size() ; i++){

                Log.d(TAG, "handleResponseData: " + elementsList.getChartElements().get(i).getAirQuality());
                Log.d(TAG, "handleResponseData: " + elementsList.getChartElements().get(i).getDate());
                Log.d(TAG, "handleResponseData: " + elementsList.getChartElements().get(i).getTime());
                Log.d(TAG, "handleResponseData: " + elementsList.getChartElements().get(i).getWeight());
            }

            Log.d("TEST", "DATA" + elementsList) ;




    }


}
