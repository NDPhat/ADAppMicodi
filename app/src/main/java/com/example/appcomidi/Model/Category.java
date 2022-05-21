package com.example.appcomidi.Model;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private byte[] image;
    private List<Food> list;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getList() {

        return list;
    }

    public void setList(List<Food> list) {
        this.list = list;
    }
    public Category() {
    }
    public Category(String name, List<Food> list) {
        this.name = name;
        this.list = list;
    }

    public Category(int id, String name, byte[] image, List<Food> list) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.list = list;
    }
    public Category(int id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;

    }

}
