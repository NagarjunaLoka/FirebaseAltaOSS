package com.example.anushak.firebasealtaoss;

public class Attendance {


    public String name, empid, intime, outtime, address, lat, longi, date, outlat, outlongi, outaddress, note, outnote, serviceno, modelno, outserviceno, outmodelno;


    public Attendance() {

    }

    public Attendance(String name, String empid, String intime, String outtime, String address, String lat, String longi, String date, String outlat, String outlongi, String outaddress, String note, String serviceno, String modelno, String outserviceno, String outmodelno, String outnote) {
        this.name = name;
        this.empid = empid;
        this.intime = intime;
        this.outtime = outtime;
        this.address = address;
        this.lat = lat;
        this.longi = longi;
        this.date = date;
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

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
