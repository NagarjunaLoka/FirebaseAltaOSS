package com.example.anushak.firebasealtaoss.model;



public class User {
    public String name;
    public String Empid;
    public String email;
    public String avata;
    public Status status;
    public Message message;
    public String dateofbirth;


    public User(){
        status = new Status();
        message = new Message();
        status.isOnline = false;
        status.timestamp = 0;
        message.idReceiver = "0";
        message.idSender = "0";
        message.text = "";
        message.timestamp = 0;
    }


}