package com.example.appcomidi.Model;

public class Rating {
    private int id;
    private String email;
    private int uid;
    private int fid;
    private int sosao;
    private String cmt;
    private byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public Rating(int id, String email, int uid, int fid, int sosao, String cmt, byte[] photo) {
        this.id = id;
        this.email = email;
        this.uid = uid;
        this.fid = fid;
        this.sosao = sosao;
        this.cmt = cmt;
        this.photo = photo;
    }
    public Rating(int id, String email, int uid, int sosao, String cmt, byte[] photo) {
        this.id = id;
        this.email = email;
        this.uid = uid;
        this.sosao = sosao;
        this.cmt = cmt;
        this.photo = photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Rating() {
    }

    public Rating(int id, String email, int uid, int fid, int sosao, String cmt) {
        this.id = id;
        this.email = email;
        this.uid = uid;
        this.fid = fid;
        this.sosao = sosao;
        this.cmt = cmt;
    }
    public Rating( String email, int uid, int fid, int sosao, String cmt) {
        this.email = email;
        this.uid = uid;
        this.fid = fid;
        this.sosao = sosao;
        this.cmt = cmt;
    }


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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getSosao() {
        return sosao;
    }

    public void setSosao(int sosao) {
        this.sosao = sosao;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
}
