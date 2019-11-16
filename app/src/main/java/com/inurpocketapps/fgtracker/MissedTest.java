package com.inurpocketapps.fgtracker;

public class MissedTest extends FitnessTest {
    /******************************************
     * we might make this an enum later on
     * for now:
     * n = N/A
     * a = Absent
     * i = IEP/Special needs
     * e = Extraordinary
     * m = Medical
     * d = Declined to participate
     ******************************************/
    char reason;

    public MissedTest() {
        date = getCurrentDate();
        reason = 'n';
    }

    public void setReason(char reason) {
        this.reason = reason;
    }

    public char getReason() {
        return reason;
    }
}
