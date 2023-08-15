package com.example.be_dolan_final.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`product_details`")
public class ProductDetails extends SharedAuditingEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "color_id")
    private Long colorId;

    @Column(name = "active")
    private boolean active;

    @Column(name = "`describe`")
    private String describe;

    @Column(name = "`material`")
    private String material;

    @Column(name = "`quantity`")
    private Long quantity;
}
