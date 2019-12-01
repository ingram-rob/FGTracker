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

    School (){ }

    School (String name){
        this.name = name;
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
     * Returns the name of the school as a string
     * @return string
     */
    public String getName() {
        return name;
    }
}
