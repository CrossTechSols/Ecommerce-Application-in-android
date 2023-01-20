package com.example.Nhacollection.Domain;

import java.io.Serializable;


public class BestSell implements Serializable {
    String Description;
    String Name;
    String img_url;
    String type;
    int Price;


    public BestSell() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = (int) price;
    }

    public BestSell(String type) {
        this.type = type;
    }
}
