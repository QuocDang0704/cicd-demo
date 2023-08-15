package com.example.be_dolan_final.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.product.ProductDTO;
import com.example.be_dolan_final.dto.product.ProductResponse;
import com.example.be_dolan_final.dto.projection.ProductHome;
import com.example.be_dolan_final.dto.projection.ProductPJ;
import com.example.be_dolan_final.entities.Color;
import com.example.be_dolan_final.entities.ProductDetails;
import com.example.be_dolan_final.entities.Products;
import com.example.be_dolan_final.entities.Size;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IColorRepository;
import com.example.be_dolan_final.repository.IProductDetailRepository;
import com.example.be_dolan_final.repository.IProductRepository;
import com.example.be_dolan_final.repository.ISizeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    private final IProductRepository productRepository;
    
    private final IProductDetailRepository productDetailRepository;

    private final IColorRepository colorRepository;

    private final ISizeRepository sizeRepository;

    public Page<ProductResponse> getAll(Pageable pageable) {
        Page<ProductPJ> allProduct = this.productRepository.findAllByProductWithDetails(pageable);
        // List<ProductResponse> productResponses = allProduct.stream().map(ProductResponse::fromTo)
        //         .collect(Collectors.toList());
        Page<ProductResponse> productResponses = allProduct.map(ProductResponse::fromTo);
        return productResponses;
    }

    public ProductDTO getOne(Long id) {
        Products products = this.productRepository.findByIdOrThrow(id);
        List<ProductDetails> productDetailList = this.productDetailRepository.findByProductId(id);
        List<Long> colorId = productDetailList.stream().map(ProductDetails::getColorId).collect(Collectors.toList());
        List<Long> sizeId = productDetailList.stream().map(ProductDetails::getSizeId).collect(Collectors.toList());
        List<Color> colors = this.colorRepository.findAllById(colorId);
        List<Size> sizes = this.sizeRepository.findAllById(sizeId);
        ProductDTO productDTO = MapperUtils.map(products, ProductDTO.class);
        productDTO.setProductDetailsList(productDetailList);
        productDTO.setColorList(colors);
        productDTO.setSizeList(sizes);
        return productDTO;
    }

    public ProductDTO createdProduct(ProductDTO productDTO) {
        Products products = this.productRepository.save(MapperUtils.map(productDTO, Products.class));
        productDTO.getCustomProductDetails().forEach(productDetail -> {
            productDetail.getSizes().forEach(size -> {
                productDetail.setProductId(products.getId());
                productDetail.setActive(products.getActive());
                productDetail.setDescribe(products.getDescribe());
                productDetail.setMaterial(products.getMaterial());
                productDetail.setColorId(productDetail.getColorId());
                productDetail.setSizeId(size.getSizeId());
                productDetail.setQuantity(size.getQuantity());
                this.productDetailRepository.save(MapperUtils.map(productDetail, ProductDetails.class));
            });
        });
        return productDTO;
    }

    public ProductDTO updatedProduct(ProductDTO productDTO) {
        this.productRepository.findById(productDTO.getId())
                .map(products -> {
                    products.setCategoryId(productDTO.getCategoryId());
                    products.setBrandId(productDTO.getBrandId());
                    products.setActive(productDTO.getActive());
                    products.setDescribe(productDTO.getDescribe());
                    products.setMaterial(productDTO.getMaterial());
                    products.setName(productDTO.getName());
                    return this.productRepository.save(products);
                }).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return productDTO;
    }

    public void deleteProduct(Long id) {
        Products products = this.productRepository.findByIdOrThrow(id);
        this.productRepository.deleteById(products.getId());
    }

    public Page<ProductDTO> findAllByPrice(Pageable pageable) {
        Page<Products> productsPage = this.productRepository.findAllByActiveOrderByPrice(pageable, true);
        
        Page<ProductDTO> dtoPage = MapperUtils.mapEntityPageIntoDtoPage(productsPage, ProductDTO.class);
        
        return dtoPage;
    }

    public Page<ProductResponse> findAllByDate(String name, Double minPrice, Double maxPrice, Pageable pageable) {
        Page<ProductHome> productsPage = this.productRepository.findAllProductHomes(name, minPrice, maxPrice, pageable);
        System.out.println(productsPage.getNumber()+" name:" + name + " minPrice:" + minPrice + " maxPrice:" + maxPrice);
        
        Page<ProductResponse> dtoPage = MapperUtils.mapEntityPageIntoDtoPage(productsPage, ProductResponse.class);
        return dtoPage;
    }
}
