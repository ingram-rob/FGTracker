package com.inurpocketapps.fgtracker;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String first;
    private String middle;
    private String last;
    private String id;
    private String birthDate;
    //private List<FitnessTest> testList;
    private int age;
    private int weight;
    private int heightFeet;
    private int heightInches;

    //Default constructor
    public Student() {
        //testList = new ArrayList<>();
    }

    public Student(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        //testList = new ArrayList<FitnessTest>();
    }

    /*public List<FitnessTest> getTestList() {
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
    }*/

    public String getName () { return first + " " + middle + " " + last; }

    public String getFirst () { return first; }

    public String getMiddle () { return middle; }

    public String getLast () { return last; }

    public String getBirthDate() { return birthDate; }

    public int getHeightFeet() { return heightFeet; }

    public int getHeightInches() { return heightInches; }

    public int getWeight() { return weight; }

    public String getId(){ return id; }

    public int getAge() { return age; }

    public void setFirst (String first) { this.first = first; }

    public void setMiddle (String middle) { this.middle = middle; }

    public void setLast (String last) { this.last = last; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public void setHeightFeet(int heightFeet) { this.heightFeet = heightFeet; }

    public void setHeightInches(int heightInches) { this.heightInches = heightInches; }

    public void setWeight(int weight) { this.weight = weight; }

    public void setAge (int age) { this.age = age; }

    public void setId (String id) { this.id = id; }
}