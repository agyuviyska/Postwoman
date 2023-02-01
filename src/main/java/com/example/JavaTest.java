package com.example;



import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class JavaTest {

    @org.junit.Test
    public void neHello() throws URISyntaxException, IOException, InterruptedException {
        //        System.out.print(getClass().getCanonicalName());
        ConfigProvider configProvider = new ConfigProvider();

        var authenticationUser=configProvider.getPropValue("authenticationUser");
        var authenticationPassword=configProvider.getPropValue("authenticationPassword");
        var apiPort=configProvider.getPropValue("apiPort");
        var apiAddress=configProvider.getPropValue("address");
//todo po-govorqshti printove
        System.out.println(authenticationUser);
        System.out.println(authenticationPassword);
        System.out.println(apiPort);
        System.out.println(apiAddress);
        var message = "" +
                "{\n                \"payment_transaction\": " +
                "{\n                                   \"card_number\": \"4200000000000000\",\n                                   \"cvv\": \"123\",\n                                   \"expiration_date\": \"06/2019\",\n                                   \"amount\": \"500\",\n                                   \"usage\": \"Coffeemaker\",\n                                   \"transaction_type\": \"sale\",\n                                   \"card_holder\": \"Panda Panda\",\n                                   \"email\": \"panda@example.com\",\n                                   \"address\": \"Panda Street, China\"\n                                 }\n                }";

        var ptClient = new PaymentTransactionClient(apiPort,apiAddress,authenticationUser,authenticationPassword);
        var response=ptClient.makePaymentTransactionRequest(message);
        System.out.println(response.statusCode());
//        var uri = new URI("http://localhost:3001/payment_transactions");
//        var message="" +
//                "{\n                \"payment_transaction\": " +
//                "{\n                                   \"card_number\": \"4200000000000000\",\n                                   \"cvv\": \"123\",\n                                   \"expiration_date\": \"06/2019\",\n                                   \"amount\": \"500\",\n                                   \"usage\": \"Coffeemaker\",\n                                   \"transaction_type\": \"sale\",\n                                   \"card_holder\": \"Panda Panda\",\n                                   \"email\": \"panda@example.com\",\n                                   \"address\": \"Panda Street, China\"\n                                 }\n                }";
//        ConfigProvider configProvider= new ConfigProvider();
//        String user = configProvider.getPropValue("autenticationUser");
//        String pwd = configProvider.getPropValue("autenticationPassword");
//        String encoding = Base64.getEncoder().encodeToString((user + ":" + pwd).getBytes());
//        String bscAuth =  "Basic " + encoding;
//
//        var client = HttpClient.newHttpClient();
//        var request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(message))
//                .header("Content-type","application/json;charset=UTF-8")
//                .header("Authorization",bscAuth).build();
////        HttpRequest.newBuilder().POST().header("Authorization","Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==").build();
//
//        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200,response.statusCode());
    }
}
