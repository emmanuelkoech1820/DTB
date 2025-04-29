package com.dtb.services.abstracts;


import com.dtb.dto.CreateAccountRequest;
import com.dtb.entities.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccount(CreateAccountRequest request);

    BigDecimal getBalance(Long accountId);

    Account activateAccount(Long accountId);

    Account deactivateAccount(Long accountId);
}
