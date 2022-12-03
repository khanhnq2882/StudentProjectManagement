package com.management.entity;

public class ClassUser {

    int idclassuser;
    int class_id;
    int team_id;
    int user_id;
    int team_leader;
    String dropout_date;
    String user_notes;
    String ongoing_eval;
    String final_pres_eval;
    String final_topic_eval;
    int status;
    String class_code;
    String trainer_id;
    String subject_id;
    String userFullName;
    String subject_code;
    String subject_name;
    String author_name;
    String trainer_name;
    String trainer_email;
    String class_year;
    String iteration_name;
    String duration;
    int iter_status;
    int iter_id;

    public ClassUser() {
    }

    public ClassUser(int idclassuser, int class_id, int team_id, int user_id, int team_leader, String dropout_date, String user_notes, String ongoing_eval, String final_pres_eval, String final_topic_eval, int status, String class_code, String trainer_id, String subject_id, String userFullName, String subject_code, String subject_name, String author_name, String trainer_name, String trainer_email, String class_year) {
        this.idclassuser = idclassuser;
        this.class_id = class_id;
        this.team_id = team_id;
        this.user_id = user_id;
        this.team_leader = team_leader;
        this.dropout_date = dropout_date;
        this.user_notes = user_notes;
        this.ongoing_eval = ongoing_eval;
        this.final_pres_eval = final_pres_eval;
        this.final_topic_eval = final_topic_eval;
        this.status = status;
        this.class_code = class_code;
        this.trainer_id = trainer_id;
        this.subject_id = subject_id;
        this.userFullName = userFullName;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.author_name = author_name;
        this.trainer_name = trainer_name;
        this.trainer_email = trainer_email;
        this.class_year = class_year;
    }

    public ClassUser(String class_code, String subject_code, String subject_name, String iteration_name, String duration, int iter_status, int iter_id) {
        this.class_code = class_code;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.iter_status = iter_status;
        this.iter_id = iter_id;
    }

    public int getIter_id() {
        return iter_id;
    }

    public void setIter_id(int iter_id) {
        this.iter_id = iter_id;
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

    public int getIter_status() {
        return iter_status;
    }

    public void setIter_status(int iter_status) {
        this.iter_status = iter_status;
    }

    public String getClass_year() {
        return class_year;
    }

    public void setClass_year(String class_year) {
        this.class_year = class_year;
    }

    public String getTrainer_email() {
        return trainer_email;
    }

    public void setTrainer_email(String trainer_email) {
        this.trainer_email = trainer_email;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getFinal_topic_eval() {
        return final_topic_eval;
    }

    public void setFinal_topic_eval(String final_topic_eval) {
        this.final_topic_eval = final_topic_eval;
    }

    public int getIdclassuser() {
        return idclassuser;
    }

    public void setIdclassuser(int idclassuser) {
        this.idclassuser = idclassuser;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTeam_leader() {
        return team_leader;
    }

    public void setTeam_leader(int team_leader) {
        this.team_leader = team_leader;
    }

    public String getDropout_date() {
        return dropout_date;
    }

    public void setDropout_date(String dropout_date) {
        this.dropout_date = dropout_date;
    }

    public String getUser_notes() {
        return user_notes;
    }

    public void setUser_notes(String user_notes) {
        this.user_notes = user_notes;
    }

    public String getOngoing_eval() {
        return ongoing_eval;
    }

    public void setOngoing_eval(String ongoing_eval) {
        this.ongoing_eval = ongoing_eval;
    }

    public String getFinal_pres_eval() {
        return final_pres_eval;
    }

    public void setFinal_pres_eval(String final_pres_eval) {
        this.final_pres_eval = final_pres_eval;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(String trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Override
    public String toString() {
        return "ClassUser{" + "idclassuser=" + idclassuser + ", class_id=" + class_id + ", team_id=" + team_id + ", user_id=" + user_id + ", team_leader=" + team_leader + ", dropout_date=" + dropout_date + ", user_notes=" + user_notes + ", ongoing_eval=" + ongoing_eval + ", final_pres_eval=" + final_pres_eval + ", status=" + status + ", class_code=" + class_code + ", trainer_id=" + trainer_id + ", subject_id=" + subject_id + ", userFullName=" + userFullName + '}';
    }

}
