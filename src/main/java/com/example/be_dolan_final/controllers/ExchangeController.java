package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.ExchangeDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dolan/exchange")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping("/create")
    public ResponseDTO createdExchange(@RequestBody ExchangeDTO exchangeDTO) {
        return ResponseDTO.success(this.exchangeService.createdExchange(exchangeDTO));
    }

}
