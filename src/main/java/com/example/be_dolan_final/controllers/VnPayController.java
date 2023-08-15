package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.OrderCreateRequest;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.VnPayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/dolan/vnpay")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class VnPayController {
    private VnPayService vnPayService;

    @PostMapping("/create-order")
    public ResponseDTO createOrder(@RequestBody OrderCreateRequest orderCreateRequest) throws IOException {
        return ResponseDTO.success(this.vnPayService.createOrder(orderCreateRequest));
    }
}
