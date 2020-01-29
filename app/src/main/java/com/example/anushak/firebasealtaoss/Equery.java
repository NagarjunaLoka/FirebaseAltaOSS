package com.example.anushak.firebasealtaoss;

public class Equery {

    String queryid,empid,email,mmail,mid,hmail,hid,subject,status,qmessage;

    public Equery() {
    }

    public Equery(String queryid, String empid, String email, String mmail, String mid, String hmail, String hid, String subject,String status, String qmessage) {
        this.queryid = queryid;
        this.empid = empid;
        this.email = email;
        this.mmail = mmail;
        this.mid = mid;
        this.hmail = hmail;
        this.hid = hid;
        this.subject = subject;
        this.status = status;
        this.qmessage = qmessage;
    }

    public String getQueryid() {
        return queryid;
    }

    public void setQueryid(String queryid) {
        this.queryid = queryid;
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

    public String getMmail() {
        return mmail;
    }

    public void setMmail(String mmail) {
        this.mmail = mmail;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getHmail() {
        return hmail;
    }

    public void setHmail(String hmail) {
        this.hmail = hmail;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQmessage() {
        return qmessage;
    }

    public void setQmessage(String qmessage) {
        this.qmessage = qmessage;
    }
}
