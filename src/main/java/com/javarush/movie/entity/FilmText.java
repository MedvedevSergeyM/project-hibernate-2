package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "film_text", schema = "movie"
        // TODO: fulltext index
//        , indexes = {@Index(name = "idx_title_description", columnList = "title, description")}
)
public class FilmText {

    @Id
    @Column(name = "film_id", columnDefinition = "smallint")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="filmText")
    private Film film;


    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

}
