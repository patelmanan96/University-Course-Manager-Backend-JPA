package com.example.myappJPA.models;

import java.util.ArrayList;

public class Module {
    private int id;
    private String title;
    private ArrayList<Lesson> lessons= new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int moduleId) {
        this.id = moduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String moduleName) {
        this.title = moduleName;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
}
