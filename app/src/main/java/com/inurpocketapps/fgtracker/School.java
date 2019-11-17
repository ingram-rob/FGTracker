package com.inurpocketapps.fgtracker;

import java.util.ArrayList;
import java.util.List;

public class School {
    private String name;
    private List<Grade> grades;

    School (){
        this.grades = new ArrayList<>();
    }

    School (String name){
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public Grade getGrade(int num) {
        return null;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void addGrade(int num) {
        grades.add(new Grade(num));
    }

    public String getName() {
        return name;
    }
}
