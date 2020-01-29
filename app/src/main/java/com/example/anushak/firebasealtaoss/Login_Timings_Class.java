package com.example.anushak.firebasealtaoss;

public class Login_Timings_Class {


    String id,email,time,location;
    String intime,empid,lat,longi,outtime,date,address,status,monthandyear,outlat,outlongi, outaddress, note, outnote, serviceno, modelno, outserviceno, outmodelno;

    public Login_Timings_Class() {

    }

    public Login_Timings_Class(String id, String intime, String empid, String lat, String longi, String outtime, String date, String address, String status, String monthandyear, String outlat, String outlongi, String outaddress, String note, String outnote, String serviceno, String modelno, String outserviceno, String outmodelno) {
        this.id = id;
        this.intime = intime;
        this.empid = empid;
        this.lat = lat;
        this.longi = longi;
        this.outtime = outtime;
        this.date = date;
        this.address = address;
        this.status = status;
        this.monthandyear = monthandyear;
        this.outlat = outlat;
        this.outlongi = outlongi;
        this.outaddress = outaddress;
        this.note = note;
        this.outnote = outnote;
        this.serviceno = serviceno;
        this.modelno = modelno;
        this.outserviceno = outserviceno;
        this.outmodelno = outmodelno;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMonthandyear() {
            return monthandyear;
        }

    public void setMonthandyear(String monthandyear) {
            this.monthandyear = monthandyear;
        }

    public String getOutlat() {
        return outlat;
    }

    public void setOutlat(String outlat) {
        this.outlat = outlat;
    }

    public String getOutlongi() {
        return outlongi;
    }

    public void setOutlongi(String outlongi) {
        this.outlongi = outlongi;
    }

    public String getOutaddress() {
        return outaddress;
    }

    public void setOutaddress(String outaddress) {
        this.outaddress = outaddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOutnote() {
        return outnote;
    }

    public void setOutnote(String outnote) {
        this.outnote = outnote;
    }

    public String getServiceno() {
        return serviceno;
    }

    public void setServiceno(String serviceno) {
        this.serviceno = serviceno;
    }

    public String getModelno() {
        return modelno;
    }

    public void setModelno(String modelno) {
        this.modelno = modelno;
    }

    public String getOutserviceno() {
        return outserviceno;
    }

    public void setOutserviceno(String outserviceno) {
        this.outserviceno = outserviceno;
    }

    public String getOutmodelno() {
        return outmodelno;
    }

    public void setOutmodelno(String outmodelno) {
        this.outmodelno = outmodelno;
    }

    public Login_Timings_Class(String id, String email, String time, String location) {
        this.id = id;
        this.email = email;
        this.time = time;
        this.location = location;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

