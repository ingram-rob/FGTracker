package com.inurpocketapps.fgtracker;

public class TrunkTest extends FitnessTest {
    int trunkLift;

    public TrunkTest() {
        date = getCurrentDate();
        trunkLift = 0;
    }

    public void setTrunkLift(int trunkLift) {
        this.trunkLift = trunkLift;
    }

    public int getTrunkLift() {
        return trunkLift;
    }
}
