package com.gdgvitvellore.abhinav.clientside_weighinggraphs;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    }

    private void initSpinner() {


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                MainApplication.getAppContext(), R.array.items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        datePickerDialog = new DatePickerDialog(MainApplication.getAppContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                selectedDay=dayOfMonth;
                selectedMonth=monthOfYear;
                selectedYear=year;

                Toast.makeText(MainApplication.getAppContext(),"Date Set",Toast.LENGTH_SHORT).show();

            }
        }, mYear, mMonth, mDay);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                if(position!=0)
                    datePickerDialog.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
