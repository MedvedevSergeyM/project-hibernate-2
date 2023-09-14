package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "inventory", schema = "movie", indexes = {
        @Index(name = "idx_fk_film_id", columnList = "film_id")
        , @Index(name = "idx_store_id_film_id", columnList = "store_id, film_id")}
)
public class Inventory extends LastUpdate {

    @Column(name = "inventory_id", columnDefinition = "mediumint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "fk_inventory_store",
            foreignKeyDefinition = "foreign key (store_id) references store (store_id) on update cascade"))
    private Store store;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "fk_inventory_film",
            foreignKeyDefinition = "foreign key (film_id) references film (film_id) on update cascade"))
    private Film film;

}
