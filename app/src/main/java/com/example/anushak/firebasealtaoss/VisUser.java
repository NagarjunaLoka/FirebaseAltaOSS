package com.example.anushak.firebasealtaoss;

public class VisUser {
    private String visid;
    private String fname;
    private String mname;
    private String lname;
    private String email;
    private String phone;
    private String contactperson;
    private String empid;
    private String companyname;
    private String companybranch;
    private String dateofvisit;
    private String reasonforvisit;
    private String sppersonalid;
    private String personalid;
    private String typeofvisit;
    private String profile;



    public VisUser() {
    }


    public VisUser(String visid, String fname, String mname, String lname, String email, String phone, String contactperson,
                   String empid, String companyname, String companybranch, String dateofvisit, String reasonforvisit, String sppersonalid, String personalid, String typeofvisit,String profile) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.contactperson = contactperson;
        this.empid = empid;
        this.companyname = companyname;
        this.companybranch = companybranch;
        this.dateofvisit = dateofvisit;
        this.reasonforvisit = reasonforvisit;
        this.sppersonalid = sppersonalid;
        this.personalid = personalid;
        this.typeofvisit = typeofvisit;
        this.visid = visid;
        this.profile=profile;

    }

    public VisUser(String fname, String lname, String email, String phone, String contactperson, String empid, String companyname, String companybranch, String dateofvisit, String reasonforvisit, String personalid, String typeofvisit) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.contactperson = contactperson;
        this.empid = empid;
        this.companyname = companyname;
        this.companybranch = companybranch;
        this.dateofvisit = dateofvisit;
        this.reasonforvisit = reasonforvisit;
        this.personalid = personalid;
        this.typeofvisit = typeofvisit;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanybranch() {
        return companybranch;
    }

    public void setCompanybranch(String companybranch) {
        this.companybranch = companybranch;
    }

    public String getDateofvisit() {
        return dateofvisit;
    }

    public void setDateofvisit(String dateofvisit) {
        this.dateofvisit = dateofvisit;
    }

    public String getReasonforvisit() {
        return reasonforvisit;
    }

    public void setReasonforvisit(String reasonforvisit) {
        this.reasonforvisit = reasonforvisit;
    }

    public String getSppersonalid() {
        return sppersonalid;
    }

    public void setSppersonalid(String sppersonalid) {
        this.sppersonalid = sppersonalid;
    }

    public String getPersonalid() {
        return personalid;
    }

    public void setPersonalid(String personalid) {
        this.personalid = personalid;
    }

    public String getTypeofvisit() {
        return typeofvisit;
    }

    public void setTypeofvisit(String typeofvisit) {
        this.typeofvisit = typeofvisit;
    }

    public String getVisid() {
        return visid;
    }

    public void setVisid(String visid) {
        this.visid = visid;
    }
}
