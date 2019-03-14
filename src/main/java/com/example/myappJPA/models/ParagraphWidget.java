package com.example.myappJPA.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("paragraph")
public class ParagraphWidget extends Widget {
    String paraText;

    public String getParaText() {
        return paraText;
    }

    public void setParaText(String paraText) {
        this.paraText = paraText;
    }
}
