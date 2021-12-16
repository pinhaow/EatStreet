package com.example.EatStreet;

public class IdHolder {

    public enum UserType{
        User, Driver, Owner
    }

    private int id;

    private UserType userType;

    public IdHolder(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}