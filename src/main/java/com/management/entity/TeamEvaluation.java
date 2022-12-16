/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.entity;

/**
 *
 * @author tqbao
 */
public class TeamEvaluation extends Class_s{
    String trainer_name;
    int team_eva_id;
    int eva_id;
    int criteria_id;
    int team_id;
    int grade;
    String note;

    public TeamEvaluation() {
    }

    public TeamEvaluation(String trainer_name, int team_eva_id, int eva_id, int criteria_id, int team_id, int grade, String note) {
        this.trainer_name = trainer_name;
        this.team_eva_id = team_eva_id;
        this.eva_id = eva_id;
        this.criteria_id = criteria_id;
        this.team_id = team_id;
        this.grade = grade;
        this.note = note;
    }

    public TeamEvaluation(int team_eva_id, int eva_id, int criteria_id, int team_id, int grade, String note) {
        this.team_eva_id = team_eva_id;
        this.eva_id = eva_id;
        this.criteria_id = criteria_id;
        this.team_id = team_id;
        this.grade = grade;
        this.note = note;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public TeamEvaluation(int team_id) {
        this.team_id = team_id;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public int getTeam_eva_id() {
        return team_eva_id;
    }

    public void setTeam_eva_id(int team_eva_id) {
        this.team_eva_id = team_eva_id;
    }

    public int getEva_id() {
        return eva_id;
    }

    public void setEva_id(int eva_id) {
        this.eva_id = eva_id;
    }

    public int getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

    @Override
    public String toString() {
        return "TeamEvaluation{" + "trainer_name=" + trainer_name + ", team_eva_id=" + team_eva_id + ", eva_id=" + eva_id + ", criteria_id=" + criteria_id + ", team_id=" + team_id + ", grade=" + grade + ", note=" + note + '}';
    }

}
