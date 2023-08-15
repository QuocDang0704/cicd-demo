package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.StatisticalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dolan/statistical")
@AllArgsConstructor
public class StatisticalController {

    private StatisticalService statisticalService;

    @GetMapping("/all")
    public ResponseDTO getStatistical(
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate
    ) throws ParseException {
        return ResponseDTO.success(this.statisticalService.getStatiscal(startDate,endDate));
    }
}
