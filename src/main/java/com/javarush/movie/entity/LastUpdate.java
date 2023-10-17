package com.javarush.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class LastUpdate {

    @Column(name = "last_update",
            insertable = false,
            updatable = false,
            columnDefinition = "timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

}
