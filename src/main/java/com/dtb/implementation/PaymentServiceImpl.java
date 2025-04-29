package com.dtb.implementation;

import com.dtb.constants.NotificationChannel;
import com.dtb.constants.TransactionType;
import com.dtb.dto.NotificationEvent;
import com.dtb.dto.TopUpRequest;
import com.dtb.dto.TransferRequest;
import com.dtb.dto.WithdrawRequest;
import com.dtb.entities.Transaction;
import com.dtb.entities.Account;
import com.dtb.repository.AccountRepository;
import com.dtb.repository.TransactionRepository;
import com.dtb.services.abstracts.NotificationService;
import com.dtb.services.abstracts.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final NotificationService notificationService;

    public PaymentServiceImpl(AccountRepository walletRepository, TransactionRepository transactionRepository, NotificationService notificationService) {
        this.accountRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public Transaction topUp(TopUpRequest request) {
        Account wallet = accountRepository.findByProfileId(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        accountRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.TOP_UP);
        transaction.setAmount(request.getAmount());
        transaction.setStatus("SUCCESS");

        Transaction savedTransaction = transactionRepository.save(transaction);

        // Send Notification
        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setTopic("PAYMENT_NOTIFICATION");
        notificationEvent.setSource("PAYMENT_SERVICE");
        notificationEvent.setProfileId(wallet.getProfileId());
        notificationEvent.setEmail("customer@example.com");
        notificationEvent.setPhoneNumber("0700000000");
        notificationEvent.setMessage("Your top-up of " + request.getAmount() + " was successful.");
        notificationEvent.setChannel(NotificationChannel.EMAIL);

        notificationService.sendNotification(notificationEvent);

        return savedTransaction;
    }

    @Override
    @Transactional
    public Transaction withdraw(WithdrawRequest request) {
        Account wallet = accountRepository.findByProfileId(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        wallet.setBalance(wallet.getBalance().subtract(request.getAmount()));
        accountRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setAmount(request.getAmount());
        transaction.setStatus("SUCCESS");

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction transfer(TransferRequest request) {
        Account senderWallet = accountRepository.findByProfileId(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender wallet not found"));

        Account receiverWallet = accountRepository.findByProfileId(request.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver wallet not found"));

        if (senderWallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds in sender's wallet");
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(request.getAmount()));
        receiverWallet.setBalance(receiverWallet.getBalance().add(request.getAmount()));

        accountRepository.save(senderWallet);
        accountRepository.save(receiverWallet);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.TRANSFER);
        transaction.setAmount(request.getAmount());
        transaction.setStatus("SUCCESS");

        Transaction trans =  transactionRepository.save(transaction);

        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setTopic("PAYMENT_NOTIFICATION");
        notificationEvent.setSource("PAYMENT_SERVICE");
        notificationEvent.setProfileId(senderWallet.getProfileId());
        notificationEvent.setEmail("customer@example.com");
        notificationEvent.setPhoneNumber("0700000000");
        notificationEvent.setMessage("Your top-up of " + request.getAmount() + " was successful.");
        notificationEvent.setChannel(NotificationChannel.EMAIL);

        notificationService.sendNotification(notificationEvent);

        return trans;
    }
}
