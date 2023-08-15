package com.example.be_dolan_final.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeResponse {
    @JsonProperty("sizeId")
    private Long sizeId;
    @JsonProperty("sizeName")
    private String sizeName;
    @JsonProperty("quantity")
    private Long quantity;
}

