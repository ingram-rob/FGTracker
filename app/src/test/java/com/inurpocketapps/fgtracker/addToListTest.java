package com.inurpocketapps.fgtracker;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class addToListTest {

    @Test
    public void addGradeTest() {
        School school = new School();

        school.addGrade(2);
        assertNotNull("Grade does not exist", school.getGrade(2));
    }

    @Test
    public void addClassTest() {
        Grade grade = new Grade();

        grade.addClassroom("Mrs. Gooble");
        assertNotNull("Class does not exist", grade.getClassroom("Mrs. Gooble"));
    }

    @Test
    public void addStudentTest() {
        Classroom classroom = new Classroom();

        classroom.addStudent("Jeremy", "Davis");
        assertNotNull("Student doesen't exist", classroom.getStudent("JeremyDavis"));
    }
}
