package com.example.appcomidi.Model;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int userid;
    private String date;
    private double tongtien;
    private String tinhtrang;


    public Order() {
    }

    public Order(int id, int userid, String date, double tongtien) {
        this.id = id;
        this.userid = userid;
        this.date = date;
        this.tongtien = tongtien;
    }
    public Order(int id, int userid, String date) {
        this.id = id;
        this.userid = userid;
        this.date = date;
        this.tongtien = tongtien;
    }
    public Order( int userid, String date, double tongtien) {
        this.userid = userid;
        this.date = date;
        this.tongtien = tongtien;
    }

    public Order(int id, int userid, String date,String tinhtrang,double tongtien) {
        this.id = id;
        this.userid = userid;
        this.date = date;
        this.tongtien = tongtien;
        this.tinhtrang = tinhtrang;
    }
    public Order( int userid, String date, String tinhtrang) {
        this.userid = userid;
        this.date = date;
        this.tinhtrang = tinhtrang;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
}

