package com.example.wagba_app.Models;

public class CartData {

    private String title;
    private int imgid;
    private String price;
    private int spinnerValue;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(int imgid) {
        this.price = price;
    }

    public int getSpinnerValue() {
        return spinnerValue;
    }

    public void setSpinnerValue(int spinnerValue) {
        this.spinnerValue = spinnerValue;
    }





    public CartData(String title, int imgid, String price, int spinnerValue) {
        this.title = title;
        this.imgid = imgid;
        this.price = price;
        this.spinnerValue = spinnerValue;
    }
}
