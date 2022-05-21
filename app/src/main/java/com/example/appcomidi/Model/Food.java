package com.example.appcomidi.Model;

import java.io.Serializable;

public class Food implements Serializable {
    public int id;
    public String name;
    public int price;
    public int idcate;
    public byte[] Image;
    public int soluong;
    public String tinhtrang;
    private int idfoodinfo;

    public Food(int id, String name, int price, int idcate, byte[] image, int soluong, String tinhtrang, int idfoodinfo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idcate = idcate;
        this.Image = image;
        this.soluong = soluong;
        this.tinhtrang = tinhtrang;
        this.idfoodinfo = idfoodinfo;
    }
    public Food( String name, int price, int idcate, byte[] image, int soluong, String tinhtrang, int idfoodinfo) {
        this.name = name;
        this.price = price;
        this.idcate = idcate;
        Image = image;
        this.soluong = soluong;
        this.tinhtrang = tinhtrang;
        this.idfoodinfo = idfoodinfo;
    }

    public Food(int id, String name, int price, int idcate, byte[] image, int soluong, int idfoodinfo, String tinhtrang) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idcate = idcate;
        Image = image;
        this.soluong = soluong;
        this.idfoodinfo = idfoodinfo;
        this.tinhtrang = tinhtrang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getIdfoodinfo() {
        return idfoodinfo;
    }

    public void setIdfoodinfo(int idfoodinfo) {
        this.idfoodinfo = idfoodinfo;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public int getIdcate() {
        return idcate;
    }

    public void setIdcate(int idcate) {
        this.idcate = idcate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }
    public Food(int id, String name, int price, int idcate, byte[] image, int soluong, String tinhtrang) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idcate = idcate;
        Image = image;
        this.soluong = soluong;
        this.tinhtrang = tinhtrang;
    }

    public Food() {
    }

    public Food(String name, int price,byte[] image) {
        this.name = name;
        this.price = price;
        Image = image;
    }

    public Food(int id, String name, int price, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        Image = image;
    }

    public Food(int id, String name, int price, byte[] image, int idcate) {
        this.id = id;
        this.name = name;
        this.price = price;
        Image = image;
        this.idcate = idcate;
    }

    public Food(String name, byte[] image) {
        this.name = name;
        Image = image;
    }
}
