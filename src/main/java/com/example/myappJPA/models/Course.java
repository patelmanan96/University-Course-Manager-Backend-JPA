package com.example.myappJPA.models;

import java.util.ArrayList;

public class Course {
    private long courseId;
    private String title;
    private String author;
    private ArrayList<Module> modules = new ArrayList<>();

    public long getId() {
        return courseId;
    }

    public void setId(long courseId) {
        this.courseId = courseId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> courseModules) {
        this.modules = courseModules;
    }
}
