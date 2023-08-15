package com.example.be_dolan_final.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`products`")
public class Products extends SharedAuditingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "`describe`")
    private String describe;

    @Column(name = "`material`")
    private String material;

    @Column(name = "`name`")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "`image`")
    private String image;
}
