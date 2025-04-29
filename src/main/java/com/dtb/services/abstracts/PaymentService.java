package com.dtb.services.abstracts;


import com.dtb.dto.TopUpRequest;
import com.dtb.dto.WithdrawRequest;
import com.dtb.dto.TransferRequest;
import com.dtb.entities.Transaction;

public interface PaymentService {
    Transaction topUp(TopUpRequest request);
    Transaction withdraw(WithdrawRequest request);
    Transaction transfer(TransferRequest request);
}
