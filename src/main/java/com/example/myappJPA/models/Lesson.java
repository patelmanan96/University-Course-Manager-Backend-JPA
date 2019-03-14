package com.example.myappJPA.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @OneToMany(mappedBy = "lesson")
    private List<Topic> topics;

    @ManyToOne
    @JsonIgnore
    private Module module;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
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
