package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {
    private Long amount;
    private String orderInfo;
    private String orderRef;
    private String returnUrl;
}
