package com.inurpocketapps.fgtracker;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private String name;
    private List<Student> students;

    Classroom(){
        this.students = new ArrayList<>();
    }

    Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<Student>();
    }

    public Student getStudent(String name)
    {
        return null;
    }

    public void addStudent(String first, String middle, String last, String birthDate) {
        students.add(new Student(first, middle, last, birthDate));
    }

    public String getName() {
        return name;
    }
}
