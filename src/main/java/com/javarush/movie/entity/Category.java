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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="film_category",
            joinColumns =  @JoinColumn(name="category_id", referencedColumnName="category_id"),
            inverseJoinColumns= @JoinColumn(name="film_id", referencedColumnName="film_id"),
            foreignKey = @ForeignKey(name = "fk_film_category_category",
                    foreignKeyDefinition = "foreign key (category_id) references category (category_id) on update cascade"),
            inverseForeignKey = @ForeignKey(name = "fk_film_category_film",
                    foreignKeyDefinition = "foreign key (film_id) references film (film_id) on update cascade"))
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Film> categories = new HashSet<>();

}
