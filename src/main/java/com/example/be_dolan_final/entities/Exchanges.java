package com.example.be_dolan_final.entities;

import com.example.be_dolan_final.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchanges")
public class Exchanges extends SharedAuditingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "reason")
    private String reason; // của khách hàng

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Constant.ExchangesType type;
}
