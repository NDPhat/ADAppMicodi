package com.example.appcomidi.Model;

public class OrderShipper {
    private int Id;
    private int Sid;
    private int Uid;
    private int Orderid;
    private double tongtien;
    private String nguoimua;
    private String tinhtrang;

    public OrderShipper() {
    }

    public OrderShipper(int id, int sid, int uid, int orderid, double tongtien, String nguoimua, String tinhtrang) {
        Id = id;
        Sid = sid;
        Uid = uid;
        Orderid = orderid;
        this.tongtien = tongtien;
        this.nguoimua = nguoimua;
        this.tinhtrang = tinhtrang;
    }
    public OrderShipper( int sid, int uid, int orderid, double tongtien, String nguoimua, String tinhtrang) {
        Sid = sid;
        Uid = uid;
        Orderid = orderid;
        this.tongtien = tongtien;
        this.nguoimua = nguoimua;
        this.tinhtrang = tinhtrang;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSid() {
        return Sid;
    }

    public void setSid(int sid) {
        Sid = sid;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getOrderid() {
        return Orderid;
    }

    public void setOrderid(int orderid) {
        Orderid = orderid;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getNguoimua() {
        return nguoimua;
    }

    public void setNguoimua(String nguoimua) {
        this.nguoimua = nguoimua;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
