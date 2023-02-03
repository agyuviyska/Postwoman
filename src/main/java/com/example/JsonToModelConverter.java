package com.example;

import com.example.models.SaleRequest;
import com.example.models.SaleResponse;
import com.example.models.VoidRequest;
import com.example.models.VoidResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//todo use generic type <T> instead of multiple methods
public class JsonToModelConverter {

    public SaleRequest getSaleRequestFromJson (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        SaleRequest saleRequest = mapper.convertValue(root.get("payment_transaction"), SaleRequest.class);
        return saleRequest;
    }
    public SaleResponse getSaleResponseFromJson (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SaleResponse saleResponse = mapper.readValue(json,SaleResponse.class);

        return saleResponse;
    }
    public VoidRequest getVoidRequestFromJson (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        VoidRequest voidRequest = mapper.convertValue(root.get("payment_transaction"), VoidRequest.class);
        return voidRequest;
    }
    public VoidResponse getVoidResponseFromJson (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        VoidResponse voidResponse = mapper.readValue(json,VoidResponse.class);

        return voidResponse;
    }

}
