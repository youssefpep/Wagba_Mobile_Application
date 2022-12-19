package com.example.wagba_app.Models;

public class PreviousOrdersData {
    private String title;
    private String description;
    private String price;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription(){return description;}
    public void setDescription (String description) {this.description = description;}

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public PreviousOrdersData(String title, String description, String price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
