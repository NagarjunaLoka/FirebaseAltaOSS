package com.example.anushak.firebasealtaoss;

public class Department {

    String name;
    String empid;


    public Department() {

    }

    public Department(String name, String empid) {
        this.name = name;
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

}
