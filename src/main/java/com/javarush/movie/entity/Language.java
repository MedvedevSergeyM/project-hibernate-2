package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "language", schema = "movie")
public class Language {

    @Column(name = "language_id", columnDefinition = "tinyint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(columnDefinition = "char(20)", nullable = false)
    private String name;

    @Column(name = "last_update", columnDefinition = "timestamp on update CURRENT_TIMESTAMP", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;
}
