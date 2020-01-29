package com.example.anushak.firebasealtaoss;



public class User {
    String empid,department,departmenttype,designation,name,middlename,lastname,email,password,gender,dob,bloodgroup,idproof,proofnumber,phone,interviewschedule,interviewdoneby,doj,referalcode;
    String permanentaddress,correspondanceaddress,officialemail,personalemail,projectmanagermail,projectmanagerid;
    String hrmail,hrid,accountno,bankname,ifsccode,cardname,securityquestions,answer;
    //Our Updates
    String updateid,utype;
    String projectname;
    String notes;
    String taskstatus;
    String updatedby;
    String date;
    String userId;
    public String imageURL,deleteURL;
    String id;
    public String preference;

    String smile,lefteye,righteye;

    String prefproject;

    String profile,project,message,mypay,payslips,reimbursementupload,attendance,ourupdates,myprojects,careers;

    String newempform,empdirectory,projectdetails,empattendance,reimbursementfetch,documentsupload,payslipsfetch,rewards,interviews;
    String documentsfetch,payroll,cccamera,permissions;
    public User(){

    }

    public User(String id,String text)
    {
        this.prefproject=text;
        this.empid=id;
    }

    public String getPrefproject() {
        return prefproject;
    }

    public void setPrefproject(String prefproject) {
        this.prefproject = prefproject;
    }

    public User(String deleteURL) {
        this.deleteURL = deleteURL;
    }

    public String getDeleteURL() {
        return deleteURL;
    }

    public void setDeleteURL(String deleteURL) {
        this.deleteURL = deleteURL;
    }


    public User(String name, String middlename, String lastname)
    {
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
    }

