package com.inurpocketapps.fgtracker;

public class ResultsTest extends FitnessTest {
    boolean aerobic;
    boolean abdominal;
    boolean bodyComp;
    boolean trunk;
    boolean upperBody;
    boolean flexibility;
    int testsMet;

    public ResultsTest() {
        date = getCurrentDate();
        aerobic = false;
        abdominal = false;
        bodyComp = false;
        trunk = false;
        upperBody = false;
        flexibility = false;
        testsMet = 0;
    }

    public void setAbdominal(boolean abdominal) {
        this.abdominal = abdominal;
    }

    public void setAerobic(boolean aerobic) {
        this.aerobic = aerobic;
    }

    public void setBodyComp(boolean bodyComp) {
        this.bodyComp = bodyComp;
    }

    public void setFlexibility(boolean flexibility) {
        this.flexibility = flexibility;
    }

    public void setTestsMet(int testsMet) {
        this.testsMet = testsMet;
    }

    public void setTrunk(boolean trunk) {
        this.trunk = trunk;
    }

    public void setUpperBody(boolean upperBody) {
        this.upperBody = upperBody;
    }

    public boolean getAerobic() {
        return aerobic;
    }

    public boolean getAbdominal() {
        return abdominal;
    }

    public boolean getBodyComp() {
        return bodyComp;
    }

    public boolean getTrunk() {
        return trunk;
    }

    public boolean getUpperBody() {
        return upperBody;
    }

    public boolean getFlexibility() {
        return flexibility;
    }

    public int getTestsMet() {
        return testsMet;
    }
}
