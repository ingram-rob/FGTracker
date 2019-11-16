package com.inurpocketapps.fgtracker;

public class BodyCompTest extends FitnessTest {
    int triceps;
    int calf;
    int biaFat; //this is a percentage

    public BodyCompTest() {
        date = getCurrentDate();
        triceps = 0;
        calf = 0;
        biaFat = 0;
    }

    public void setBiaFat(int biaFat) {
        this.biaFat = biaFat;
    }

    public void setCalf(int calf) {
        this.calf = calf;
    }

    public void setTriceps(int triceps) {
        this.triceps = triceps;
    }

    public int getBiaFat() {
        return biaFat;
    }

    public int getCalf() {
        return calf;
    }

    public int getTriceps() {
        return triceps;
    }
}
