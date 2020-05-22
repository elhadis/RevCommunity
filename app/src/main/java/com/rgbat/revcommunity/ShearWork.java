package com.rgbat.revcommunity;

public class ShearWork {
    String description,place,employer,date,time;

    public ShearWork() {
    }

    public ShearWork(String description, String place, String employer, String date, String time) {
        this.description = description;
        this.place = place;
        this.employer = employer;
        this.date = date;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
