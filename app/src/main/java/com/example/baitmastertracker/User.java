package com.example.baitmastertracker;

public class User {

    private String email;
    private String password;
    private String phoneNum;
    private String recoveryNum;

    public User(){}

    public User(String email, String password, String phoneNum, String recoveryNum) {
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.recoveryNum = recoveryNum;
    }

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
}
