package com.inurpocketapps.fgtracker;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String first;
    private String middle;
    private String last;
    private String birthDate;
    private Map<String, Integer> testList;

    //Default constructor
    //all it needs to do for now is initialize testList
    public Student(String first, String middle, String last, String birthDate) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.birthDate = birthDate;
        testList = new HashMap<String, Integer>();
    }

    public Map<String, Integer> getTestList() {
        return testList;
    }

    public void setTest(Map<String, Integer> testList) {
        this.testList = testList;
    }

    //add data for a test, should create a new field for the test
    //if it does not already have a field in the map
    public void addTest(String testKey, int testValue) {
        testList.put(testKey, testValue);
    }

    //add a field to the map for a test without providing any results
    public void addTest(String testKey) {
        testList.put(testKey, 0);
    }

    //get test data from the map, requires a valid key
    public int getTest(String test) {
        Integer data = testList.get(test);
        if(data == null) {
            return 0;
        }
        else {
            return data;
        }
    }
}
