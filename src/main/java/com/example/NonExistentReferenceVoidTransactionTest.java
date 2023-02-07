package com.example;

import com.example.models.VoidRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class NonExistentReferenceVoidTransactionTest {
    private static PaymentTransactionClient paymentClient;
    private static String voidRequestMessage;

    //    private String voidRequestMessage;
    @BeforeClass
    public static void setUpForValidVoidTransaction() throws IOException, URISyntaxException {
        paymentClient = SetUpPaymentClient.setUpForValidHttpConnection();
        VoidRequest voidRequest = new VoidRequest();
        voidRequest.setReferenceID("Lamas without Pyjamas");
        voidRequest.setTransactionType("void");
        JsonModelConverter converter = new JsonModelConverter();
        voidRequestMessage = converter.getJsonFromVoidRequest(voidRequest);
    }
    @Test
    public void validSaleTransactionStatusCode200() throws IOException, InterruptedException {

        var response = paymentClient.makePaymentTransactionRequest(voidRequestMessage);
        System.out.println(response.statusCode());
        int expectedResult=422;
        int actualResult=response.statusCode();

        assertEquals(expectedResult, actualResult);
    }

}
