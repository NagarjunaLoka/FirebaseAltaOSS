package com.example.anushak.firebasealtaoss;

public class contact {
    String email,phone,comment,name;

    public contact(){

    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public contact(String email, String phone, String comment, String name) {
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.name = name;
    }
}
