package com.example.anushak.firebasealtaoss;



public class Artist {

    public String id, name,empid, email,title,subject,discription,priority,dept,status,empidlist;
    String phone;
    String prefproject;


    public Artist() {
    }
    public Artist(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    public Artist(String id, String name, String empid, String email, String title, String subject, String discription, String priority, String dept, String status, String empidlist) {
        this.id = id;
        this.name = name;
        this.empid = empid;
        this.email = email;
        this.title = title;
        this.subject = subject;
        this.discription = discription;
        this.priority = priority;
        this.dept = dept;
        this.status = status;
        this.empidlist = empidlist;
    }

    public Artist(String prefproject, String empid) {
        this.prefproject = prefproject;
        this.empid = empid;
    }


    public String getPrefproject() {
        return prefproject;
    }

    public void setPrefproject(String prefproject) {
        this.prefproject = prefproject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmpidlist() {
        return empidlist;
    }

    public void setEmpidlist(String empidlist) {
        this.empidlist = empidlist;
    }
}
