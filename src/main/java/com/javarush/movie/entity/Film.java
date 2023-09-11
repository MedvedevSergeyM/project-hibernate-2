package com.javarush.movie.entity;

import com.javarush.movie.converter.RatingConverter;
import com.javarush.movie.converter.SpecialFeaturesConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "film", schema = "movie", indexes = {
        @Index(name = "idx_fk_language_id", columnList = "language_id"),
        @Index(name = "idx_fk_original_language_id", columnList = "original_language_id"),
        @Index(name = "idx_title", columnList = "title")
})
public class Film {

    @Column(name = "film_id", columnDefinition = "smallint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private LocalDate releaseYear;

    // TODO: on update cascade
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", nullable = false, foreignKey = @ForeignKey(name = "fk_film_language"))
    private Language language;

    // TODO: on update cascade
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "original_language_id", foreignKey = @ForeignKey(name = "fk_film_language_original"))
    private Language originalLanguage;

    @Column(name = "rental_duration", columnDefinition = "tinyint unsigned", nullable = false)
    @ColumnDefault("3")
    private Short rentalDuration;

    @Column(name="rental_rate", columnDefinition="decimal(4, 2)", nullable = false)
    @ColumnDefault("4.99")
    private BigDecimal rentalRate;

    @Column(columnDefinition = "smallint unsigned")
    private Integer length;

    @Column(name="replacement_cost", columnDefinition="decimal(5, 2)", nullable = false)
    @ColumnDefault("19.99")
    private BigDecimal replacementCost;

    @Column(columnDefinition = "enum ('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @ColumnDefault("'G'")
    @Enumerated(EnumType.STRING)
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "set ('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    @Enumerated(EnumType.STRING)
    @Convert(converter = SpecialFeaturesConverter.class)
    private SpecialFeatures specialFeatures;

    @Column(name = "last_update", columnDefinition = "timestamp on update CURRENT_TIMESTAMP", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

}
