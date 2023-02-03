package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class PaymentTransactionClient {
    private URI uri;
    private String basicAuth;
    final String PAYMENT_TRANSACTION_METHOD = "payment_transactions";

    //todo napravq model s props
    public PaymentTransactionClient(String port, String address, String userName, String userPass) throws URISyntaxException, IOException {
        this.uri = new URI("http://" + address + ":" + port + "/" + PAYMENT_TRANSACTION_METHOD);
//      generira basic authentication header-a
        String encoding = Base64.getEncoder().encodeToString((userName + ":" + userPass).getBytes());
        this.basicAuth = "Basic " + encoding;
    }

    public HttpResponse makePaymentTransactionRequest(String message) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(this.uri).POST(HttpRequest.BodyPublishers.ofString(message))
                .header("Content-type", "application/json;charset=UTF-8")
                .header("Authorization", this.basicAuth).build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}