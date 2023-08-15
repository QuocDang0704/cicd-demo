package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CancelOrderDTO {
    private Long orderId;
    private List<OrderDetailsDTO> orderDetailsDTOS;
    private List<Long> listDelete;
}
