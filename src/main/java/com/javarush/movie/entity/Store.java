package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"managerStaff"})
@Entity
@Table(name = "store", schema = "movie"
        , indexes = {@Index(name = "idx_fk_address_id", columnList = "address_id")}
        , uniqueConstraints = {@UniqueConstraint(name = "idx_unique_manager", columnNames = {"manager_staff_id"})}
)
public class Store extends LastUpdate {

    @Column(name = "store_id", columnDefinition = "tinyint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_staff_id", nullable = false, foreignKey = @ForeignKey(name = "fk_store_staff",
            foreignKeyDefinition = "foreign key (manager_staff_id) references staff (staff_id) on update cascade"))
    private Staff managerStaff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "fk_store_address",
            foreignKeyDefinition = "foreign key (address_id) references address (address_id) on update cascade"))
    private Address address;

}
