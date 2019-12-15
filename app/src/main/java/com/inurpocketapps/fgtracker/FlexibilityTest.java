package com.inurpocketapps.fgtracker;

public class FlexibilityTest extends FitnessTest {
    String sitAndReachRight;
    String sitAndReachLeft;
    boolean leftPass;
    boolean rightPass;

    public FlexibilityTest() {
        date = getCurrentDate();
        sitAndReachLeft = "0";
        sitAndReachRight = "0";
        leftPass = false;
        rightPass = false;
    }

    public void setLeftPass(Boolean leftPass) {
        this.leftPass = leftPass;
    }

    public void setRightPass(Boolean rightPass) {
        this.rightPass = rightPass;
    }

    public void setSitAndReachLeft(String sitAndReachLeft) {
        this.sitAndReachLeft = sitAndReachLeft;
    }

    public void setSitAndReachRight(String sitAndReachRight) {
        this.sitAndReachRight = sitAndReachRight;
    }

    public String getSitAndReachLeft() {
        return sitAndReachLeft;
    }

    public String getSitAndReachRight() {
        return sitAndReachRight;
    }

    public boolean isLeftPass() {
        return leftPass;
    }

    public boolean isRightPass() {
        return rightPass;
    }
}
