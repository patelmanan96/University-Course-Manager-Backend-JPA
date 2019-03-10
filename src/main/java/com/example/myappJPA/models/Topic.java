package com.example.myappJPA.models;

public class Topic {
    private String title;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String topicName) {
        this.title = topicName;
    }

    public int getId() {
        return id;
    }

    public void setId(int topicId) {
        this.id = topicId;
    }
}
