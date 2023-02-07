package com.example;

import com.example.models.SaleRequest;
import com.example.models.SaleResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class ValidSaleTransactionTest {
    final String VALID_MESSAGE_FILE_NAME = "ValidSaleMessages.json";
    private static PaymentTransactionClient paymentClient;
    @BeforeClass
    public static void setUpForValidTransaction() throws IOException, URISyntaxException {
        paymentClient=SetUpPaymentClient.setUpForValidHttpConnection();
    }

    @Test
    public void validSaleTransactionStatusCode200() throws IOException, InterruptedException {

        var messageProvider = new PaymentRequestMessageProvider();
        String message = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
        var response = paymentClient.makePaymentTransactionRequest(message);
        System.out.println(response.statusCode());

        assertEquals(200, response.statusCode());
    }

    @Test
    public void checkValidSaleStatusApproved() throws IOException, InterruptedException {

        var messageProvider = new PaymentRequestMessageProvider();
        String request = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
        var response = paymentClient.makePaymentTransactionRequest(request);
        SaleResponse saleResponse;
        JsonModelConverter converter = new JsonModelConverter();
        saleResponse = converter.getSaleResponseFromJson(response.body().toString());
        String expectedResult = "approved";
        String actualResult = saleResponse.getStatus();

        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void checkAmountsAreEqual() throws IOException, InterruptedException {

        var messageProvider = new PaymentRequestMessageProvider();
        String request = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
        var response = paymentClient.makePaymentTransactionRequest(request);
        System.out.println(response.body().toString());
        SaleRequest saleRequest;
        SaleResponse saleResponse;
        JsonModelConverter converter = new JsonModelConverter();
        saleRequest = converter.getSaleRequestFromJson(request);
        saleResponse = converter.getSaleResponseFromJson(response.body().toString());

        assertEquals(saleRequest.getAmount(),saleResponse.getAmount());
    }


}
