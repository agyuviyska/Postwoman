package com.example;

import com.example.ConfigProvider;
import com.example.PaymentRequestMessageProvider;
import com.example.PaymentTransactionClient;
import com.example.models.SaleRequest;
import com.example.models.SaleResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class TestPaymentTransactionClient {
    final String VALID_MESSAGE_FILE_NAME = "ValidSaleMessages.json";
    private static PaymentTransactionClient paymentClient;

    @BeforeClass
//    todo da go iznesem v otdelen class i da go izvikvame
    public static void setup() throws IOException, URISyntaxException {
        //        System.out.print(getClass().getCanonicalName());
        ConfigProvider configProvider = new ConfigProvider();
//todo razlichni testove: bez hedyr za auth, greshen hedyr za auth
        var authenticationUser = configProvider.getPropValue("authenticationUser");
        var authenticationPassword = configProvider.getPropValue("authenticationPassword");
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
    public void validSaleTransactionStatus() throws IOException, InterruptedException {

        var messageProvider = new PaymentRequestMessageProvider();
        String message = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
        var response = paymentClient.makePaymentTransactionRequest(message);
        System.out.println(response.statusCode());

        //todo proveri celiq respons, a ne samo status koda
        assertEquals(200, response.statusCode());
    }
//    @Test
//    public void invalidSaleTransactionStatus() throws IOException, InterruptedException {
//
//        var messageProvider = new PaymentRequestMessageProvider();
//        String message = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
//        var response = paymentClient.makePaymentTransactionRequest(message);
//        System.out.println(response.statusCode());
//
//        //todo proveri celiq respons, a ne samo status koda
//        assertEquals(401, response.statusCode());
//    }
    @Test
    public void checkAmountsAreEqual() throws IOException, InterruptedException {

        var messageProvider = new PaymentRequestMessageProvider();
        String request = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
        var response = paymentClient.makePaymentTransactionRequest(request);
        System.out.println(response.body().toString());
        SaleRequest saleRequest;
        SaleResponse saleResponse;
        JsonToModelConverter converter = new JsonToModelConverter();
        saleRequest = converter.getSaleRequestFromJson(request);
        saleResponse = converter.getSaleResponseFromJson(response.body().toString());

        assertEquals(saleRequest.getAmount(),saleResponse.getAmount());
    }
}
