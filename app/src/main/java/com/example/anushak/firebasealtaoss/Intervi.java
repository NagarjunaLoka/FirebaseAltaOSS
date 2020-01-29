package com.example.anushak.firebasealtaoss;


public class Intervi {


    String interid;
    String full_name;
    String qualification;
    String freshers;
    String email;
    String mobile;
    String design;
    String project;
    String referename;
    String refereid;
    String select;
    String status;



    public Intervi
            (String interid, String full_Name, String qualification, String freshers, String email, String mobile, String designation, String project, String select, String status) {
        this.interid = interid;
        this.full_name = full_Name;
        this.qualification = qualification;
        this.freshers = freshers;
        this.email = email;
        this.mobile = mobile;
        this.design = designation;
        this.project = project;
        this.select = select;
        this.status = status;


    }

    public Intervi(String interid, String full_name, String qualification, String freshers, String email, String mobile, String design, String project, String referename, String refereid, String select, String status) {
        this.interid = interid;
        this.full_name = full_name;
        this.qualification = qualification;
        this.freshers = freshers;
        this.email = email;
        this.mobile = mobile;
        this.design = design;
        this.project = project;
        this.referename = referename;
        this.refereid = refereid;
        this.select = select;
        this.status = status;
    }

    public Intervi() {
    }

    public String getInterid() {

        return interid;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getQualification() {
        return qualification;
    }

    public String getFreshers() {
        return freshers;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDesign() {
        return design;
    }

    public String getProject() {
        return project;
    }


    public String getReferename() {return referename; }

    public String getRefereid() {return refereid; }

    public String getSelect() {return select; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

