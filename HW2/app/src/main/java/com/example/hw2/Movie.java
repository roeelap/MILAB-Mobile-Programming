package com.example.hw2;

public class Movie {

    public String name;
    public String score;
    public int image;

    public Movie(String name, String score, int image) {
        this.name = name;
        this.score = String.format("Score: %s", score);
        this.image = image;
    }
}
