package com.example.ellbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button btnGoCalender;
    private Button btnGoUser;
//    private FirebaseAnalytics analytics;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        analytics = FirebaseAnalytics.getInstance(this);

        btnGoCalender = (Button) findViewById(R.id.btnGoCalender);
        btnGoUser = (Button) findViewById(R.id.btnGoUserInfo);

        Intent incomingIntent = getIntent();
        email = incomingIntent.getStringExtra("email");

        btnGoCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                analytics.logEvent("button_clicked", null);
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        btnGoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }

}