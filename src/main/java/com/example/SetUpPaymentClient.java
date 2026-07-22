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
//todo better names for sout
        System.out.println("Authentication User: " + authenticationUser);
        System.out.println("Authentication Password:" + authenticationPassword);
        System.out.println("API Port: " + apiPort);
        System.out.println("Address: " + apiAddress);
        return new PaymentTransactionClient(apiPort, apiAddress, authenticationUser, authenticationPassword);
    }
}
