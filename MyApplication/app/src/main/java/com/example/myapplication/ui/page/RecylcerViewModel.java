package com.example.myapplication.ui.page;

import android.net.Uri;

public class RecylcerViewModel {



    private String study_name;
    private String study_photo;
    private String study_company;
    private String study_studycafe;
    private String study_number;
    private String study_explain;
    private String study_category_low;
    private String study_primary;

    public RecylcerViewModel(){}

    public String getStudy_name() {
        return study_name;
    }

    public void setStudy_name(String study_name) {
        this.study_name = study_name;
    }

    public String getStudy_photo() {
        return study_photo;
    }

    public void setStudy_photo(String study_photo) {
        this.study_photo = study_photo;
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

    public String getStudy_category_low() {
        return study_category_low;
    }

    public void setStudy_category_low(String study_category_low) {
        this.study_category_low = study_category_low;
    }

    public String getStudy_number() {
        return study_number;
    }

    public void setStudy_number(String study_number) {
        this.study_number = study_number;
    }

    public String getStudy_explain() {
        return study_explain;
    }

    public void setStudy_explain(String study_explain) {
        this.study_explain = study_explain;
    }

    public String getStudy_primary() {
        return study_primary;
    }

    public void setStudy_primary(String study_primary) {
        this.study_primary = study_primary;
    }
}