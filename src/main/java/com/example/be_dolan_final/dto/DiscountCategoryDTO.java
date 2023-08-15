package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

import com.example.be_dolan_final.entities.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountCategoryDTO {
    private Long id;
    private Long categoryId;
    private boolean status;
    private String percentage;
    private String describe;
    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private List<Long> listCategoryId;
    private Category category;
}
