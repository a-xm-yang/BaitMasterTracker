package com.example.baitmastertracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String email;
    private String password;
    private String phoneNum;
    private String recoveryNum;
    private HashMap<String, String> Img;
    private HashMap<String, LocationTime> Location;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRecoveryNum() {
        return recoveryNum;
    }

    public void setRecoveryNum(String recoveryNum) {
        this.recoveryNum = recoveryNum;
    }

    public HashMap<String, LocationTime> getLocation() {
        return Location;
    }

    public void setLocation(HashMap<String, LocationTime> location) {
        Location = location;
    }

    public HashMap<String, String> getImg() {
        return Img;
    }

    public void setImg(HashMap<String, String> img) {
        Img = img;
    }

    public List<String> getAllImageUrl(){

        if (Img == null){
            Img = new HashMap<>();
        }

        ArrayList<String> list = new ArrayList<>(Img.values());
        return list;
    }

    public List<LocationTime> getAllLocationTime(){

        if(Location == null){
            Location = new HashMap<>();
        }

        ArrayList<LocationTime> list = new ArrayList<>(Location.values());
        System.out.println("H:" + list);
        return list;
    }
}
