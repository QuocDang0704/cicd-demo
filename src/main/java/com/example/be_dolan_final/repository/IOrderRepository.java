package com.example.be_dolan_final.repository;

import com.example.be_dolan_final.entities.Orders;
import com.example.be_dolan_final.repository.custom.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface IOrderRepository extends IBaseRepository<Orders, Long> {
    @Query(value = "select *\n" +
            "from orders o\n" +
            "where status = :status\n" +
            "and phone like %:phone%\n" +
            "order by o.id desc", nativeQuery = true)
    Page<Orders> getOrdersByStatusAdmin(String status,String phone, Pageable pageable);

    @Query(value = "SELECT * FROM orders o \n" +
            "WHERE o.user_id =:userId and o.status =:status\n" +
            "ORDER BY o.id DESC", nativeQuery = true)
    Page<Orders> getOrdersByStatusUser(Long userId, String status, Pageable pageable);

    Orders findFirstByCode(String code);

    @Query(value = "select IFNULL(SUM(o.total), 0) as sumPrice\n" +
            "from orders o\n" +
            "where o.created_timestamp between :startDate and :endDate", nativeQuery = true)
    BigDecimal sumMoneyRevenue(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "select distinct IFNULL(sum(o.price * ed.quantity), 0) as sumPrice\n" +
            "from orders od\n" +
            "         left join exchanges e on od.id = e.order_id\n" +
            "         left join exchange_details ed on e.id = ed.exchange_id\n" +
            "         left join order_details o on e.order_id = o.order_id\n" +
            "where e.type = 'Return'\n" +
            "  AND o.status = 'WaitingForReturn'\n" +
            "  and od.created_timestamp between :startDate and :endDate", nativeQuery = true)
    BigDecimal sumMoneyReturn(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "select IFNULL(sum(total), 0) as money\n" +
            "from orders o\n" +
            "where status = :status", nativeQuery = true)
    BigDecimal sumQuantityStatisticalOrder(String status);

}
