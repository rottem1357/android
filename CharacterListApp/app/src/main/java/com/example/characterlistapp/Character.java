package com.example.characterlistapp;

public class Character {
    private final String name;
    private final String description;
    private final int imageResourceId;

    // Constructor
    public Character(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
