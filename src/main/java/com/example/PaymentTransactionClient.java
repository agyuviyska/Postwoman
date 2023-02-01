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
//      var uri = new URI("http://localhost:3001/payment_transactions");
        var message = "" +
                "{\n                \"payment_transaction\": " +
                "{\n                                   \"card_number\": \"4200000000000000\",\n                                   \"cvv\": \"123\",\n                                   \"expiration_date\": \"06/2019\",\n                                   \"amount\": \"500\",\n                                   \"usage\": \"Coffeemaker\",\n                                   \"transaction_type\": \"sale\",\n                                   \"card_holder\": \"Panda Panda\",\n                                   \"email\": \"panda@example.com\",\n                                   \"address\": \"Panda Street, China\"\n                                 }\n                }";
//      generira basic authentication header-a
        String encoding = Base64.getEncoder().encodeToString((userName + ":" + userPass).getBytes());
        this.basicAuth = "Basic " + encoding;

//      var client = HttpClient.newHttpClient();
//      var request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(message))
//                .header("Content-type","application/json;charset=UTF-8")
//                .header("Authorization",basicAuth).build();
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