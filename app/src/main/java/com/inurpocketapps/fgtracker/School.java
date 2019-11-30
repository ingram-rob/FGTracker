package com.inurpocketapps.fgtracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Dallin Lovin
 * @version     1.0
 * @since       1.0
 */
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

    /**
     * returns the specified grade object
     * @param num The grade object to return
     * @return Grade
     */
    public Grade getGrade(int num) {
        return null;
    }

    /**
     * returns the grades list stored in thet school
     * @return List<Grade>
     */
    public List<Grade> getGrades() {
        return grades;
    }

    /**
     * Adds a new grade
     * @param num grade to add
     */
    public void addGrade(int num) {
        grades.add(new Grade(num));
    }

    /**
     * Returns the name of the school as a string
     * @return string
     */
    public String getName() {
        return name;
    }
}
