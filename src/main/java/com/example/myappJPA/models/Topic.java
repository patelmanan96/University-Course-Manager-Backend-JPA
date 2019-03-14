package com.example.myappJPA.models;

import com.example.myappJPA.models.Widget;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @OneToMany(mappedBy = "topic")
    private List<Widget> widgets;

    @ManyToOne
    @JsonIgnore
    private Lesson lesson;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

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
