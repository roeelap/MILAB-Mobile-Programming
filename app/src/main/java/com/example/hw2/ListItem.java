package com.example.hw2;

public class ListItem {
    public static int itemId = 0;

    public int id;
    public String name;
    public int image;

    public ListItem(String name, int image) {
        this.id = ++itemId;
        this.name = name;
        this.image = image;
    }
}
