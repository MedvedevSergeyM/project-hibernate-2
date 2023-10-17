package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "city", schema = "movie", indexes = {
        @Index(name = "idx_fk_country_id", columnList = "country_id")})
public class City extends LastUpdate {

    @Column(name = "city_id", columnDefinition = "smallint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "city", length = 50, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "fk_city_country",
            foreignKeyDefinition = "foreign key (country_id) references country (country_id) on update cascade"))
    private Country country;

//    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
//    private Set<Address> addresses = new HashSet<>();

}
