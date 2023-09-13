package com.javarush.movie.type.converter;

import com.javarush.movie.type.SpecialFeature;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class SpecialFeaturesConverter implements AttributeConverter<Set<SpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(Set<SpecialFeature> specialFeatures) {
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return null;
        }
        return specialFeatures.stream()
                .map(SpecialFeature::getName)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<SpecialFeature> convertToEntityAttribute(String names) {
        if (names == null) {
            return Set.of();
        }
        return Arrays.stream(names.split(","))
                .map(SpecialFeature::getByName)
                .collect(Collectors.toSet());
    }
}

