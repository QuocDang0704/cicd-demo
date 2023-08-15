package com.example.be_dolan_final.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.product.ProductDetailDTO;
import com.example.be_dolan_final.entities.Color;
import com.example.be_dolan_final.entities.ProductDetails;
import com.example.be_dolan_final.entities.Size;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IColorRepository;
import com.example.be_dolan_final.repository.IProductDetailRepository;
import com.example.be_dolan_final.repository.ISizeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductDetailService {
    private final IProductDetailRepository productDetailRepository;
    private final ISizeRepository sizeRepository;
    private final IColorRepository colorRepository;

    public Page<ProductDetailDTO> getAll(Pageable pageable) {
        Page<ProductDetails> product_details = this.productDetailRepository.findAll(pageable);
        return MapperUtils.mapEntityPageIntoDtoPage(product_details, ProductDetailDTO.class);
    }

    public ProductDetailDTO getOne(Long id) {
        ProductDetails product_details = this.productDetailRepository.findByIdOrThrow(id);
        ProductDetailDTO dto = MapperUtils.map(product_details, ProductDetailDTO.class);
        Size size = this.sizeRepository.findByIdOrThrow(dto.getSizeId());
        Color color = this.colorRepository.findByIdOrThrow(dto.getColorId());
        dto.setSizeName(size.getName());
        dto.setColorName(color.getName());
        return dto;
    }

    public ProductDetailDTO createdProductDetail(ProductDetailDTO productDetailDTO) {
        ProductDetails product_details = MapperUtils.map(productDetailDTO, ProductDetails.class);
        this.productDetailRepository.save(product_details);
        return productDetailDTO;
    }

    public ProductDetailDTO updatedProductDetail(ProductDetailDTO productDetailDTO) {
        this.productDetailRepository.findById(productDetailDTO.getId())
                .map(product_details -> {
                    product_details.setProductId(productDetailDTO.getProductId());
                    product_details.setSizeId(productDetailDTO.getSizeId());
                    product_details.setColorId(productDetailDTO.getColorId());
                    product_details.setActive(productDetailDTO.isActive());
                    product_details.setDescribe(productDetailDTO.getDescribe());
                    product_details.setMaterial(productDetailDTO.getMaterial());
                    return this.productDetailRepository.save(product_details);
                }).orElseThrow(() -> new IllegalArgumentException("Product Detail not found"));
        return productDetailDTO;
    }

    public void deleteProductDetail(Long id) {
        ProductDetails product_details = this.productDetailRepository.findByIdOrThrow(id);
        this.productDetailRepository.deleteById(product_details.getId());
    }

    public ProductDetailDTO findByProductAndSizeAndColor(Long productId, Long colorId, Long sizeId) {
        ProductDetails details = this.productDetailRepository.findByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        ProductDetailDTO dto = MapperUtils.map(details, ProductDetailDTO.class);
        return dto;
    }
}
