package com.example.myapplication;

public class UserLocationData {

    private String user_location;

    public String getUser_location(){

        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    private static UserLocationData instance = null;

    public static synchronized UserLocationData getInstance(){
        if(null == instance){
            instance = new UserLocationData();
        }
        return instance;
    }
}
