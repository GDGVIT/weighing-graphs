package com.gdgvitvellore.abhinav.clientside_weighinggraphs;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.LogManager;

public class DateFragment  extends Fragment{

    private DatePickerDialog datePickerDialog;

    private SimpleDateFormat dateFormatter;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int selectedDay;
    private int selectedMonth;
    private int selectedYear;

    private Spinner spinner ;

    private SharedPreferences sharedPreferences ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date, container, false) ;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view) ;
        initSpinner();

    }

    private void init(View view) {

        spinner = (Spinner) view.findViewById(R.id.spinner);
        sharedPreferences = getActivity().getSharedPreferences("DateData", Context.MODE_PRIVATE) ;
    }

    private void initSpinner() {


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                if(position!=0){
                    showPopup(position);

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void showPopup(final int position) {


        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                selectedDay = dayOfMonth;
                selectedMonth = monthOfYear;
                selectedYear = year;

                Toast.makeText(getActivity(),"Date Set",Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("type", position);
                editor.putInt("selectedDay", selectedDay) ;
                editor.putInt("selectedMonth", selectedMonth) ;
                editor.commit();

                Log.d("SHARED PREFERENCES", "type: " + position + " selectedDay: " + selectedDay
                        + " selectedMonth: " + selectedMonth) ;


            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
