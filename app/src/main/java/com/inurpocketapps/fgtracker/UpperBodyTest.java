package com.inurpocketapps.fgtracker;

public class UpperBodyTest extends FitnessTest {
    String pullUps;
    String pushUps;
    String armHang;

    public UpperBodyTest() {
        date = getCurrentDate();
        pullUps = "0";
        pushUps = "0";
        armHang = "0";
    }

    public void setArmHang(String armHang) {
        this.armHang = armHang;
    }

    public void setPullUps(String pullUps) {
        this.pullUps = pullUps;
    }

    public void setPushUps(String pushUps) {
        this.pushUps = pushUps;
    }

    public String getArmHang() {
        return armHang;
    }

    public String getPullUps() {
        return pullUps;
    }

    public String getPushUps() {
        return pushUps;
    }
}
