package com.example.myapplication;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

public class AddModel {

    public String study_name;

    public String study_photo;
    public String study_category_high;
    public String study_category_low;
    public String study_company;
    public String study_studycafe;
    public String study_number;
    public boolean study_isadd;
    public boolean study_isonoff;
    public boolean study_islocal;
    public String study_explain;
    public String study_date;
    public String study_primary;
    public String study_masterID;
    public String study_master;
    public String study_location;

    public Map<String, Boolean> join = new HashMap<>();


    public AddModel(String study_name, String study_category_high, String study_category_low, String study_company,
                    String study_studycafe, String study_number, boolean study_isadd, boolean study_isonoff, boolean study_islocal,
                    String study_explain, String study_primary, String study_date, String study_masterID, String study_master,
                    String study_photo, String study_location) {
        this.study_name = study_name;
        this.study_category_high = study_category_high;
        this.study_category_low = study_category_low;
        this.study_company = study_company;
        this.study_studycafe = study_studycafe;
        this.study_number = study_number;
        this.study_isadd = study_isadd;
        this.study_isonoff = study_isonoff;
        this.study_explain = study_explain;
        this.study_islocal = study_islocal;
        this.study_primary = study_primary;
        this.study_date = study_date;
        this.study_masterID = study_masterID;
        this.study_master = study_master;
        this.study_photo = study_photo;
        this.study_location = study_location;
    }


    public String getStudy_location() {
        return study_location;
    }

    public void setStudy_location(String study_location) {
        this.study_location = study_location;
    }

    public String getStudy_photo() {
        return study_photo;
    }

    public void setStudy_photo(String study_photo) {
        this.study_photo = study_photo;
    }

    public String getStudy_master() {
        return study_master;
    }

    public void setStudy_master(String study_master) {
        this.study_master = study_master;
    }

    public AddModel(String study_masterID) {
        this.study_masterID = study_masterID;
    }


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
        return study_isonoff;
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

    public String getStudy_date() {
        return study_date;
    }

    public void setStudy_date(String study_date) {
        this.study_date = study_date;
    }

    public String getStudy_primary() {
        return study_primary;
    }

    public void setStudy_primary(String study_primary) {
        this.study_primary = study_primary;
    }

    public String getStudy_masterID() {
        return study_masterID;
    }

    public void setStudy_masterID(String study_masterID) {
        this.study_masterID = study_masterID;
    }

    @Override
    public String toString() {
        return "Study{" +
                "name='" + study_name + '\'' +
                "high category='" + study_category_high + '\'' +
                ", low category='" + study_category_low + '\'' +
                ", company='" + study_company + '\'' +
                ", study cafe='" + study_studycafe + '\'' +
                ", number='" + study_number + '\'' +
                ", isadd='" + study_isadd + '\'' +
                ", isonoff='" + study_isonoff + '\'' +
                ", islocal='" + study_islocal + '\'' +
                ", explain='" + study_explain + '\'' +
                ", study primary key='" + study_primary + '\'' +
                ", study date='" + study_date + '\'' +
                ", study masterID='" + study_masterID + '\'' +
                ", study master='" + study_master + '\'' +
                ", study photo='" + study_photo + '\'' +
                ", study location='" + study_location + '\'' +
                '}';
    }

}