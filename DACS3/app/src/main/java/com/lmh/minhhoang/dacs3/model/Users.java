package com.lmh.minhhoang.dacs3.model;

public class Users {
    private String email;
    private String password;
    private String name;

//    public Users(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }

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
}
