package com.example.myappJPA.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("list")
public class ListWidget extends Widget{
    private String listType;
    private String listItems = "";

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getListItems() {
        return listItems;
    }

    public void setListItems(String listItems) {
        this.listItems = listItems;
    }
}
