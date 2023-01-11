package com.example.myapplication;

import com.example.myapplication.UseridData;

import java.io.Serializable;
class CVAddData implements Serializable {

    public String study_name;

    public String getStudy_name() {
        return study_name;
    }

    public void setStudy_name(String study_name) {
        this.study_name = study_name;
    }

    public String study_category_high;
    public String study_category_low;
    public String study_company;

    public String study_explain;
    public String study_explain1;

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




    public String getStudy_explain() {
        return study_explain;
    }

    public void setStudy_explain(String study_explain) {
        this.study_explain = study_explain;
    }

    public String getStudy_explain1() {
        return study_explain1;
    }

    public void setStudy_explain1(String study_explain1) {
        this.study_explain1 = study_explain1;
    }

}