/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.entity;

public class Function {
    private String function_id;
    private String team_id;
    private String function_name;
    private String features_id;
    private String access_roles;
    private String description;
    private String complexity_id;
    private String own_id;
    private String priority;
    private String status;

    public Function() {
    }

    public Function(String function_id, String team_id, String function_name, String features_id, String access_roles, String description, String complexity_id, String own_id, String priority, String status) {
        this.function_id = function_id;
        this.team_id = team_id;
        this.function_name = function_name;
        this.features_id = features_id;
        this.access_roles = access_roles;
        this.description = description;
        this.complexity_id = complexity_id;
        this.own_id = own_id;
        this.priority = priority;
        this.status = status;
    }
    

    public String getFunction_id() {
        return function_id;
    }

    public void setFunction_id(String function_id) {
        this.function_id = function_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getFeatures_id() {
        return features_id;
    }

    public void setFeatures_id(String features_id) {
        this.features_id = features_id;
    }

    public String getAccess_roles() {
        return access_roles;
    }

    public void setAccess_roles(String access_roles) {
        this.access_roles = access_roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComplexity_id() {
        return complexity_id;
    }

    public void setComplexity_id(String complexity_id) {
        this.complexity_id = complexity_id;
    }

    public String getOwn_id() {
        return own_id;
    }

    public void setOwn_id(String own_id) {
        this.own_id = own_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Function{" + "function_id=" + function_id + ", team_id=" + team_id + ", function_name=" + function_name + ",        features_id=" + features_id + ", access_roles=" + access_roles + ", description=" + description + ", complexity_id=" + complexity_id + ", own_id=" + own_id + ", priority=" + priority + ", status=" + status + '}';
    }
    
}
