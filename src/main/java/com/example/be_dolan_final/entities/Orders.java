package com.example.be_dolan_final.entities;

import com.example.be_dolan_final.config.Constant;
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
@Table(name = "`orders`")
public class Orders extends SharedAuditingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "`payment`")
    private String payment;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Constant.OderStatus status;

    @Column(name = "ship_price")
    private BigDecimal shipPrice;

    @Column(name = "ship_price_discount")
    private BigDecimal shipPriceDiscount;

    @Column(name = "`describe`")
    private String describe;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address")
    private String addressName;

    @Column(name = "phone")
    private String phone;
}
