package com.dtb.dto;

import java.math.BigDecimal;

public class CreateAccountRequest {
    private Long profileId;
    private BigDecimal initialBalance;

    // Default constructor
    public CreateAccountRequest() {
    }

    // Parameterized constructor
    public CreateAccountRequest(Long profileId, BigDecimal initialBalance) {
        this.profileId = profileId;
        this.initialBalance = initialBalance;
    }

    // Getter for profileId
    public Long getProfileId() {
        return profileId;
    }

    // Setter for profileId
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    // Getter for initialBalance
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    // Setter for initialBalance
    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    // toString method
    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "profileId=" + profileId +
                ", initialBalance=" + initialBalance +
                '}';
    }
}