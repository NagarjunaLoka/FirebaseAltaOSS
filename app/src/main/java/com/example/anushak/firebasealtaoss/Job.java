package com.example.anushak.firebasealtaoss;

public class Job {
    String jobid;
    String jempid;
    String jobtitle;
    String vacancies;
    String experience;
    String bondperiod;
    String salary;
    String joblocation;
    String interviewlocation;
    String skillrequirments;

    public Job() {
    }

    public Job(String jobid, String jempid, String jobtitle, String vacancies, String experience, String bondperiod, String salary, String joblocation, String interviewlocation, String skillrequirments) {
        this.jobid = jobid;
        this.jempid = jempid;
        this.jobtitle = jobtitle;
        this.vacancies = vacancies;
        this.experience = experience;
        this.bondperiod = bondperiod;
        this.salary=salary;
        this.joblocation = joblocation;
        this.interviewlocation = interviewlocation;
        this.skillrequirments = skillrequirments;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJempid() {
        return jempid;
    }

    public void setJempid(String jempid) {
        this.jempid = jempid;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getVacancies() {
        return vacancies;
    }

    public void setVacancies(String vacancies) {
        this.vacancies = vacancies;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getBondperiod() {
        return bondperiod;
    }

    public void setBondperiod(String bondperiod) {
        this.bondperiod = bondperiod;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJoblocation() {
        return joblocation;
    }

    public void setJoblocation(String joblocation) {
        this.joblocation = joblocation;
    }

    public String getInterviewlocation() {
        return interviewlocation;
    }

    public void setInterviewlocation(String interviewlocation) {
        this.interviewlocation = interviewlocation;
    }

    public String getSkillrequirments() {
        return skillrequirments;
    }

    public void setSkillrequirments(String skillrequirments) {
        this.skillrequirments = skillrequirments;
    }

}

