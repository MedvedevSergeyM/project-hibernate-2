package com.javarush.movie.type.converter;

import com.javarush.movie.type.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getName();
    }

    @Override
    public Rating convertToEntityAttribute(String name) {
         return Arrays.stream(Rating.values())
                 .filter(rating -> rating.getName().equals(name))
                 .findFirst()
                 .orElse(null);
    }
}
