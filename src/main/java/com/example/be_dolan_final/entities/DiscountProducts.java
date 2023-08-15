package com.example.be_dolan_final.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`discount_products`")
public class DiscountProducts extends SharedAuditingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "percentage")
    private String percentage;

    @Column(name = "`describe`")
    private String describe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "`effective_start_date`")
    private Date effectiveStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "`effective_end_date`")
    private Date effectiveEndDate;
}
