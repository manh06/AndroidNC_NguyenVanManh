package com.example.thuchanh2;

public class Country {
    private String name;
    private int flagResource; // Đường dẫn đến lá cờ

    public Country(String name, int flagResource) {
        this.name = name;
        this.flagResource = flagResource;
    }

    public String getName() {
        return name;
    }

    public int getFlagResource() {
        return flagResource;
    }
}

