package com.inurpocketapps.fgtracker;

public class TrunkTest extends FitnessTest {
    String trunkLift;

    public TrunkTest() {
        date = getCurrentDate();
        trunkLift = "0";
    }

    public void setTrunkLift(String trunkLift) {
        this.trunkLift = trunkLift;
    }

    public String getTrunkLift() {
        return trunkLift;
    }
}
