package com.example.Nhacollection.Domain;

import java.io.Serializable;

public class Feature implements Serializable {
    String Description ;
    String img_url;
    String Name;
    Double Price ;
    Double Shippingcost;
    public Feature() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Double getShippingcost() {
        return Shippingcost;
    }

    public void setShippingcost(Double shippingcost) {
        Shippingcost = shippingcost;
    }
}
