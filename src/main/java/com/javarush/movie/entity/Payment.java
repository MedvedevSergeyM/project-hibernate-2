package com.javarush.movie.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "payment", schema = "movie", indexes = {
        @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
        @Index(name = "idx_fk_staff_id", columnList = "staff_id")
})
public class Payment extends LastUpdate {

    @Column(name = "payment_id", columnDefinition = "smallint unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_payment_customer",
            foreignKeyDefinition = "foreign key (customer_id) references customer (customer_id) on update cascade"))
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", nullable = false, foreignKey = @ForeignKey(name = "fk_payment_staff",
            foreignKeyDefinition = "foreign key (staff_id) references staff (staff_id) on update cascade"))
    private Staff staff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_id", foreignKey = @ForeignKey(name = "fk_payment_rental",
            foreignKeyDefinition = "foreign key (rental_id) references rental (rental_id) on update cascade on delete set null"))
    private Rental rental;

    @Column(name = "amount", nullable = false, columnDefinition = "decimal(5,2)")
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime paymentDate;

}
