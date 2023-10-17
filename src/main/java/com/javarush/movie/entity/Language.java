package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "language", schema = "movie")
public class Language extends LastUpdate {

    @Column(name = "language_id", columnDefinition = "tinyint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(columnDefinition = "char(20)", nullable = false)
    private String name;

}
