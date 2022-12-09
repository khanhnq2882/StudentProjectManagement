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
public class Setting {

    private int setting_id;
    private int type_id;
    private String setting_title;
    private String setting_value;
    private String display_order;
    private int status;
    private String type;
    private String note;

    public Setting() {
    }

    public Setting(int setting_id, int type_id, String setting_title, String setting_value, String display_order, int status, String type, String note) {
        this.setting_id = setting_id;
        this.type_id = type_id;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
        this.type = type;
        this.note = note;
    }
    
    
    public Setting(int setting_id, int type_id, String setting_title, String setting_value, String display_order, int status, String note) {
        this.setting_id = setting_id;
        this.type_id = type_id;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
        this.note = note;
    }

    public Setting(int type_id, String setting_title, String setting_value, String display_order, int status, String note) {
        this.type_id = type_id;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
        this.note = note;
    }
    

    public Setting(int setting_id, String type, String setting_title, String setting_value, String display_order, int status) {
        this.setting_id = setting_id;
        this.type = type;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
        
    }
 
    
    public Setting(int setting_id, int type_id, String setting_title, String setting_value, String display_order, int status) {
        this.setting_id = setting_id;
        this.type_id = type_id;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
    }

    public Setting(int type_id, String setting_title, String setting_value, String display_order, int status) {
        this.type_id = type_id;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
  }

    public Setting(int type_id, String type) {
        this.type_id = type_id;
        this.type = type;
    }
     

    public Setting(int setting_id, int status) {
        this.setting_id = setting_id;
        this.status = status;
    }

    public Setting(int type_id) {
        this.type_id = type_id;
    }
      

    public Setting(int type_id, String setting_title, int status) {
        this.type_id = type_id;
        this.setting_title = setting_title;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getSetting_title() {
        return setting_title;
    }

    public void setSetting_title(String setting_title) {
        this.setting_title = setting_title;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    } 

    
    @Override
    public String toString() {
        return "Setting{" + "setting_id=" + setting_id + ", type_id=" + type_id + ", setting_title=" + setting_title + ", setting_value=" + setting_value + ", display_order=" + display_order + ", status=" + status + ", type=" + type + ", note=" + note + '}';
    }   
   
}
