package com.example;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class WrongBasicAuthenticationTransactionStatusCode401Test {
    final String VALID_MESSAGE_FILE_NAME = "ValidSaleMessages.json";
    private static PaymentTransactionClient paymentClient;
    @Before
    public void setUpWrongBasicAuthenticationTransactionStatusCode401() throws IOException, URISyntaxException {
        ConfigProvider configProvider = new ConfigProvider();
        var authenticationUser = "fff";
        var authenticationPassword = "fff";
        var apiPort = configProvider.getPropValue("apiPort");
        var apiAddress = configProvider.getPropValue("address");
//todo po-govorqshti printove
        System.out.println(authenticationUser);
        System.out.println(authenticationPassword);
        System.out.println(apiPort);
        System.out.println(apiAddress);
        paymentClient = new PaymentTransactionClient(apiPort, apiAddress, authenticationUser, authenticationPassword);
    }

    @Test
    public void wrongBasicAuthenticationTransactionStatusCode401() throws IOException, InterruptedException {

        var messageProvider = new PaymentRequestMessageProvider();
        String message = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
        var response = paymentClient.makePaymentTransactionRequest(message);
        System.out.println(response.statusCode());
        assertEquals(401, response.statusCode());
    }
}

