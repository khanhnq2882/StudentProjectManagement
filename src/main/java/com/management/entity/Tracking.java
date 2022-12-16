package com.management.entity;

public class Tracking {
    private int tracking_id;
    private int team_id_id;
    private int milestone_id;
    private int function_id;
    private int assigner_id;
    private int assignee_id;
    private String tracking_note;
    private String update;
    private int status;
    private String team_name;
    private String milestone_name;
    private String function_name;
    private String assigner_name;
    private String assignee_name;

    public Tracking() {
    }

    public Tracking(int tracking_id, int team_id_id, int milestone_id, int function_id, int assigner_id, int assignee_id, String tracking_note, String update, int status, String team_name, String milestone_name, String function_name, String assigner_name, String assignee_name) {
        this.tracking_id = tracking_id;
        this.team_id_id = team_id_id;
        this.milestone_id = milestone_id;
        this.function_id = function_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.tracking_note = tracking_note;
        this.update = update;
        this.status = status;
        this.team_name = team_name;
        this.milestone_name = milestone_name;
        this.function_name = function_name;
        this.assigner_name = assigner_name;
        this.assignee_name = assignee_name;
    }

    public int getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(int tracking_id) {
        this.tracking_id = tracking_id;
    }

    public int getTeam_id_id() {
        return team_id_id;
    }

    public void setTeam_id_id(int team_id_id) {
        this.team_id_id = team_id_id;
    }

    public int getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }

    public int getFunction_id() {
        return function_id;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }

    public int getAssigner_id() {
        return assigner_id;
    }

    public void setAssigner_id(int assigner_id) {
        this.assigner_id = assigner_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getTracking_note() {
        return tracking_note;
    }

    public void setTracking_note(String tracking_note) {
        this.tracking_note = tracking_note;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
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

    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getAssigner_name() {
        return assigner_name;
    }

    public void setAssigner_name(String assigner_name) {
        this.assigner_name = assigner_name;
    }

    public String getAssignee_name() {
        return assignee_name;
    }

    public void setAssignee_name(String assignee_name) {
        this.assignee_name = assignee_name;
    }

    @Override
    public String toString() {
        return "Tracking{" + "tracking_id=" + tracking_id + ", team_id_id=" + team_id_id + ", milestone_id=" + milestone_id + ", function_id=" + function_id + ", assigner_id=" + assigner_id + ", assignee_id=" + assignee_id + ", tracking_note=" + tracking_note + ", update=" + update + ", status=" + status + ", team_name=" + team_name + ", milestone_name=" + milestone_name + ", function_name=" + function_name + ", assigner_name=" + assigner_name + ", assignee_name=" + assignee_name + '}';
    }
    
}
