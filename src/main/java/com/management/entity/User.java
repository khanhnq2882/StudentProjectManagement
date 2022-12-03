package com.management.entity;

public class User {

    int user_id;
    String roll_number;
    String fullname;
    int gender;
    String date_of_birth;
    String email;
    String mobile;
    String avatar_link;
    String facebook_link;
    int role_id;
    int status;
    String user;
    String pass;
    int idclassuser;
    String teamName;
    int teamLead;
    String note;

    public User() {
    }

    public User(int user_id, String fullname, String email, String mobile) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
    }


    public User(int user_id, String roll_number, String fullname, int gender, String date_of_birth, String email, String mobile, String avatar_link, String facebook_link, int role_id, int status, String user, String pass) {
        this.user_id = user_id;
        this.roll_number = roll_number;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.facebook_link = facebook_link;
        this.role_id = role_id;
        this.status = status;
        this.user = user;
        this.pass = pass;
    }

    public User(String roll_number, String fullname) {
        this.roll_number = roll_number;
        this.fullname = fullname;
    }

    public User(int user_id, String roll_number, String fullname, int gender, String date_of_birth, String email, String mobile, String avatar_link, String facebook_link, int role_id, int status, String user, String pass, int idclassuser, String teamName, int teamLead) {
        this.user_id = user_id;
        this.roll_number = roll_number;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.facebook_link = facebook_link;
        this.role_id = role_id;
        this.status = status;
        this.user = user;
        this.pass = pass;
        this.idclassuser = idclassuser;
        this.teamName = teamName;
        this.teamLead = teamLead;
    }

    public User(String roll_number, String fullname, int gender, String date_of_birth, String email, int role_id, int status, String user, String pass, String note) {
        this.roll_number = roll_number;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.role_id = role_id;
        this.status = status;
        this.user = user;
        this.pass = pass;
        this.note = note;
    }

    public User(String roll_number, String fullname, int gender, String date_of_birth, String email, String mobile, String avatar_link, String user, String pass) {
        this.roll_number = roll_number;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.user = user;
        this.pass = pass;
    }

    public User(int user_id, String roll_number, String fullname, int gender, String date_of_birth, String email, String mobile, int status) {
        this.user_id = user_id;
        this.roll_number = roll_number;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
    }

    public User(int user_id, String roll_number, String fullname, int gender, String date_of_birth, String email, String mobile, int role_id, int status, String note) {
        this.user_id = user_id;
        this.roll_number = roll_number;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.role_id = role_id;
        this.status = status;
        this.note = note;
    }


    public User(int user_id, String roll_number, String fullname, int gender, String date_of_birth, String email, String mobile, String avatar_link, String facebook_link, int role_id, int status, String user, String pass, String note) {
        this.user_id = user_id;
        this.roll_number = roll_number;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.mobile = mobile;
        this.avatar_link = avatar_link;
        this.facebook_link = facebook_link;
        this.role_id = role_id;
        this.status = status;
        this.user = user;
        this.pass = pass;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(int teamLead) {
        this.teamLead = teamLead;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getFullname() {
        return fullname;
    }

    public int getIdclassuser() {
        return idclassuser;
    }

    public void setIdclassuser(int idclassuser) {
        this.idclassuser = idclassuser;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar_link() {
        return avatar_link;
    }

    public void setAvatar_link(String avatar_link) {
        this.avatar_link = avatar_link;
    }

    public String getFacebook_link() {
        return facebook_link;
    }

    public void setFacebook_link(String facebook_link) {
        this.facebook_link = facebook_link;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User(String fullname, int role_id) {
        this.fullname = fullname;
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", roll_number=" + roll_number + ", fullname=" + fullname + ", gender=" + gender + ", date_of_birth=" + date_of_birth + ", email=" + email + ", mobile=" + mobile + ", avatar_link=" + avatar_link + ", facebook_link=" + facebook_link + ", role_id=" + role_id + ", status=" + status + ", user=" + user + ", pass=" + pass + '}';
    }

}
