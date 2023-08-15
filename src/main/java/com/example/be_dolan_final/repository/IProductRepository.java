package com.example.be_dolan_final.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.dto.projection.ProductHome;
import com.example.be_dolan_final.dto.projection.ProductPJ;
import com.example.be_dolan_final.entities.Products;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface IProductRepository extends IBaseRepository<Products, Long> {
        @Query(value = "SELECT p.id,\n" +
                        "       p.name,\n" +
                        "       p.material,\n" +
                        "       p.describe,\n" +
                        "       c.name   AS categoryName,\n" +
                        "       c.id     AS categoryId,\n" +
                        "       b.name   AS brandName,\n" +
                        "       b.id     AS brandId,\n" +
                        "       p.image  as image,\n" +
                        "       p.active as active,\n" +
                        "       p.price as price,\n" +
                        "       JSON_ARRAYAGG(\n" +
                        "               JSON_OBJECT(\n" +
                        "                       'colorName', color.name,\n" +
                        "                       'colorId', color.id,\n" +
                        "                       'sizes', (SELECT JSON_ARRAYAGG(\n" +
                        "                                                JSON_OBJECT(\n" +
                        "                                                        'sizeName', size.name,\n" +
                        "                                                        'quantity', pd.quantity,\n" +
                        "                                                        'sizeId', size.id\n" +
                        "                                                    )\n" +
                        "                                            )\n" +
                        "                                 FROM size size\n" +
                        "                                          JOIN product_details pd ON pd.size_id = size.id\n" +
                        "                                 WHERE pd.product_id = p.id\n" +
                        "                                   AND pd.color_id = color.id)\n" +
                        "                   )\n" +
                        "           )    AS customProductDetails\n" +
                        "FROM products p\n" +
                        "         JOIN category c ON p.category_id = c.id\n" +
                        "         JOIN brand b ON p.brand_id = b.id\n" +
                        "         LEFT JOIN product_details pd ON p.id = pd.product_id\n" +
                        "         LEFT JOIN color ON pd.color_id = color.id\n" +
                        "GROUP BY p.id,\n" +
                        "         p.name,\n" +
                        "         p.material,\n" +
                        "         p.describe,\n" +
                        "         c.name,\n" +
                        "         c.id,\n" +
                        "         b.name,\n" +
                        "         b.id", 
                        countQuery = "SELECT COUNT(*) FROM products p", 
                        nativeQuery = true)
        Page<ProductPJ> findAllByProductWithDetails(Pageable pageable);

        Page<Products> findAllByActiveOrderByPrice(Pageable pageable, Boolean active);

        Page<Products> findAllByActiveOrderByCreatedTimestampDesc(Pageable pageable, Boolean active);

        @Query(value = "SELECT p.id, \n" +
                        "        p.name, \n" +
                        "        p.material, \n" +
                        "        p.describe, \n" +
                        "        p.category_id, \n" +
                        "        p.brand_id, \n" +
                        "        p.image, \n" +
                        "        p.active, \n" +
                        "        p.price, \n" +
                        "    CASE \n" +
                        "        WHEN dc.percentage IS NOT NULL AND (dp.percentage IS NULL OR dc.percentage > dp.percentage) THEN dc.percentage \n"
                        +
                        "        WHEN dp.percentage IS NOT NULL THEN dp.percentage \n" +
                        "        ELSE null \n" +
                        "    END AS appliedPercentage, \n" +
                        "    CASE \n" +
                        "        WHEN dc.percentage IS NOT NULL AND (dp.percentage IS NULL OR dc.percentage > dp.percentage) THEN p.price * (1 - dc.percentage / 100) \n"
                        +
                        "        WHEN dp.percentage IS NOT NULL THEN p.price * (1 - dp.percentage / 100) \n" +
                        "        ELSE null \n" +
                        "    END AS discountedPrice \n" +
                        "    FROM products p \n" +
                        "    LEFT JOIN ( \n" +
                        "        SELECT discount_category.category_id, discount_category.percentage \n" +
                        "        FROM discount_category \n" +
                        "        WHERE CURDATE() BETWEEN discount_category.effective_start_date AND discount_category.effective_end_date \n"
                        +
                        "        AND status = 1 \n" +
                        "    ) dc ON p.category_id = dc.category_id \n" +
                        "    LEFT JOIN ( \n" +
                        "        SELECT discount_products.product_id, discount_products.percentage \n" +
                        "        FROM discount_products \n" +
                        "        WHERE CURDATE() BETWEEN discount_products.effective_start_date AND discount_products.effective_end_date \n"
                        +
                        "        AND status = 1 \n" +
                        "    ) dp ON p.id = dp.product_id \n" +
                        "WHERE (:nameConstraint IS NULL OR p.name LIKE %:nameConstraint%) " +
                        "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
                        "AND (:maxPrice IS NULL OR p.price <= :maxPrice) order BY created_timestamp DESC", countQuery = "SELECT COUNT(*) FROM products p "
                                        +
                                        "WHERE (:nameConstraint IS NULL OR p.name LIKE %:nameConstraint%) " +
                                        "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
                                        "AND (:maxPrice IS NULL OR p.price <= :maxPrice)", nativeQuery = true)
        Page<ProductHome> findAllProductHomes(
                        @Param("nameConstraint") String nameConstraint,
                        @Param("minPrice") Double minPrice,
                        @Param("maxPrice") Double maxPrice,
                        Pageable pageable);

}
