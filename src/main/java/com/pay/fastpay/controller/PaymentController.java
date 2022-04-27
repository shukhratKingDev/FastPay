package com.pay.fastpay.controller;

import com.pay.fastpay.entity.*;
import com.pay.fastpay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ObjectInputFilter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/pay")
    public ResponseEntity<Response> pay(@RequestBody PaymentDto paymentDto){
        Response response=paymentService.pay(paymentDto);
return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("/paid")
    public ResponseEntity<Response> paid(@RequestBody PaymentDto paymentDto){
Response response=paymentService.paid(paymentDto);
return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/income")
    public ResponseEntity<List<Income>> getIncome(ReportDto reportDto){
List<Income>list=paymentService.getIncome(reportDto);
return ResponseEntity.status(list.isEmpty()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(list);
    }

    @GetMapping("/outcome")
    public ResponseEntity<List<Outcome>> getOutcome(ReportDto reportDto){
List<Outcome> list=paymentService.getOutcome(reportDto);
return ResponseEntity.status(list.isEmpty()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(list);
    }

}
