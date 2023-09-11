package com.javarush.movie.converter;

import com.javarush.movie.entity.SpecialFeatures;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class SpecialFeaturesConverter implements AttributeConverter<SpecialFeatures, String> {
    @Override
    public String convertToDatabaseColumn(SpecialFeatures specialFeatures) {
        return specialFeatures.getName();
    }

    @Override
    public SpecialFeatures convertToEntityAttribute(String name) {
        return Arrays.stream(SpecialFeatures.values())
                .filter(rating -> rating.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

