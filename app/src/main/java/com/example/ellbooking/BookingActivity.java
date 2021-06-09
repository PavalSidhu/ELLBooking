package com.example.ellbooking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class BookingActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnHome;
    private Button btnSubmit;
    private TextView date;
    private int rowClicked;
    private  String bookingTime;

    String timeSlot[] = {"9:00 AM", "10:00 AM", "11:00 AM", "12:00 AM", "1:00 PM" , "2:00 PM", "3:00 PM", "4:00 AM"};
    String status[] = {"Available", "Available", "Available", "Available", "Available", "Available", "Available", "Available"};
    Boolean booked[] = {false, false, false, false, false, false, false, false};

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);

        btnBack = (Button) findViewById(R.id.bookingBackBtn);
        btnHome = (Button) findViewById(R.id.bookingHomeBtn);
        btnSubmit = (Button) findViewById(R.id.saveBookingData);
        date = (TextView) findViewById(R.id.BookingDate);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        MyAdapter myAdapter = new MyAdapter(this, timeSlot, status, booked);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent incomingIntent = getIntent();
        String dateString = incomingIntent.getStringExtra("date");

        date.setText(dateString);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < timeSlot.length; i++) {
                    CheckBox view = recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.booked);
                    if(view.isChecked() == true) {
                        rowClicked = i;
                    }
                }
                TextView timeView = recyclerView.findViewHolderForAdapterPosition(rowClicked).itemView.findViewById(R.id.timeSlot);
                if(rowClicked >= 0) {
                    bookingTime = timeView.getText().toString();
                    SaveBooking(bookingTime);
                }
                Log.d("firebase", String.valueOf(rowClicked));
            }
        });
    }

    private void SaveBooking(String timeSlots) {

//        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        HashMap<String, Object> map = new HashMap<>();
        map.put("timeSlot", "2:00 PM");
        map.put("status", "Booked");
        map.put("booked", "True");

        FirebaseDatabase.getInstance().getReference().child("bookings").child(user.getUid()).updateChildren(map);
    }


}
