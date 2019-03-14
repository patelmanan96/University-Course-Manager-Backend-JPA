package com.example.myappJPA.models;

import javax.persistence.*;

@Entity
@DiscriminatorValue("heading")
public class HeadingWidget extends Widget {

    private int size;

    private String headingText;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }
}
