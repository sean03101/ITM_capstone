package com.example.myapplication;

import android.net.Uri;

class AddmeetingModel {

    public AddmeetingModel(){}
    public String a_name;
    public String a_primary;





    public String getA_primary() {
        return a_primary;
    }

    public void setA_primary(String a_primary) {
        this.a_primary = a_primary;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String study_category_high;
    public String study_category_low;
    public String study_company;



    public boolean study_ishab;


    public String study_explain;
    public String study_explain2;
    public String study_explain3;

    public AddmeetingModel(String a_name, String a_primary, String study_category_high, String study_category_low,
                           String study_company, boolean study_ishab, String study_explain, String study_explain2, String study_explain3, String study_explain4, String study_explain5, String study_explain6, String study_explain7, String study_explain8, String study_explain9, String study_explain10) {
        this.a_name = a_name;
        this.a_primary = a_primary;
        this.study_category_high = study_category_high;
        this.study_category_low = study_category_low;
        this.study_company = study_company;
        this.study_ishab = study_ishab;
        this.study_explain = study_explain;
        this.study_explain2 = study_explain2;
        this.study_explain3 = study_explain3;
        this.study_explain4 = study_explain4;
        this.study_explain5 = study_explain5;
        this.study_explain6 = study_explain6;
        this.study_explain7 = study_explain7;
        this.study_explain8 = study_explain8;
        this.study_explain9 = study_explain9;
        this.study_explain10 = study_explain10;
    }

    public String study_explain4;
    public String study_explain5;

    public String getStudy_explain3() {
        return study_explain3;
    }

    public void setStudy_explain3(String study_explain3) {
        this.study_explain3 = study_explain3;
    }

    public String getStudy_explain4() {
        return study_explain4;
    }

    public void setStudy_explain4(String study_explain4) {
        this.study_explain4 = study_explain4;
    }

    public String getStudy_explain5() {
        return study_explain5;
    }

    public void setStudy_explain5(String study_explain5) {
        this.study_explain5 = study_explain5;
    }

    public String getStudy_explain6() {
        return study_explain6;
    }

    public void setStudy_explain6(String study_explain6) {
        this.study_explain6 = study_explain6;
    }

    public String getStudy_explain7() {
        return study_explain7;
    }

    public void setStudy_explain7(String study_explain7) {
        this.study_explain7 = study_explain7;
    }

    public String getStudy_explain8() {
        return study_explain8;
    }

    public void setStudy_explain8(String study_explain8) {
        this.study_explain8 = study_explain8;
    }

    public String getStudy_explain9() {
        return study_explain9;
    }

    public void setStudy_explain9(String study_explain9) {
        this.study_explain9 = study_explain9;
    }

    public String getStudy_explain10() {
        return study_explain10;
    }

    public void setStudy_explain10(String study_explain10) {
        this.study_explain10 = study_explain10;
    }

    public String study_explain6;
    public String study_explain7;
    public String study_explain8;
    public String study_explain9;
    public String study_explain10;




    public String getStudy_category_high() {
        return study_category_high;
    }

    public void setStudy_category_high(String study_category_high) {
        this.study_category_high = study_category_high;
    }

    public String getStudy_category_low(){
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




    public boolean isStudy_ishab() {
        return study_ishab;
    }

    public void setStudy_ishab(boolean study_ishab) {
        this.study_ishab = study_ishab;
    }




    public String getStudy_explain() {
        return study_explain;
    }

    public void setStudy_explain(String study_explain) {
        this.study_explain = study_explain;
    }

    public String getStudy_explain2() {
        return study_explain2;
    }

    public void setStudy_explain2(String study_explain2) {
        this.study_explain2 = study_explain2;
    }


    @Override
    public String toString() {
        return "Study{" +

                "high category='" + study_category_high + '\'' +
                ", low category='" + study_category_low + '\'' +
                ", company='" + study_company + '\'' +

                ", ishab='" + study_ishab + '\'' +


                ", explain='" + study_explain + '\'' +
                ", explain2='" + study_explain2 + '\'' +
                '}';
    }

}