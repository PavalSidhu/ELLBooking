package com.example.ellbooking.Presenter;

public interface Booking {
    String getTimeSlot();
    String getDate();
    String getStatus();
    String getBooked();
    void SaveBooking(String timeSlot, String date);
    void checkBookingDate(String date);
    void setBookingData(String time);
}
