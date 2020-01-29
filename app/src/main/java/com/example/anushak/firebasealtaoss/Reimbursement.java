package com.example.anushak.firebasealtaoss;

public class Reimbursement {

    public String spinner;
    public String billno;
    public String billdate;
    public String paymentmode;
    public String claimedamount;
    public String url;
    public String emid;
    public String uploaded;
    public String month;
    public String year;
    public String update;
    public String type;
    public String updateid;

    public String mobileno;
    public String operator;



    public String generaltype;

    public String conveancetype;
    public String startdate;
    public String enddate;
    public String source;
    public String destination;
    public String totalkms;
    public String rate;

    public Reimbursement() {
    }

    public Reimbursement(String spinner, String month, String year, String mobileno, String operator, String billno,
                        String billdate, String paymentmode,
                        String claimedamount, String url, String emid,String uploaded,String update,String updateid) {
        this.spinner = spinner;
        this.month = month;
        this.year = year;
        //this.mobileno = mobileno;
        this.operator = operator;
        this.billno = billno;
        this.billdate = billdate;
        this.paymentmode = paymentmode;
        this.claimedamount = claimedamount;
        this.url = url;
        this.emid=emid;
        this.uploaded=uploaded;
        this.update=update;
        this.type=mobileno;
        this.updateid=updateid;
    }
    public Reimbursement(String spinner, String generalmonth, String generalyear, String generaltype,
                         String billno, String billdate, String paymentmode, String claimedamount,
                         String url, String emid,String uploaded,String update,String updateid) {
        this.spinner = spinner;
        this.month = generalmonth;
        this.year = generalyear;
//        this.generaltype = generaltype;
        this.type=generaltype;
        this.billno = billno;
        this.billdate = billdate;
        this.paymentmode = paymentmode;
        this.claimedamount = claimedamount;
        this.url = url;
        this.emid=emid;
        this.uploaded=uploaded;
        this.update=update;
        this.updateid=updateid;

    }

    public Reimbursement(String spinner, String conveyancemonth, String conveyanceyear, String conveancetype,
                            String startdate, String enddate, String source, String destination,
                            String totalkms, String rate, String paymentmode, String claimedamount,
                            String url, String emid,String uploaded,String billdate,String update,String updateid) {
        this.spinner = spinner;
        this.month = conveyancemonth;
        this.year = conveyanceyear;
//        this.conveancetype = conveancetype;
        this.type=conveancetype;
        this.startdate = startdate;
        this.enddate = enddate;
        this.source = source;
        this.destination = destination;
        this.totalkms = totalkms;
        this.rate = rate;
        this.paymentmode = paymentmode;
        this.claimedamount = claimedamount;
        this.url = url;
        this.emid=emid;
        this.uploaded=uploaded;
        this.billdate=billdate;
        this.update=update;
        this.updateid=updateid;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public String getClaimedamount() {
        return claimedamount;
    }

    public void setClaimedamount(String claimedamount) {
        this.claimedamount = claimedamount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmid() {
        return emid;
    }

    public void setEmid(String emid) {
        this.emid = emid;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getGeneraltype() {
        return generaltype;
    }

    public void setGeneraltype(String generaltype) {
        this.generaltype = generaltype;
    }

    public String getConveancetype() {
        return conveancetype;
    }

    public void setConveancetype(String conveancetype) {
        this.conveancetype = conveancetype;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTotalkms() {
        return totalkms;
    }

    public void setTotalkms(String totalkms) {
        this.totalkms = totalkms;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateid() {
        return updateid;
    }

    public void setUpdateid(String updateid) {
        this.updateid = updateid;
    }
}
