package com.example.wagba_app.Models;

public class CartData {

    private String title;
    private String image;
    private String price;
    //private int spinnerValue;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    //public int getSpinnerValue() {
        //return spinnerValue;
    //}

    //public void setSpinnerValue(int spinnerValue) {
        //this.spinnerValue = spinnerValue;
    //}





    public CartData() {
        this.title = title;
        this.image = image;
        this.price = price;
        //this.spinnerValue = spinnerValue;
    }
}
