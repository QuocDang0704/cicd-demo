package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.CancelOrderDTO;
import com.example.be_dolan_final.dto.OrderDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dolan/order")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order-by-status")
    public ResponseDTO getOderByStatus(
            @RequestParam("status") String status,
            @RequestParam(value = "phone", defaultValue = "") String phone,
            Pageable pageable) {
        return ResponseDTO.success(this.orderService.getOrderByStatus(status, phone, pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getOderByStatus(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.orderService.getOne(id));
    }

    @GetMapping("/get-code")
    public ResponseDTO getOrderByCode(@RequestParam("code") String code) {
        return ResponseDTO.success(this.orderService.getOrderByCode(code));
    }

    @PostMapping("/created-order")
    public ResponseDTO getOderByStatus(@RequestBody OrderDTO orderDTO) {
        return ResponseDTO.success(this.orderService.createdOrder(orderDTO));
    }

    @PutMapping("/update-status")
    public ResponseDTO updateStatus(@RequestBody OrderDTO orderDTO) {
        return ResponseDTO.success(this.orderService.updateStatus(orderDTO));
    }

    @PutMapping("/exchange-order/{orderId}")
    public ResponseDTO exchangeOrder(
            @PathVariable(name = "orderId") Long orderId,
            @RequestBody List<Long> orderDetailIds) {
        this.orderService.exchangeUpdateOrderDetailsList(orderId, orderDetailIds);
        return ResponseDTO.success();
    }

    @PostMapping("/cancel")
    public ResponseDTO cancelExchangeOrder(@RequestBody CancelOrderDTO cancelOrderDTO) {
        this.orderService.cancelOderSucess(cancelOrderDTO);
        return ResponseDTO.success();
    }

    @PostMapping("/confirm/{orderId}")
    public ResponseDTO getOderByStatus(
            @PathVariable("orderId") Long orderId,
            @RequestParam(value = "money", defaultValue = "") String money) {
        this.orderService.confirmOderSucess(orderId, money);
        return ResponseDTO.success();
    }

}
