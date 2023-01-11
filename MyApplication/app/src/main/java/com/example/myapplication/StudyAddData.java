package com.example.myapplication;

import com.example.myapplication.UseridData;

import java.io.Serializable;

public class StudyAddData implements Serializable {

    public String study_name;
    public String study_category_high;
    public String study_category_low;
    public String study_company;
    public String study_studycafe;
    public String study_number;
    public boolean study_isadd;
    public boolean study_isonoff;
    public boolean study_islocal;
    public String study_explain;



    public String getStudy_name() {
        return study_name;
    }

    public void setStudy_name(String study_name) {
        this.study_name = study_name;
    }

    public String getStudy_category_high() {
        return study_category_high;
    }

    public void setStudy_category_high(String study_category_high) {
        this.study_category_high = study_category_high;
    }

    public String getStudy_category_low() {
        return study_category_low;
    }

    public void setStudy_category_low(String study_category_low) {
        this.study_category_low = study_category_low;
    }

    public String getStudy_company() {
        return study_company;
    }

    public void setStudy_company(String study_company) {
        this.study_company = study_company;
    }

    public String getStudy_studycafe() {
        return study_studycafe;
    }

    public void setStudy_studycafe(String study_studycafe) {
        this.study_studycafe = study_studycafe;
    }

    public String getStudy_number() {
        return study_number;
    }

    public void setStudy_number(String study_number) {
        this.study_number = study_number;
    }

    public boolean isStudy_isadd() {
        return study_isadd;
    }

    public void setStudy_isadd(boolean study_isadd) {
        this.study_isadd = study_isadd;
    }

    public boolean isStudy_isonoff() {
        return study_isonoff;
    }

    public void setStudy_isonoff(boolean study_isonoff) {
        this.study_isonoff = study_isonoff;
    }

    public boolean isStudy_islocal() {
        return study_islocal;
    }

    public void setStudy_islocal(boolean study_islocal) {
        this.study_islocal = study_islocal;
    }

    public String getStudy_explain() {
        return study_explain;
    }

    public void setStudy_explain(String study_explain) {
        this.study_explain = study_explain;
    }


}