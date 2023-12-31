package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "country", schema = "movie")
public class Country extends LastUpdate {

    @Column(name = "country_id", nullable = false, columnDefinition = "smallint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "country", length = 50, nullable = false)
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
//    private Set<City> cities = new HashSet<>();

}
