package com.example.myappJPA.models;

import java.util.ArrayList;

public class Faculty {

    private String username;
    private String password;
    private int userId;
    private String fName;
    private String lName;
    private ArrayList<Course> facultyCourseList = new ArrayList<>();
    private String email;
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Course> getFacultyCourseList() {
        return facultyCourseList;
    }

    public void setFacultyCourseList(ArrayList<Course> facultyCourseList) {
        this.facultyCourseList = facultyCourseList;
    }

    public void setFirstName(String firstName) {
        fName = firstName;
    }


    public String getFirstName() {
        return fName;
    }


    public void setLastName(String lastName) {
        lName = lastName;
    }


    public String getLastName() {
        return lName;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return this.username;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getUserId() {
        return this.userId;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return this.password;
    }
}
