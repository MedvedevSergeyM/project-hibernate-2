package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "address", schema = "movie", indexes = {
        @Index(name = "idx_fk_city_id", columnList = "city_id")})
public class Address extends LastUpdate {

    @Column(name = "address_id", columnDefinition = "smallint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 50)
    private String address2;

    @Column(length = 20, nullable = false)
    private String district;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "fk_address_city",
            foreignKeyDefinition = "foreign key (city_id) references city (city_id) on update cascade"))
    private City city;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(length = 20, nullable = false)
    private String phone;

}
