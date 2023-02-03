package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SaleRequest {
    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("cvv")
    private String cvv;
    @JsonProperty("expiration_date")
    private String expirationDate;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("usage")
    private String usage;
    @JsonProperty("transaction_type")
    private String transactionType;
    @JsonProperty("card_holder")
    private String cardHolder;
    @JsonProperty("email")
    private String email;
    @JsonProperty("address")
    private String address;


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}




