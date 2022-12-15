package com.management.entity;

import com.management.dao.teamevaluation.DAOTeamEvaluation;

public class Class_s {
    private int id;
    private String ClassCode;
    private String trainerId;
    private String SubjectId;
    private String ClassYear;
    private String ClassTerm;
    private String Block5Class;
    private int status;
    private String note;

    public Class_s() {
    }

    public Class_s(int id, String classCode, String trainerId, String subjectId, String classYear, String classTerm, String block5Class, int status, String note) {
        this.id = id;
        ClassCode = classCode;
        this.trainerId = trainerId;
        SubjectId = subjectId;
        ClassYear = classYear;
        ClassTerm = classTerm;
        Block5Class = block5Class;
        this.status = status;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Class_s(int id, String ClassCode) {
        this.id = id;
        this.ClassCode = ClassCode;
    }

    public Class_s(int id, String ClassCode, String trainerId, String SubjectId, String ClassYear, String ClassTerm, String Block5Class, int status) {
        this.id = id;
        this.ClassCode = ClassCode;
        this.trainerId = trainerId;
        this.SubjectId = SubjectId;
        this.ClassYear = ClassYear;
        this.ClassTerm = ClassTerm;
        this.Block5Class = Block5Class;
        this.status = status;
    }

    public Class_s(String trainerId) {
        this.trainerId = trainerId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String ClassCode) {
        this.ClassCode = ClassCode;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String SubjectId) {
        this.SubjectId = SubjectId;
    }

    public String getClassYear() {
        return ClassYear;
    }

    public void setClassYear(String ClassYear) {
        this.ClassYear = ClassYear;
    }

    public String getClassTerm() {
        return ClassTerm;
    }

    public void setClassTerm(String ClassTerm) {
        this.ClassTerm = ClassTerm;
    }

    public String getBlock5Class() {
        return Block5Class;
    }

    public void setBlock5Class(String Block5Class) {
        this.Block5Class = Block5Class;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getTrainer() {
        DAOTeamEvaluation dao = new DAOTeamEvaluation();
        return dao.getUserByID(trainerId);
    }

    @Override
    public String toString() {
        return "Class_s{" +
                "id=" + id +
                ", ClassCode='" + ClassCode + '\'' +
                ", trainerId='" + trainerId + '\'' +
                ", SubjectId='" + SubjectId + '\'' +
                ", ClassYear='" + ClassYear + '\'' +
                ", ClassTerm='" + ClassTerm + '\'' +
                ", Block5Class='" + Block5Class + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                '}';
    }
}
