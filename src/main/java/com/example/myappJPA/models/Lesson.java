package com.example.myappJPA.models;

import java.util.ArrayList;

public class Lesson {
    private int id;
    private String title;
    private ArrayList<Topic> topics = new ArrayList<>();

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public int getId() {
        return id;
    }

    public void setId(int lessonId) {
        this.id = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String lessonName) {
        this.title = lessonName;
    }
}
