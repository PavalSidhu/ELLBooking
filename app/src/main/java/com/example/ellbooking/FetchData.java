package com.example.ellbooking;

public class FetchData {

    String booked;
    String date;
    String status;
    String timeSlot;

    public FetchData(String booked, String date, String status, String timeSlot) {
        this.booked = booked;
        this.date = date;
        this.status = status;
        this.timeSlot = timeSlot;
    }

    public String getBooked() {
        return booked;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
