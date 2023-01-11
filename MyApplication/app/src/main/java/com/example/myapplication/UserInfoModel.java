package com.example.myapplication;



public class UserInfoModel {



    public String user_name;
    public String user_email;
    public String uid;
    public String comment;
    public String pushToken;
    public String user_id;
    public String status;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {

        return user_id;

    }


    public void setUser_id(String user_id) {

        this.user_id = user_id;

    }


    private static UseridData instance = null;


    public static synchronized UseridData getInstance(){

        if(null==instance){

            instance = new UseridData();

        }

        return instance;

    }





}