package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.ExchangeDetailsDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.ExchangeDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dolan/exchange-detail")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ExchangeDetailsController {

    private final ExchangeDetailsService exchangeDetailsService;

//    @PostMapping("/create-list")
//    public ResponseDTO createdExchangeDetails(@RequestBody List<ExchangeDetailsDTO> exchangeDetailsDTOS) {
//        return ResponseDTO.success(this.exchangeDetailsService.createdExchangeDetailsList(exchangeDetailsDTOS));
//    }

    @PutMapping("/updateAccept/{id}")
    public ResponseDTO updateAccept(@PathVariable("id") Long id) {
        this.exchangeDetailsService.updateStatusAccept(id);
        return ResponseDTO.success();
    }

    @PutMapping("/updateReject/{id}")
    public ResponseDTO updateReject(@PathVariable("id") Long id) {
        this.exchangeDetailsService.updateStatusReject(id);
        return ResponseDTO.success();
    }

}
