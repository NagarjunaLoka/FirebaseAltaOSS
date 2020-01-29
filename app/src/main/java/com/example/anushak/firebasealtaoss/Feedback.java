package com.example.anushak.firebasealtaoss;

public class Feedback {
    String id,name,email,mobile,comment;

    public Feedback(){

    }

    public Feedback(String id, String name, String email, String mobile, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.comment = comment;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
