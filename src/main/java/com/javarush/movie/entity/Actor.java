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
@Table(name = "actor", schema = "movie", indexes = {
        @Index(name = "idx_actor_last_name", columnList = "last_name")
})

public class Actor extends LastUpdate {

    @Column(name = "actor_id", columnDefinition = "smallint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    // TODO: last_update
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="film_actor",
            joinColumns=  @JoinColumn(name="actor_id", referencedColumnName="actor_id"),
            inverseJoinColumns= @JoinColumn(name="film_id", referencedColumnName="film_id"),
            foreignKey = @ForeignKey(name = "fk_film_actor_film",
                    foreignKeyDefinition = "foreign key (film_id) references film (film_id) on update cascade"),
            inverseForeignKey = @ForeignKey(name = "fk_film_actor_actor",
                    foreignKeyDefinition = "foreign key (actor_id) references actor (actor_id) on update cascade"),
            indexes = {@Index(name = "idx_fk_film_id", columnList = "film_id")}
    )
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Film> films = new HashSet<>();

}
