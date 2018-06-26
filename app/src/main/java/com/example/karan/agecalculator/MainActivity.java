package com.example.karan.agecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button button;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        button = findViewById(R.id.confirmButton);

        //Set max date to be 3 years prior
        int minAge = 3; //min age is 3 years old
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -minAge);
        datePicker.setMaxDate(cal.getTimeInMillis());

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                month++; //0 indexed
                String date = day + "/" + month + "/" + year;
                Log.d(TAG, "onSelectedDayChange: date : " + date);

                final int selectedDates[] = {day, month, year};

                button.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startCalc(selectedDates);
                    }
                });

            }
        });
    }

    private void startCalc(int[] selectedDates) {
        Intent intent = new Intent(this, CalculatedAgeActivity.class);
        intent.putExtra("dates", selectedDates);
        startActivity(intent);
    }
}