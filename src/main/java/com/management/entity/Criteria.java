package com.management.entity;

import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class Criteria {
    private int subject_id;
    private int criteria_id;
    private int iteration_id;
    private double evaluation_weight;
    private boolean team_evaluation;
    private String criteria_order;
    private int max_loc;
    private int status;
    private String evaluation_title;
    private String description;
    private String iteration_name;
    private String subject_name;
    private String subject_code;
    DecimalFormat format = new DecimalFormat("0.#");

    public Criteria() {
    }

    public Criteria(int criteria_id, int iteration_id, double evaluation_weight, boolean team_evaluation, String criteria_order, int max_loc, int status, String evaluation_title,String description) {
        this.criteria_id = criteria_id;
        this.iteration_id = iteration_id;
        this.evaluation_title = evaluation_title;
        this.evaluation_weight = evaluation_weight;
        this.team_evaluation = team_evaluation;
        this.criteria_order = criteria_order;
        this.max_loc = max_loc;
        this.status = status;
        this.description = description;
    }

    public Criteria(int iteration_id, double evaluation_weight, boolean team_evaluation, String criteria_order, int max_loc, int status,String evaluation_title, String description) {
        this.iteration_id = iteration_id;
        this.evaluation_title = evaluation_title;
        this.evaluation_weight = evaluation_weight;
        this.team_evaluation = team_evaluation;
        this.criteria_order = criteria_order;
        this.max_loc = max_loc;
        this.status = status;
        this.description = description;
    }

    public Criteria(int criteria_id, int iteration_id, double evaluation_weight, boolean team_evaluation, String criteria_order, int max_loc, int status,String evaluation_title,String description, String iteration_name, String subject_code, int subject_id) {
        this.criteria_id = criteria_id;
        this.iteration_id = iteration_id;
        this.iteration_name = iteration_name;
        this.evaluation_title = evaluation_title;
        this.evaluation_weight = evaluation_weight;
        this.team_evaluation = team_evaluation;
        this.criteria_order = criteria_order;
        this.max_loc = max_loc;
        this.status = status;
        this.subject_code = subject_code;
        this.description = description;
        this.subject_id = subject_id;
    }

    public Criteria(int subject_id, String subject_name, String subject_code) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_code = subject_code;
    }

    public Criteria(int subject_id, int iteration_id, String iteration_name, String subject_code) {
        this.subject_id = subject_id;
        this.iteration_id = iteration_id;
        this.iteration_name = iteration_name;
        this.subject_code = subject_code;
    }







    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }




    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }


    public Criteria(String iteration_name) {
        this.iteration_name = iteration_name;
    }

    public Criteria(int subject_id, String subject_name) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public String getIteration_name() {
        return iteration_name;
    }

    public void setIteration_name(String iteration_name) {
        this.iteration_name = iteration_name;
    }

    public String getEvaluation_title() {
        return evaluation_title;
    }

    public void setEvaluation_title(String evaluation_title) {
        this.evaluation_title = evaluation_title;
    }

    public Criteria(int iteration_id) {
        this.iteration_id = iteration_id;
    }

    public int getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public int getIteration_id() {
        return iteration_id;
    }

    public void setIteration_id(int iteration_id) {
        this.iteration_id = iteration_id;
    }

    public String getEvaluation_weight() {
        String n = format.format(evaluation_weight);
        return n;
    }

    public void setEvaluation_weight(double evaluation_weight) {
        this.evaluation_weight = evaluation_weight;
    }

    public boolean isTeam_evaluation() {
        return team_evaluation;
    }

    public void setTeam_evaluation(boolean team_evaluation) {
        this.team_evaluation = team_evaluation;
    }

    public String getCriteria_order() {
        return criteria_order;
    }

    public void setCriteria_order(String criteria_order) {
        this.criteria_order = criteria_order;
    }

    public int getMax_loc() {
        return max_loc;
    }

    public void setMax_loc(int max_loc) {
        this.max_loc = max_loc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Criteria{" + "subject_id=" + subject_id + " criteria_id=" + criteria_id + ", iteration_id=" + iteration_id + ", evaluation_weight=" + evaluation_weight + ", team_evaluation=" + team_evaluation + ", criteria_order=" + criteria_order + ", max_loc=" + max_loc + ", status=" + status + ", evaluation_title=" + evaluation_title + ", description=" + description + ", iteration_name=" + iteration_name + ", subject_code=" + subject_code + ", subject_name=" + subject_name +'}';
    }
}
