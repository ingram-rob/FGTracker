package com.inurpocketapps.fgtracker;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String first;
    private String middle;
    private String last;
    private String birthDate;
    private List<FitnessTest> testList;
    private int weight;
    private int heightFeet;
    private int heightInches;

    //Default constructor
    //all it needs to do for now is initialize testList
    public Student(String first, String middle, String last, String birthDate) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.birthDate = birthDate;
        testList = new ArrayList<FitnessTest>();
    }

    public List<FitnessTest> getTestList() {
        return testList;
    }

    public void setTestList(List<FitnessTest> testList) {
        this.testList = testList;
    }

    public void addTest(FitnessTest test) {
        testList.add(test);
    }

    public FitnessTest getTest(int i) {
        return testList.get(i);
    }

    public String getName () {
        return first + " " + middle + " " + last;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getHeightFeet() {
        return heightFeet;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public int getWeight() {
        return weight;
    }

    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
