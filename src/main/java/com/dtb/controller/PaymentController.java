package com.dtb.controller;

import com.dtb.dto.TopUpRequest;
import com.dtb.dto.TransferRequest;
import com.dtb.dto.WithdrawRequest;
import com.dtb.entities.Transaction;
import com.dtb.services.abstracts.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/topup")
    public ResponseEntity<Transaction> topUp(@RequestBody TopUpRequest request) {
        Transaction transaction = paymentService.topUp(request);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawRequest request) {
        Transaction transaction = paymentService.withdraw(request);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request) {
        Transaction transaction = paymentService.transfer(request);
        return ResponseEntity.ok(transaction);
    }
}
