/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.entity;

/**
 *
 * @author ASUS
 */
public class loc_evaluation {
    private String evaluation_id;
    private String evaluation_time;
    private String evaluation_note;
    private String complexity_id;
    private String quality_id;
    private String tracking_id;
    private String teamID;
    private String milestoneID;
    private String functionID;
    private String assignerId;
    private String assigneeId;
    private String note;
    private String update;
    private String status; //14

    public loc_evaluation() {
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(String milestoneID) {
        this.milestoneID = milestoneID;
    }

    public String getFunctionID() {
        return functionID;
    }

    public void setFunctionID(String functionID) {
        this.functionID = functionID;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public loc_evaluation(String evaluation_id, String evaluation_time, String evaluation_note, String complexity_id, String quality_id, String tracking_id, String teamID, String milestoneID, String functionID, String assignerId, String assigneeId, String note, String update, String status) {
        this.evaluation_id = evaluation_id;
        this.evaluation_time = evaluation_time;
        this.evaluation_note = evaluation_note;
        this.complexity_id = complexity_id;
        this.quality_id = quality_id;
        this.tracking_id = tracking_id;
        this.teamID = teamID;
        this.milestoneID = milestoneID;
        this.functionID = functionID;
        this.assignerId = assignerId;
        this.assigneeId = assigneeId;
        this.note = note;
        this.update = update;
        this.status = status;
    }

    public loc_evaluation(String evaluation_id, String evaluation_time, String evaluation_note, String complexity_id, String quality_id, String tracking_id) {
        this.evaluation_id = evaluation_id;
        this.evaluation_time = evaluation_time;
        this.evaluation_note = evaluation_note;
        this.complexity_id = complexity_id;
        this.quality_id = quality_id;
        this.tracking_id = tracking_id;
    }

    public String getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(String evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public String getEvaluation_time() {
        return evaluation_time;
    }

    public void setEvaluation_time(String evaluation_time) {
        this.evaluation_time = evaluation_time;
    }

    public String getEvaluation_note() {
        return evaluation_note;
    }

    public void setEvaluation_note(String evaluation_note) {
        this.evaluation_note = evaluation_note;
    }

    public String getComplexity_id() {
        return complexity_id;
    }

    public void setComplexity_id(String complexity_id) {
        this.complexity_id = complexity_id;
    }

    public String getQuality_id() {
        return quality_id;
    }

    public void setQuality_id(String quality_id) {
        this.quality_id = quality_id;
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }

    @Override
    public String toString() {
        return "loc_evaluation{" + "evaluation_id=" + evaluation_id + ", evaluation_time=" + evaluation_time + ", evaluation_note=" + evaluation_note + ", complexity_id=" + complexity_id + ", quality_id=" + quality_id + ", tracking_id=" + tracking_id + ", teamID=" + teamID + ", milestoneID=" + milestoneID + ", functionID=" + functionID + ", assignerId=" + assignerId + ", assigneeId=" + assigneeId + ", note=" + note + ", update=" + update + ", status=" + status + '}';
    }
}
