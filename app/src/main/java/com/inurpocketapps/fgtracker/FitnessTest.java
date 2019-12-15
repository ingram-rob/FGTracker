package com.inurpocketapps.fgtracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FitnessTest {
    String date;

    public FitnessTest() {
        date = getCurrentDate();
    }
    public FitnessTest(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //this should be able to get the current date from the system
    public String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(c.getTime());
        return currentDate;
    }
}
