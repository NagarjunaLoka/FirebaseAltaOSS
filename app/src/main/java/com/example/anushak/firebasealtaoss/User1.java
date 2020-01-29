package com.example.anushak.firebasealtaoss;

public class User1 {
    String id1;
    String type1;
    String empid1;
    String url1;


    public User1(String id1, String type1, String empid1, String url1) {
        this.id1 = id1;
        this.empid1 = empid1;
        this.type1 = type1;
        this.url1 = url1;
    }

    public User1() {
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getEmpid1() {
        return empid1;
    }

    public void setEmpid1(String empid1) {
        this.empid1 = empid1;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }
}

