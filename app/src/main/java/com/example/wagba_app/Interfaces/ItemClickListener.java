package com.example.wagba_app.Interfaces;


public interface ItemClickListener {
    public default void click(int position) {
    }

    public default void addToCart(String name, String price, String link){

    }
}