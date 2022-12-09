package com.management.entity;

import com.management.dao.DAOChangePass;

public class Team {
    int team_id;
    String class_id;
    String class_code;
    String topic_code;
    String topic_name;
    String gitlab_url;
    int status;
    String team_name;

    public Team(int team_id, String class_id, String topic_code, String topic_name, String team_name) {
        this.team_id = team_id;
        this.class_id = class_id;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.team_name = team_name;
    }

    public Team(int team_id, String class_id, String topic_code, String topic_name, String gitlab_url, int status, String team_name) {
        this.team_id = team_id;
        this.class_id = class_id;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.gitlab_url = gitlab_url;
        this.status = status;
        this.team_name = team_name;
    }

    public Team(int team_id, String class_id, String topic_code, String topic_name, String gitlab_url, int status) {
        this.team_id = team_id;
        this.class_id = class_id;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.gitlab_url = gitlab_url;
        this.status = status;
    }

    public Team(String class_id, String topic_code, String topic_name, String gitlab_url, int status, String team_name) {
        this.class_id = class_id;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.gitlab_url = gitlab_url;
        this.status = status;
        this.team_name = team_name;
    }

    public Team(String class_id, String class_code, String topic_code, String topic_name, String gitlab_url, int status, String team_name) {
        this.class_id = class_id;
        this.class_code = class_code;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.gitlab_url = gitlab_url;
        this.status = status;
        this.team_name = team_name;
    }

    public Team(int team_id, String class_id, String class_code, String topic_code, String topic_name, String gitlab_url, int status, String team_name) {
        this.team_id = team_id;
        this.class_id = class_id;
        this.class_code = class_code;
        this.topic_code = topic_code;
        this.topic_name = topic_name;
        this.gitlab_url = gitlab_url;
        this.status = status;
        this.team_name = team_name;
    }



    public Team(String class_id, String class_code) {
        this.class_id = class_id;
        this.class_code = class_code;
    }


    public Team() {
    }

    public Team(String team_name) {
        this.team_name = team_name;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getTopic_code() {
        return topic_code;
    }

    public void setTopic_code(String topic_code) {
        this.topic_code = topic_code;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getGitlab_url() {
        return gitlab_url;
    }

    public void setGitlab_url(String gitlab_url) {
        this.gitlab_url = gitlab_url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Class_s getClassroom(){
        DAOChangePass dao = new DAOChangePass();
        return dao.viewClassById(class_id);
    }

    @Override
    public String toString() {
        return "Team{" + "team_id=" + team_id + ", class_id=" + class_id + ", topic_code=" + topic_code + ", topic_name=" + topic_name + ", gitlab_url=" + gitlab_url + ", status=" + status + ", team_name=" + team_name + '}';
    }

}
