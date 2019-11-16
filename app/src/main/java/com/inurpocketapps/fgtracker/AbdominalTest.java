package com.inurpocketapps.fgtracker;

public class AbdominalTest extends FitnessTest {
    int curlUps;

    public AbdominalTest() {
        date = getCurrentDate();
        curlUps = 0;
    }

    public AbdominalTest(int curlUps) {
        this.curlUps = curlUps;
        date = getCurrentDate();
    }

    public AbdominalTest(int curlUps, String date) {
        this.curlUps = curlUps;
        this.date = date;
    }

    public void setCurlUps(int curlUps) {
        this.curlUps = curlUps;
    }
    public int getCurlUps() {
        return curlUps;
    }
}
