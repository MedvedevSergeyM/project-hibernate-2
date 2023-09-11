package com.javarush.movie.entity;

public enum SpecialFeatures {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String name;

    SpecialFeatures(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
