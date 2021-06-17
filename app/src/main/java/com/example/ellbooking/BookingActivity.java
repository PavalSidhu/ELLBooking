package com.example.ellbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class BookingActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnHome;
    private Button btnSubmit;
    private TextView date;
    private int rowClicked;
    private  String bookingTime;
    boolean submitData;

    String timeSlot[] = {"9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM" , "2:00 PM", "3:00 PM", "4:00 PM"};
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


        Intent incomingIntent = getIntent();
        String dateString = incomingIntent.getStringExtra("date");

        date.setText(dateString);
        String selectedDate = date.getText().toString();
        Log.d("dateBeginning", selectedDate);
        checkBookingDate(selectedDate);

        MyAdapter myAdapter = new MyAdapter(this, timeSlot, status, booked);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
                if(rowClicked >= 0) {
                    checkUserBooking(dateString);
                }
                Log.d("firebase", String.valueOf(rowClicked));
            }
        });
    }

    private void SaveBooking(String timeSlot, String date) {

//        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Log.d("saveBooking", timeSlot);

        HashMap<String, Object> map = new HashMap<>();
        map.put("timeSlot", timeSlot);
        map.put("date", date);
        map.put("status", "Booked");
        map.put("booked", "True");

        FirebaseDatabase.getInstance().getReference().child("bookings").child(user.getUid()).updateChildren(map);
    }

    private void checkBookingDate(String date) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        Query dateQuery = FirebaseDatabase.getInstance().getReference("bookings").orderByChild("date").equalTo(date);


        dateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("bookingValue", String.valueOf(snapshot.getChildrenCount()));
//                Log.d("bookingValue", String.valueOf(snapshot.getValue()));
//
//                Log.d("bookingValue", String.valueOf(snapshot.getValue()));

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    Log.d("bookingValue", String.valueOf(userSnapshot.getValue()));
                    Log.d("booking1", String.valueOf(userSnapshot.child("timeSlot").getValue()));
//                    Log.d("bookingValue", String.valueOf(userSnapshot.child("timeSlot").getValue()));

                    setBookingData(String.valueOf(userSnapshot.child("timeSlot").getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "unlucky");
            }
        });

    }

    private void setBookingData(String time) {

        if(time.equals(timeSlot[0])) {
            status[0] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(0).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(0).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        } else if(time.equals(timeSlot[1])) {
            status[1] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(1).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(1).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        } else if(time.equals(timeSlot[2])) {
            status[2] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(2).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(2).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        } else if(time.equals(timeSlot[3])) {
            status[3] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(3).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(3).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        } else if(time.equals(timeSlot[4])) {
            status[4] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(4).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(4).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        } else if(time.equals(timeSlot[5])) {
            status[5] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(5).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(5).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        } else if(time.equals(timeSlot[6])) {
            status[6] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(6).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(6).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        } else if(time.equals(timeSlot[7])) {
            status[7] = "Booked";
            TextView statusView = recyclerView.findViewHolderForAdapterPosition(7).itemView.findViewById(R.id.status);
            statusView.setText("Booked");
            CheckBox checkBox = recyclerView.findViewHolderForAdapterPosition(7).itemView.findViewById(R.id.booked);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        }
    }

    private void checkUserBooking(String dateString) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Query dateQuery = FirebaseDatabase.getInstance().getReference("bookings");


        dateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(user.getUid())) {
                    AlertDialog alertDialog = new AlertDialog.Builder(BookingActivity.this).create();
                    alertDialog.setTitle("Booking Error");
                    alertDialog.setMessage("Cancel prior booking before selecting a new booking time");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    submitData = false;
                } else {
                    submitData = true;

                    TextView timeView = recyclerView.findViewHolderForAdapterPosition(rowClicked).itemView.findViewById(R.id.timeSlot);
                    bookingTime = timeView.getText().toString();
                    SaveBooking(bookingTime, dateString);
                    Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "unlucky");
            }
        });


    }

}
