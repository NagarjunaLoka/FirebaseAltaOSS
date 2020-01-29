package com.example.anushak.firebasealtaoss.model;



public class Friend extends User {
    public String id;
    public String idRoom;

    public Friend(String id, String idRoom) {
        this.id = id;
        this.idRoom = idRoom;
    }

    public Friend() {
    }
}
