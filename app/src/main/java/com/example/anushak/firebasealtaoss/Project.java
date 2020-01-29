package com.example.anushak.firebasealtaoss;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Project {

    String projectid;
    String pempid;
    String projectname;
    String teamleadername;
    String clientname;
    String startdate;
    String enddate;
    String projectstatus;

    String year,empid,leavetype,reason,fromdate,todate,noofdays,applyto,description,status,monthandyear,month,empidmonthandyear,empidyear;

    public Project() {
    }

    public String getPempid() {
        return pempid;
    }

    public void setPempid(String pempid) {
        this.pempid = pempid;
    }

    public Project(String projectid, String pempid, String projectname, String teamleadername, String clientname, String startdate, String enddate, String projectstatus) {
        this.projectid = projectid;
        this.pempid = pempid;
        this.projectname = projectname;
        this.teamleadername = teamleadername;
        this.clientname = clientname;
        this.startdate = startdate;
        this.enddate=enddate;
        this.projectstatus = projectstatus;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getTeamleadername() {
        return teamleadername;
    }

    public void setTeamleadername(String teamleadername) {
        this.teamleadername = teamleadername;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus;
    }


    //Apply Leave


    public Project(String empid, String leavetype, String reason, String fromdate, String todate, String noofdays, String applyto, String description, String status, String month, String year, String monthandyear, String empidmonthandyear, String empidyear) {
        this.empid = empid;
        this.leavetype = leavetype;
        this.reason = reason;
        this.fromdate = fromdate;
        this.todate = todate;
        this.noofdays = noofdays;
        this.applyto = applyto;
        this.description = description;
        this.status=status;
        this.month=month;
        this.year=year;
        this.monthandyear=monthandyear;
        this.empidmonthandyear=empidmonthandyear;
        this.empidyear = empidyear;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getNoofdays() {
        return noofdays;
    }

    public void setNoofdays(String noofdays) {
        this.noofdays = noofdays;
    }

    public String getApplyto() {
        return applyto;
    }

    public void setApplyto(String applyto) {
        this.applyto = applyto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEmpidmonthandyear() {
        return empidmonthandyear;
    }

    public void setEmpidmonthandyear(String empidmonthandyear) {
        this.empidmonthandyear = empidmonthandyear;
    }

    public String getEmpidyear() {
        return empidyear;
    }

    public void setEmpidyear(String empidyear) {
        this.empidyear = empidyear;
    }
}

