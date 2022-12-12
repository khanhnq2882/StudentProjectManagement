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
public class Iteration {

    private int iteration_id;
    private int subject_id;
    private String subject_code;
    private String iteration_name;
    private String duration;
    private int status;
    private String note;

    public Iteration() {
    }

    public Iteration(int subject_id) {
        this.subject_id = subject_id;
    }

    public Iteration(String iteration_name) {
        this.iteration_name = iteration_name;
    }

    public Iteration(int subject_id, String subject_code) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
    }

    public Iteration(int iteration_id, int subject_id, String subject_code, String iteration_name, String duration, int status) {
        this.iteration_id = iteration_id;
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
    }

    public Iteration(int subject_id, String subject_code, String iteration_name, String duration, int status, String note) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
        this.note = note;
    }

    public Iteration(int subject_id, String iteration_name, String duration, int status, String note) {
        this.subject_id = subject_id;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
        this.note = note;
    }

    
    public Iteration(int iteration_id, String subject_code, String iteration_name, String duration, int status) {
        this.iteration_id = iteration_id;
        this.subject_code = subject_code;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
    }

    public Iteration(int iteration_id, int subject_id, String iteration_name, String duration, int status, String note) {
        this.iteration_id = iteration_id;
        this.subject_id = subject_id;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
        this.note = note;
    }

    public Iteration(int iteration_id, int subject_id, String subject_code, String iteration_name, String duration, int status, String note) {
        this.iteration_id = iteration_id;
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
        this.note = note;
    }

    

    public Iteration(String iteration_name, String duration, int status) {
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
    }
    
    

    public Iteration(int iteration_id, int subject_id, String iteration_name, String duration, int status) {
        this.iteration_id = iteration_id;
        this.subject_id = subject_id;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
    }

    public Iteration(int subject_id, String iteration_name, String duration, int status) {
        this.subject_id = subject_id;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }   

    public int getIteration_id() {
        return iteration_id;
    }

    public void setIteration_id(int iteration_id) {
        this.iteration_id = iteration_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getIteration_name() {
        return iteration_name;
    }

    public void setIteration_name(String iteration_name) {
        this.iteration_name = iteration_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Iteration{" + "iteration_id=" + iteration_id + ", subject_id=" + subject_id + ", subject_code=" + subject_code + ", iteration_name=" + iteration_name + ", duration=" + duration + ", status=" + status + '}';
    }

    
    
    
}

