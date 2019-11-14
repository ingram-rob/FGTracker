package com.inurpocketapps.fgtracker;

import java.util.ArrayList;
import java.util.List;

public class Grade {
    private int gradeNumber;
    private List<Classroom> classrooms;

    Grade(int gradeNumber) {
        this.gradeNumber = gradeNumber;
        this.classrooms = new ArrayList<Classroom>();
    }

    public Classroom getClassroom(String name){
        return null;
    }

    public void addClassroom(String name) {
        classrooms.add(new Classroom(name));
    }
}
