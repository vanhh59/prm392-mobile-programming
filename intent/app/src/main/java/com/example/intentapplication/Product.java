package com.example.intentapplication;

public class Product {
    private String name;
    private int image;

    public Product(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
