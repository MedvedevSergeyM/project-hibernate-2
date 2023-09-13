package com.javarush.movie.entity;

import com.javarush.movie.type.converter.RatingConverter;
import com.javarush.movie.type.converter.SpecialFeaturesConverter;
import com.javarush.movie.type.Rating;
import com.javarush.movie.type.SpecialFeature;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "film", schema = "movie", indexes = {
        @Index(name = "idx_fk_language_id", columnList = "language_id"),
        @Index(name = "idx_fk_original_language_id", columnList = "original_language_id"),
        @Index(name = "idx_title", columnList = "title")
})
public class Film extends LastUpdate {

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", nullable = false, foreignKey = @ForeignKey(name = "fk_film_language",
            foreignKeyDefinition = "foreign key (language_id) references language (language_id) on update cascade"))
    private Language language;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "original_language_id", foreignKey = @ForeignKey(name = "fk_film_language_original",
            foreignKeyDefinition = "foreign key (original_language_id) references language (language_id) on update cascade"))
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
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "set ('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    @Convert(converter = SpecialFeaturesConverter.class)
    private Set<SpecialFeature> specialFeatures;

    // TODO: last_update
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="film_category",
            joinColumns =  @JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName="category_id"))
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Category> categories = new HashSet<>();

    // TODO: last_update
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
            joinColumns =  @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
            foreignKey = @ForeignKey(name = "fk_film_actor_film",
                    foreignKeyDefinition = "foreign key (film_id) references film (film_id) on update cascade"),
            inverseForeignKey = @ForeignKey(name = "fk_film_actor_actor",
                    foreignKeyDefinition = "foreign key (actor_id) references actor (actor_id) on update cascade"),
            indexes = {@Index(name = "idx_fk_film_id", columnList = "film_id")})
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Actor> actors = new HashSet<>();

    // TODO: FilmText
//    @OneToOne(cascade = CascadeType.ALL, mappedBy="film")
//    private FilmText filmText;

    @PrePersist
    protected void prePersist() {
        if (rentalDuration == null) {
            rentalDuration = 3;
        }
        if (rentalRate == null) {
            rentalRate = new BigDecimal("4.99");
        }
        if (replacementCost == null) {
            replacementCost = new BigDecimal("19.99");
        }
        if (rating == null) {
            rating = Rating.G;
        }
    }

}
