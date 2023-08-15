package com.example.be_dolan_final.entities;

import com.example.be_dolan_final.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange_details")
public class ExchangeDetails extends SharedAuditingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "exchange_id")
    private Long exchangeId;

    @Column(name = "order_detail_id")
    private Long orderDetailId;

    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Constant.ExchangesStatus status;
}
