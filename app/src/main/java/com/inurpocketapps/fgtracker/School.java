package com.inurpocketapps.fgtracker;

import java.util.ArrayList;
import java.util.List;

public class School {
    private String name;
    private List<Grade> grades;

    School (String name){
        this.name = name;
        this.grades = new ArrayList<Grade>();
    }

    public Grade getGrade(int num) {
        return null;
    }

    public void addGrade(int num) {
        grades.add(new Grade(num));
    }
}
