package com.example.myappJPA.models;

import java.util.ArrayList;

public class Faculty extends User {

    private String office;
    private String tenure;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }
}
