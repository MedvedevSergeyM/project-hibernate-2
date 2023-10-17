package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "staff", schema = "movie", indexes = {
        @Index(name = "idx_fk_address_id", columnList = "address_id")
        , @Index(name = "idx_fk_store_id", columnList = "store_id")
})
public class Staff extends LastUpdate {

    @Column(name = "staff_id", columnDefinition = "tinyint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "fk_staff_address",
            foreignKeyDefinition = "foreign key (address_id) references address (address_id) on update cascade"))
    private Address address;

    @Column(columnDefinition = "blob")
    @Lob
    private byte[] picture;

    @Column(length = 50)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "fk_staff_store",
            foreignKeyDefinition = "foreign key (store_id) references store (store_id) on update cascade"))
    private Store store;

// TODO:   @Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    @Column(nullable = false, columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;

    @Column(length = 16, nullable = false)
    private String username;

    @Column(length = 40)
    private String password;

}
