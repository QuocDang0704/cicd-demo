package com.example.be_dolan_final.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {
    @JsonProperty("colorId")
    private Long colorId;
    @JsonProperty("colorName")
    private String colorName;
    @JsonProperty("sizes")
    private List<SizeResponse> sizes;
}
