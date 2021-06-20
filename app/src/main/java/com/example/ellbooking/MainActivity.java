package com.example.ellbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ellbooking.View.MainView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable, MainView {

    private Button btnGoCalender;
    private Button btnGoUser;
    private Button btnSignOut;
    private Button btnGoAppointment;
//    private FirebaseAnalytics analytics;
    private String email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            showOriginalView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showOriginalView() throws IOException {
        setContentView(R.layout.activity_main);

//        analytics = FirebaseAnalytics.getInstance(this);

        btnGoCalender = (Button) findViewById(R.id.btnGoCalender);
        btnGoUser = (Button) findViewById(R.id.btnGoUserInfo);
        btnSignOut = (Button) findViewById(R.id.signOutMain);
        btnGoAppointment = (Button) findViewById(R.id.btnGoAppointment);

//        Intent incomingIntent = getIntent();
//        email = incomingIntent.getStringExtra("email");

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });


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

        btnGoAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
                startActivity(intent);
            }
        });
    }
}