package com.inurpocketapps.fgtracker;

public class AerobicTest extends FitnessTest {
    int mileMin;
    int mileSec;
    int pacerLaps;
    int walkMin;
    int walkSec;
    int walkPulse;

    public AerobicTest() {
        date = getCurrentDate();
        mileMin = 0;
        mileSec = 0;
        pacerLaps = 0;
        walkMin = 0;
        walkSec = 0;
        walkPulse = 0;
    }

    public void setMileMin(int mileMin) {
        this.mileMin = mileMin;
    }

    public void setMileSec(int mileSec) {
        this.mileSec = mileSec;
    }

    public void setPacerLaps(int pacerLaps) {
        this.pacerLaps = pacerLaps;
    }

    public void setWalkMin(int walkMin) {
        this.walkMin = walkMin;
    }

    public void setWalkSec(int walkSec) {
        this.walkSec = walkSec;
    }

    public void setWalkPulse(int walkPulse) {
        this.walkPulse = walkPulse;
    }

    public int getMileMin() {
        return mileMin;
    }

    public int getMileSec() {
        return mileSec;
    }

    public int getPacerLaps() {
        return pacerLaps;
    }

    public int getWalkMin() {
        return walkMin;
    }

    public int getWalkSec() {
        return walkSec;
    }

    public int getWalkPulse() {
        return walkPulse;
    }
}
