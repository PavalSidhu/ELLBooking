package com.example.ellbooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnHidden;

    private static final String TAG = "CalendarActivity";
    private CalendarView calendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        btnBack = (Button) findViewById(R.id.calendarBackBtn);

        btnHidden = (Button) findViewById(R.id.hiddenDate);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, BookingActivity.class);
                intent.putExtra("date", "18/06/2021");
                startActivity(intent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("bookingDate", String.valueOf(month));
                String monthString = "";
                month = month + 1;
                if(month< 10) {
                    monthString = "0" + month;
                } else {
                    monthString = "" + (month);
                }
                String date = dayOfMonth + "/" + monthString + "/" + year;

                Intent intent = new Intent(CalendarActivity.this, BookingActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }


}
