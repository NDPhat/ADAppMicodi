package com.example.appcomidi.Model;

public class Users {
    private int id;
    private String name;
    private String phone;
    private byte [] image;
    private int idrole;
    private int accid;



    public Users(int id, String name, String phone, int idrole) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.accid=accid;
        this.idrole=idrole;
    }

    public Users(int id, String name, String phone, byte[] image, int idrole, int accid) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.accid=accid;
        this.idrole=idrole;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }
    public Users(int id, String name, String phone, byte[] image,int address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = image;


    }

    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
