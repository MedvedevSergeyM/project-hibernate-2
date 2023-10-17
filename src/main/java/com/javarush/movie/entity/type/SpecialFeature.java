package com.javarush.movie.entity.type;

import java.util.Arrays;

public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String name;

    SpecialFeature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SpecialFeature getByName(String name) {
        return Arrays.stream(SpecialFeature.values())
                .filter(rating -> rating.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
