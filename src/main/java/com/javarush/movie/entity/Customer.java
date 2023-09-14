package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "customer", schema = "movie" , indexes = {
        @Index(name = "idx_fk_address_id", columnList = "address_id")
        , @Index(name = "idx_fk_store_id", columnList = "store_id")
        , @Index(name = "idx_last_name", columnList = "last_name")}
)
public class Customer extends LastUpdate {

    @Column(name = "customer_id", columnDefinition = "smallint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer_store",
            foreignKeyDefinition = "foreign key (store_id) references store (store_id) on update cascade"))
    private Store store;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(length = 50)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer_address",
            foreignKeyDefinition = "foreign key (address_id) references address (address_id) on update cascade"))
    private Address address;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean active;

    @Column(name = "create_date", nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createDate;

}
