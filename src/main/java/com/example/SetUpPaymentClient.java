package com.example;

import java.io.IOException;
import java.net.URISyntaxException;

public class SetUpPaymentClient {

    public static PaymentTransactionClient setUpForValidHttpConnection() throws IOException, URISyntaxException {
        ConfigProvider configProvider = new ConfigProvider();
        var authenticationUser = configProvider.getPropValue("authenticationUser");
        var authenticationPassword = configProvider.getPropValue("authenticationPassword");
        var apiPort = configProvider.getPropValue("apiPort");
        var apiAddress = configProvider.getPropValue("address");
//todo po-govorqshti printove
        System.out.println(authenticationUser);
        System.out.println(authenticationPassword);
        System.out.println(apiPort);
        System.out.println(apiAddress);
        return new PaymentTransactionClient(apiPort, apiAddress, authenticationUser, authenticationPassword);
    }
}
