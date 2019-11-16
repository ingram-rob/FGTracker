package com.inurpocketapps.fgtracker;

public class UpperBodyTest extends FitnessTest {
    int pullUps;
    int pushUps;
    int armHang;

    public UpperBodyTest() {
        date = getCurrentDate();
        pullUps = 0;
        pushUps = 0;
        armHang = 0;
    }

    public void setArmHang(int armHang) {
        this.armHang = armHang;
    }

    public void setPullUps(int pullUps) {
        this.pullUps = pullUps;
    }

    public void setPushUps(int pushUps) {
        this.pushUps = pushUps;
    }

    public int getArmHang() {
        return armHang;
    }

    public int getPullUps() {
        return pullUps;
    }

    public int getPushUps() {
        return pushUps;
    }
}
