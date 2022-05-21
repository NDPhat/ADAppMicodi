package com.example.appcomidi.Model;

public class Address {
    private int id;
    private String homenumber;
    private String street;
    private String city;
    private int userid;

    public Address() {
    }

    public Address(int id, String homenumber, String street, String city, int userid) {
        this.id = id;
        this.homenumber = homenumber;
        this.street = street;
        this.city = city;
        this.userid = userid;
    }
    public Address( String homenumber, String street, String city, int userid) {

        this.homenumber = homenumber;
        this.street = street;
        this.city = city;
        this.userid = userid;
    }
    public Address(int id, String homenumber, String street, String city) {
        this.id = id;
        this.homenumber = homenumber;
        this.street = street;
        this.city = city;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHomenumber() {
        return homenumber;
    }

    public void setHomenumber(String homenumber) {
        this.homenumber = homenumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
