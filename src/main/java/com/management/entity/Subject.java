package com.management.entity;

public class Subject {

    private int subject_id;
    private String subject_code;
    private String subject_name;
    private int author_id;
    private String author_name;
    private int status;

    public Subject() {
    }

    public Subject(int subject_id, String subject_code, String subject_name, String author_name, int status) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.author_name = author_name;
        this.status = status;
    }

    public Subject(int subject_id, String subject_code, String subject_name, int author_id, String author_name, int status) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.author_id = author_id;
        this.author_name = author_name;
        this.status = status;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subject{" + "subject_id=" + subject_id + ", subject_code=" + subject_code + ", subject_name=" + subject_name + ", author_id=" + author_id + ", author_name=" + author_name + ", status=" + status + '}';
    }



}
