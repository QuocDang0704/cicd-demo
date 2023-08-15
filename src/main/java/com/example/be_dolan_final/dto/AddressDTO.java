package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressDTO {
    private Long id;
    private Long userId;
    private boolean active;
    private String province;
    private String provinceId;
    private String district;
    private String districtId;
    private String ward;
    private String wardId;
    private String address;
    private boolean isDefault;
}
