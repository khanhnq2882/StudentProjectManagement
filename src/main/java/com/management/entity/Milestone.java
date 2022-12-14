/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.entity;

/**
 *
 * @author Admin
 */
public class Milestone {

    private int milestone_id;
    private int interation_id;
    private int class_id;
    private String from_date;
    private String to_date;
    private int status;
    private String classCode;
    private String iterationName;
    private String milestone_name;
    private String note;
    public Milestone() {
    }



    public Milestone(int milestone_id, int interation_id, int class_id, String from_date, String to_date, int status) {
        this.milestone_id = milestone_id;
        this.interation_id = interation_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
    }

    public Milestone(int interation_id, int class_id, String from_date, String to_date, int status) {
        this.interation_id = interation_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
    }

    public Milestone(int milestone_id, String from_date, String to_date, int status, String classCode, String iterationName) {
        this.milestone_id = milestone_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.classCode = classCode;
        this.iterationName = iterationName;
    }

    public Milestone(int milestone_id, int class_id, String from_date, String to_date, int status, String classCode) {
        this.milestone_id = milestone_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.classCode = classCode;
    }

    public Milestone(int milestone_id, int interation_id, int class_id, String from_date, String to_date, int status, String classCode, String iterationName, String milestone_name) {
        this.milestone_id = milestone_id;
        this.interation_id = interation_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.classCode = classCode;
        this.iterationName = iterationName;
        this.milestone_name = milestone_name;
    }

    public Milestone(int interation_id, int class_id, String from_date, String to_date, String milestone_name, int status ) {
        this.interation_id = interation_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.milestone_name = milestone_name;
    }

    public Milestone(int milestone_id, int interation_id, int class_id, String from_date, String to_date, int status, String classCode, String iterationName) {
        this.milestone_id = milestone_id;
        this.interation_id = interation_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.classCode = classCode;
        this.iterationName = iterationName;
    }

    public Milestone(int milestone_id, int interation_id, int class_id, String from_date, String to_date, int status, String iterationName) {
        this.milestone_id = milestone_id;
        this.interation_id = interation_id;
        this.class_id = class_id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.iterationName = iterationName;
    }

    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }




    public Milestone(int interation_id, String iterationName) {
        this.interation_id = interation_id;
        this.iterationName = iterationName;
    }

    public Milestone(String class_code,int class_id) {
        this.class_id = class_id;
        this.classCode = class_code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



    public int getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getIterationName() {
        return iterationName;
    }

    public void setIterationName(String iterationName) {
        this.iterationName = iterationName;
    }

    public int getInteration_id() {
        return interation_id;
    }

    public void setInteration_id(int interation_id) {
        this.interation_id = interation_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return "Milestone{" + "milestone_id=" + milestone_id + ", interation_id=" + interation_id + ", class_id=" + class_id + ", from_date=" + from_date + ", to_date=" + to_date + ", status=" + status + ", classCode=" + classCode + ", iterationName=" + iterationName + '}';
    }






}
