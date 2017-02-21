package com.huake.testrxjava.bean;

/*
 * @创建者     兰昱
 * @创建时间  2017/2/20 22:57
 * @描述	      
 */

import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Course> mCourse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourse() {
        return mCourse;
    }

    public void setCourse(ArrayList<Course> course) {
        mCourse = course;
    }
}
