package com.example.anushak.firebasealtaoss;

/**
 * Created by Maheshbabu on 16-05-2019.
 */

public class Event {
    String eventid;
    String eventname;
    String godate;
    String todate;
    String eventdes;

    public Event(){
    }

    public Event(String eventid, String eventname, String godate, String todate, String eventdes) {
        this.eventid = eventid;
        this.eventname = eventname;
        this.godate = godate;
        this.todate = todate;
        this.eventdes = eventdes;
    }

    public String getEventid() {
        return eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public String getGodate() {
        return godate;
    }

    public String getTodate() {
        return todate;
    }

    public String getEventdes() {
        return eventdes;
    }


    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setGodate(String godate) {
        this.godate = godate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public void setEventdes(String eventdes) {
        this.eventdes = eventdes;
    }
}
