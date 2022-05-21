package com.example.appcomidi.Model;

public class FoodInfo {
    private int id;
    private String diachigannhat;
    private double sosao;
    private double sokm;
    private int thoigian;

    public FoodInfo(int id, String diachigannhat, double sosao, double sokm, int thoigian) {
        this.id = id;
        this.diachigannhat = diachigannhat;
        this.sosao = sosao;
        this.sokm = sokm;
        this.thoigian = thoigian;
    }

    public FoodInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiachigannhat() {
        return diachigannhat;
    }

    public void setDiachigannhat(String diachigannhat) {
        this.diachigannhat = diachigannhat;
    }

    public double getSosao() {
        return sosao;
    }

    public void setSosao(double sosao) {
        this.sosao = sosao;
    }

    public double getSokm() {
        return sokm;
    }

    public void setSokm(double sokm) {
        this.sokm = sokm;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

}