    public User(String empid, String smile, String lefteye, String righteye) {
        this.empid = empid;
        this.smile = smile;
        this.lefteye = lefteye;
        this.righteye = righteye;
    }

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }

    public String getLefteye() {
        return lefteye;
    }

    public void setLefteye(String lefteye) {
        this.lefteye = lefteye;
    }

    public String getRighteye() {
        return righteye;
    }

    public void setRighteye(String righteye) {
        this.righteye = righteye;
    }

    //For Pay slips Documents Upload
    public String type;
    // public String name;
    public String month;
    public String year;
    public String url;

    ////New Employee Form

    public User(String userId, String empid, String department, String departmenttype, String designation, String name, String middlename, String lastname, String email, String password, String gender, String dob, String bloodgroup, String idproof, String proofnumber, String phone, String interviewschedule, String interviewdoneby, String doj, String permanentaddress, String correspondanceaddress, String officialemail, String personalemail, String projectmanagermail, String projectmanagerid, String hrmail, String hrid, String accountno, String bankname, String ifsccode, String cardname, String securityquestions, String answer, String referalcode, String imageURL) {
        this.userId = userId;
        this.empid = empid;
        this.department = department;
        this.departmenttype = departmenttype;
        this.designation = designation;
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.bloodgroup = bloodgroup;
        this.idproof = idproof;
        this.proofnumber = proofnumber;
        this.phone = phone;
        this.interviewschedule = interviewschedule;
        this.interviewdoneby = interviewdoneby;
        this.doj = doj;
        this.permanentaddress = permanentaddress;
        this.correspondanceaddress = correspondanceaddress;
        this.officialemail = officialemail;
        this.personalemail = personalemail;
        this.projectmanagermail = projectmanagermail;
        this.projectmanagerid = projectmanagerid;
        this.hrmail = hrmail;
        this.hrid = hrid;
        this.accountno = accountno;
        this.bankname = bankname;
        this.ifsccode = ifsccode;
        this.cardname = cardname;
        this.securityquestions = securityquestions;
        this.answer = answer;
        this.referalcode = referalcode;
        this.imageURL = imageURL;
    }

    public User(String type, String empid, String month, String year, String url) {
        this.type = type;
        this.empid = empid;
        this.month = month;
        this.year = year;
        this.url = url;
    }

    /////For Pay Slip Documents Upload

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }


    public String getDepartmenttype() {
        return departmenttype;
    }

    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getIdproof() {
        return idproof;
    }

    public void setIdproof(String idproof) {
        this.idproof = idproof;
    }

    public String getProofnumber() {
        return proofnumber;
    }

    public void setProofnumber(String proofnumber) {
        this.proofnumber = proofnumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInterviewschedule() {
        return interviewschedule;
    }

    public void setInterviewschedule(String interviewschedule) {
        this.interviewschedule = interviewschedule;
    }

    public String getInterviewdoneby() {
        return interviewdoneby;
    }

    public void setInterviewdoneby(String interviewdoneby) {
        this.interviewdoneby = interviewdoneby;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getPermanentaddress() {
        return permanentaddress;
    }

    public void setPermanentaddress(String permanentaddress) {
        this.permanentaddress = permanentaddress;
    }

    public String getCorrespondanceaddress() {
        return correspondanceaddress;
    }

    public void setCorrespondanceaddress(String correspondanceaddress) {
        this.correspondanceaddress = correspondanceaddress;
    }

    public String getOfficialemail() {
        return officialemail;
    }

    public void setOfficialemail(String officialemail) {
        this.officialemail = officialemail;
    }

    public String getPersonalemail() {
        return personalemail;
    }

    public void setPersonalemail(String personalemail) {
        this.personalemail = personalemail;
    }

    public String getProjectmanagermail() {
        return projectmanagermail;
    }

    public void setProjectmanagermail(String projectmanagermail) {
        this.projectmanagermail = projectmanagermail;
    }

    public String getProjectmanagerid() {
        return projectmanagerid;
    }

    public void setProjectmanagerid(String projectmanagerid) {
        this.projectmanagerid = projectmanagerid;
    }

    public String getHrmail() {
        return hrmail;
    }

    public void setHrmail(String hrmail) {
        this.hrmail = hrmail;
    }

    public String getHrid() {
        return hrid;
    }

    public void setHrid(String hrid) {
        this.hrid = hrid;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getSecurityquestions() {
        return securityquestions;
    }

    public void setSecurityquestions(String securityquestions) {
        this.securityquestions = securityquestions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getReferalcode() {
        return referalcode;
    }

    public void setReferalcode(String referalcode) {
        this.referalcode = referalcode;
    }

    ////New Update

    public User(String updateid, String empid, String projectname, String notes, String taskstatus, String updatedby, String date,String utype) {
        this.empid = empid;
        this.updateid = updateid;
        this.projectname = projectname;
        this.notes = notes;
        this.taskstatus = taskstatus;
        this.updatedby = updatedby;
        this.date = date;
        this.utype = utype;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getUpdateid() {
        return updateid;
    }

    public void setUpdateid(String updateid) {
        this.updateid = updateid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //Employee Prefernces

    public User(String empid, String profile, String project, String message, String mypay, String payslips, String reimbursementupload, String attendance, String ourupdates, String myprojects, String careers) {
        this.empid = empid;
        this.profile = profile;
        this.project = project;
        this.message = message;
        this.mypay = mypay;
        this.payslips = payslips;
        this.reimbursementupload = reimbursementupload;
        this.attendance = attendance;
        this.ourupdates = ourupdates;
        this.myprojects = myprojects;
        this.careers = careers;
    }
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMypay() {
        return mypay;
    }

    public void setMypay(String mypay) {
        this.mypay = mypay;
    }

    public String getPayslips() {
        return payslips;
    }

    public void setPayslips(String payslips) {
        this.payslips = payslips;
    }

    public String getReimbursementupload() {
        return reimbursementupload;
    }

    public void setReimbursementupload(String reimbursementupload) {
        this.reimbursementupload = reimbursementupload;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getOurupdates() {
        return ourupdates;
    }

    public void setOurupdates(String ourupdates) {
        this.ourupdates = ourupdates;
    }

    public String getMyprojects() {
        return myprojects;
    }

    public void setMyprojects(String myprojects) {
        this.myprojects = myprojects;
    }

    public String getCareers() {
        return careers;
    }

    public void setCareers(String careers) {
        this.careers = careers;
    }

    //HR Preferences


    public User(String empid, String profile, String project, String message, String mypay, String payslips, String reimbursementupload, String attendance, String ourupdates, String myprojects, String newempform, String empdirectory, String projectdetails, String empattendance, String reimbursementfetch, String documentsupload, String payslipsfetch, String careers, String rewards, String interviews) {
        this.empid = empid;
        this.profile = profile;
        this.project = project;
        this.message = message;
        this.mypay = mypay;
        this.payslips = payslips;
        this.reimbursementupload = reimbursementupload;
        this.attendance = attendance;
        this.ourupdates = ourupdates;
        this.myprojects = myprojects;
        this.newempform = newempform;
        this.empdirectory = empdirectory;
        this.projectdetails = projectdetails;
        this.empattendance = empattendance;
        this.reimbursementfetch = reimbursementfetch;
        this.documentsupload = documentsupload;
        this.payslipsfetch = payslipsfetch;
        this.careers = careers;
        this.rewards = rewards;
        this.interviews = interviews;
    }


    ///Admin Preferences

    public User(String empid, String profile, String project, String message, String mypay, String payslips, String reimbursementupload, String attendance, String ourupdates, String myprojects, String newempform, String empdirectory, String projectdetails, String empattendance, String reimbursementfetch, String documentsupload, String payslipsfetch, String careers, String rewards, String interviews, String documentsfetch, String payroll, String cccamera, String permissions) {
        this.empid = empid;
        this.profile = profile;
        this.project = project;
        this.message = message;
        this.mypay = mypay;
        this.payslips = payslips;
        this.reimbursementupload = reimbursementupload;
        this.attendance = attendance;
        this.ourupdates = ourupdates;
        this.myprojects = myprojects;
        this.newempform = newempform;
        this.empdirectory = empdirectory;
        this.projectdetails = projectdetails;
        this.empattendance = empattendance;
        this.reimbursementfetch = reimbursementfetch;
        this.documentsupload = documentsupload;
        this.payslipsfetch = payslipsfetch;
        this.careers = careers;
        this.rewards = rewards;
        this.interviews = interviews;
        this.documentsfetch = documentsfetch;
        this.payroll = payroll;
        this.cccamera = cccamera;
        this.permissions = permissions;
    }

    public String getNewempform() {
        return newempform;
    }

    public void setNewempform(String newempform) {
        this.newempform = newempform;
    }

    public String getEmpdirectory() {
        return empdirectory;
    }

    public void setEmpdirectory(String empdirectory) {
        this.empdirectory = empdirectory;
    }

    public String getProjectdetails() {
        return projectdetails;
    }

    public void setProjectdetails(String projectdetails) {
        this.projectdetails = projectdetails;
    }

    public String getEmpattendance() {
        return empattendance;
    }

    public void setEmpattendance(String empattendance) {
        this.empattendance = empattendance;
    }

    public String getReimbursementfetch() {
        return reimbursementfetch;
    }

    public void setReimbursementfetch(String reimbursementfetch) {
        this.reimbursementfetch = reimbursementfetch;
    }

    public String getDocumentsupload() {
        return documentsupload;
    }

    public void setDocumentsupload(String documentsupload) {
        this.documentsupload = documentsupload;
    }

    public String getPayslipsfetch() {
        return payslipsfetch;
    }

    public void setPayslipsfetch(String payslipsfetch) {
        this.payslipsfetch = payslipsfetch;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public String getInterviews() {
        return interviews;
    }

    public void setInterviews(String interviews) {
        this.interviews = interviews;
    }

    public String getDocumentsfetch() {
        return documentsfetch;
    }

    public void setDocumentsfetch(String documentsfetch) {
        this.documentsfetch = documentsfetch;
    }

    public String getPayroll() {
        return payroll;
    }

    public void setPayroll(String payroll) {
        this.payroll = payroll;
    }

    public String getCccamera() {
        return cccamera;
    }

    public void setCccamera(String cccamera) {
        this.cccamera = cccamera;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }


}
