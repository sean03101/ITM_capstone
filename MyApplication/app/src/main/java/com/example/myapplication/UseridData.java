package com.example.myapplication;

public class UseridData {

    private String user_id;

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