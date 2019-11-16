package com.inurpocketapps.fgtracker;

public class FlexibilityTest extends FitnessTest {
    int sitAndReachRight;
    int sitAndReachLeft;
    boolean leftPass;
    boolean rightPass;

    public FlexibilityTest() {
        date = getCurrentDate();
        sitAndReachLeft = 0;
        sitAndReachRight = 0;
        leftPass = false;
        rightPass = false;
    }

    public void setLeftPass(Boolean leftPass) {
        this.leftPass = leftPass;
    }

    public void setRightPass(Boolean rightPass) {
        this.rightPass = rightPass;
    }

    public void setSitAndReachLeft(int sitAndReachLeft) {
        this.sitAndReachLeft = sitAndReachLeft;
    }

    public void setSitAndReachRight(int sitAndReachRight) {
        this.sitAndReachRight = sitAndReachRight;
    }

    public int getSitAndReachLeft() {
        return sitAndReachLeft;
    }

    public int getSitAndReachRight() {
        return sitAndReachRight;
    }

    public boolean isLeftPass() {
        return leftPass;
    }

    public boolean isRightPass() {
        return rightPass;
    }
}
