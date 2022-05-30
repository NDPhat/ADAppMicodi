package com.example.appcomidi.Model;

import android.text.TextUtils;
import android.util.Patterns;

public class Account {
    private int id;
    private String email;
    private String pass;
    private String repass;
    private String Active;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public Account(int id, String email, String pass, String repass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.repass = repass;
    }

    public Account(String email, String pass, String repass) {
        this.email = email;
        this.pass = pass;
        this.repass = repass;
    }
    public Account(String email, String pass) {
        this.email = email;
        this.pass = pass;

    }
    public Account(String email, String pass,String active,String no) {
        this.email = email;
        this.pass = pass;
        this.Active=active;


    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public boolean isValidEmail()
    {
        return  !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean isValidPassword()
    {
        return  !TextUtils.isEmpty(email) && pass.length()>=6;
    }

}
