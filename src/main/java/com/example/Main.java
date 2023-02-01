package com.example;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Result result = JUnitCore.runClasses(TestPaymentTransactionClient.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
////        System.out.print(getClass().getCanonicalName());
//        ConfigProvider configProvider = new ConfigProvider();
//
//        var authenticationUser=configProvider.getPropValue("authenticationUser");
//        var authenticationPassword=configProvider.getPropValue("authenticationPassword");
//        var apiPort=configProvider.getPropValue("apiPort");
//        var apiAddress=configProvider.getPropValue("address");
////todo po-govorqshti printove
//        System.out.println(authenticationUser);
//        System.out.println(authenticationPassword);
//        System.out.println(apiPort);
//        System.out.println(apiAddress);
//        var message = "" +
//                "{\n                \"payment_transaction\": " +
//                "{\n                                   \"card_number\": \"4200000000000000\",\n                                   \"cvv\": \"123\",\n                                   \"expiration_date\": \"06/2019\",\n                                   \"amount\": \"500\",\n                                   \"usage\": \"Coffeemaker\",\n                                   \"transaction_type\": \"sale\",\n                                   \"card_holder\": \"Panda Panda\",\n                                   \"email\": \"panda@example.com\",\n                                   \"address\": \"Panda Street, China\"\n                                 }\n                }";
//
//        var ptClient = new PaymentTransactionClient(apiPort,apiAddress,authenticationUser,authenticationPassword);
//        var response=ptClient.makePaymentTransactionRequest(message);
//        System.out.println(response.statusCode());
    }
}
