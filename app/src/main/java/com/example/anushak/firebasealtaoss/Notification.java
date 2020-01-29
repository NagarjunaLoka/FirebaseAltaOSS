package com.example.anushak.firebasealtaoss;

/**
 * Created by Anushak on 29-03-2019.
 */

public class Notification {

    private String NTitle = null;
    private String NMessage = null;
    private String NDate = null;
    private  String EMPLOYEE;
    private  String id;

    public Notification(String id, String EMPLOYEE, String Ntitle, String Nmessage, String Ndate) {

        super();

        this.NTitle = Ntitle;

        this.NMessage = Nmessage;

        this.NDate = Ndate;

        this.EMPLOYEE=EMPLOYEE;

        this.id=id;

    }

    public Notification() {
    }

    public String getNTitle() {
        return NTitle;
    }

    public void setNTitle(String NTitle) {
        this.NTitle = NTitle;
    }

    public String getNMessage() {
        return NMessage;
    }

    public void setNMessage(String NMessage) {
        this.NMessage = NMessage;
    }

    public String getNDate() {
        return NDate;
    }

    public void setNDate(String NDate) {
        this.NDate = NDate;
    }

    public String getEMPLOYEE() {
        return EMPLOYEE;
    }

    public void setEMPLOYEE(String EMPLOYEE) {
        this.EMPLOYEE = EMPLOYEE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
