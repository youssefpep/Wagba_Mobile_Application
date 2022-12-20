package com.example.wagba_app.Models;

import android.net.Uri;

import java.net.URI;

public class RestaurantData {
    private String name;
    private String description;
    private String image;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
