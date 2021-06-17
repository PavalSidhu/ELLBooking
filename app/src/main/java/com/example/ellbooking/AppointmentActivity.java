package com.example.ellbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppointmentActivity extends AppCompatActivity {

    private Button btnBack;
    private Button btnCancelBooking;
    private TextView appointmentDateText;
    private String date;
    private String timeSlot;
    private TextView zoomTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        btnBack = (Button) findViewById(R.id.appointmentBackBtn);
        btnCancelBooking = (Button) findViewById(R.id.cancelBooking);
        appointmentDateText = (TextView) findViewById(R.id.appointmentDate);
        zoomTextView = (TextView) findViewById(R.id.zoomLink);

        checkBookingDate();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCancelBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBooking();

//                Intent intent = new Intent(AppointmentActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void cancelBooking() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        Query dateQuery = FirebaseDatabase.getInstance().getReference("bookings").child(user.getUid());


        dateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("bookingValue", String.valueOf(snapshot.getChildrenCount()));
//                Log.d("bookingValue", String.valueOf(snapshot.getValue()));
//
//                Log.d("bookingValue", String.valueOf(snapshot.getValue()));

                if(appointmentDateText.getText().toString().equals("Appointment Date")){
                    AlertDialog alertDialog1 = new AlertDialog.Builder(AppointmentActivity.this).create();
                    alertDialog1.setTitle("Canceling Error");
                    alertDialog1.setMessage("No Booking exists");
                    alertDialog1.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog1.show();
                } else {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        Log.d("bookingValue", String.valueOf(userSnapshot.getValue()));
                        snapshot.getRef().removeValue();
                    }

                    Intent intent = new Intent(AppointmentActivity.this, MainActivity.class);
                    startActivity(intent);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "unlucky");
            }
        });

    }

    private void checkBookingDate() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query dateQuery = FirebaseDatabase.getInstance().getReference("bookings").child(user.getUid());

        dateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("bookingValue", String.valueOf(snapshot.getChildrenCount()));
//                Log.d("bookingValue", String.valueOf(snapshot.getValue()));
//
//                Log.d("bookingValue", String.valueOf(snapshot.getValue()));

                if(snapshot.exists()) {
                    Log.d("bookingValue", "exists");
                    date = String.valueOf(snapshot.child("date").getValue());
                    timeSlot = String.valueOf(snapshot.child("timeSlot").getValue());
                    appointmentDateText.setText(date + " " + timeSlot);
                    checkBookingTime();
                } else {
                    Log.d("bookingValue", "does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "unlucky");
            }
        });
    }

    private void checkBookingTime() {
        int index = 99;
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =  mdformat.format(currentTime.getTime());

        Log.d("currentTime", strDate);


        if(timeSlot.contains("9:00 AM")) {
            index = 0;

        } else if(timeSlot.contains("10:00 AM")) {
            index = 1;

        } else if(timeSlot.contains("11:00 AM")) {
            index = 2;

        } else if(timeSlot.contains("12:00 PM")) {
            Log.d("check", "contains 12");
            index = 3;

        } else if(timeSlot.contains("1:00 PM")) {
            index = 4;

        } else if(timeSlot.contains("2:00 PM")) {
            index = 5;

        } else if(timeSlot.contains("3:00 PM")) {
            index = 6;

        } else if(timeSlot.contains("4:00 PM")) {
            index = 7;

        }

        Date d1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String nineAm = mdformat.format(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        String tenAm = mdformat.format(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        String elevenAm = mdformat.format(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        String twelvePm = mdformat.format(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        String onePm = mdformat.format(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        String twoPm = mdformat.format(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        String threePm = mdformat.format(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        String fourPm = mdformat.format(calendar.getTime());

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        Log.d("bookingDate", thisDate);


        if(thisDate.equals(date)) {
            Log.d("bookingDate", "dates are equal");


            if(strDate.compareTo(nineAm)>= 0 && index == 0) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);

            } else if(strDate.compareTo(tenAm)>= 0 && index == 1) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);

            } else if(strDate.compareTo(elevenAm)>= 0 && index == 2) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);

            } else if(strDate.compareTo(twelvePm)>= 0 && index == 3) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                Log.d("blah", "contains 12pm");
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);


            } else if(strDate.compareTo(onePm)>= 0 && index == 4) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);


            } else if(strDate.compareTo(twoPm)>= 0 && index == 5) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);


            } else if(strDate.compareTo(threePm)>= 0 && index == 6) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);


            } else if(strDate.compareTo(fourPm)>= 0 && index == 7) {
                zoomTextView.setMovementMethod(LinkMovementMethod.getInstance());
                zoomTextView.setLinksClickable(true);
                zoomTextView.setLinkTextColor(Color.BLUE);


            }


        } else {
            Log.d("bookingDate", "dates are not equal");
            Log.d("date1", date);
            Log.d("date2", thisDate);
        }

    }
}