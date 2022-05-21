package com.example.appcomidi.Model;

public class Giohang {
    private int id;
    private int idsp;
    private String tensp;
    private int giasp;
    private int soluongsp;
    private byte[] hinhsp;
    private int cartid;

    public Giohang(int id, int idsp, String tensp, int giasp, int soluongsp, byte[] hinhsp, int cartid) {
        this.id = id;
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
        this.hinhsp = hinhsp;
        this.cartid = cartid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Giohang(int idsp, String tensp, int giasp, int soluongsp, byte[] hinhsp, int cartid) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
        this.hinhsp = hinhsp;
        this.cartid = cartid;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public byte[] getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(byte[] hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
    public Giohang() {
    }

    public Giohang(int idsp, String tensp, int giasp, byte[] hinhsp, int soluongsp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
    }
}
