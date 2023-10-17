package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "rental", schema = "movie",
        indexes = {
            @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
            @Index(name = "idx_fk_inventory_id", columnList = "inventory_id"),
            @Index(name = "idx_fk_staff_id", columnList = "staff_id")},
        uniqueConstraints = {
            @UniqueConstraint(name = "rental_date", columnNames = {"rental_date", "inventory_id", "customer_id"})}
)
public class Rental extends LastUpdate {

    @Column(name = "rental_id", columnDefinition = "int")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rental_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime rentalDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rental_inventory",
            foreignKeyDefinition = "foreign key (inventory_id) references inventory (inventory_id) on update cascade"))
    private Inventory inventory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rental_customer",
            foreignKeyDefinition = "foreign key (customer_id) references customer (customer_id) on update cascade"))
    private Customer customer;

    @Column(name = "return_date", columnDefinition = "datetime")
    private LocalDateTime returnDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rental_staff",
            foreignKeyDefinition = "foreign key (staff_id) references staff (staff_id) on update cascade"))
    private Staff staff;

}
