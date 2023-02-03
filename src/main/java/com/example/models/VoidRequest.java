package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoidRequest {

    @JsonProperty("reference_id")
    private String referenceID;
    @JsonProperty("transaction_type")
    private String transactionType;

    public String getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
