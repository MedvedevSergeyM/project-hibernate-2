package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "category", schema = "movie")
public class Category extends LastUpdate {

    @Column(name = "category_id", columnDefinition = "tinyint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Film> films = new HashSet<>();

}
