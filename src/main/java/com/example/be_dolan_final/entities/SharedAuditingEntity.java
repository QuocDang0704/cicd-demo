package com.example.be_dolan_final.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SharedAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "created_timestamp", updatable = false)
    @JsonIgnore
    @CreationTimestamp
    private Instant createdTimestamp;

    @Column(name = "updated_timestamp")
    @JsonIgnore
    @UpdateTimestamp
    private Instant updatedTimestamp;
}
