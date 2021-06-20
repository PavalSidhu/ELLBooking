package com.example.ellbooking.Model;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ellbooking.Presenter.Booking;
import com.example.ellbooking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;

public class BookingModel implements Serializable, Booking {

    String timeSlotS;
    String dateS;
    String statusS;
    String bookedS;
    String timeSlot[] = {"9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM" , "2:00 PM", "3:00 PM", "4:00 PM"};
    String status[] = {"Available", "Available", "Available", "Available", "Available", "Available", "Available", "Available"};
    Boolean booked[] = {false, false, false, false, false, false, false, false};

    RecyclerView recyclerView;


    @Override
    public String getTimeSlot() {
        return timeSlotS;
    }

    @Override
    public String getDate() {
        return dateS;
    }

    @Override
    public String getStatus() {
        return statusS;
    }

    @Override
    public String getBooked() {
        return bookedS;
    }

    @Override
    public void SaveBooking(String timeSlot, String date) {
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

    @Override
    public void checkBookingDate(String date) {


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

    public void setBookingData(String time) {

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

}
