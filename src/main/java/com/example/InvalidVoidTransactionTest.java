package com.example;

import com.example.models.SaleResponse;
import com.example.models.VoidRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


public class InvalidVoidTransactionTest {
    static final String VALID_MESSAGE_FILE_NAME = "ValidSaleMessages.json";
    private static PaymentTransactionClient paymentClient;
    private static String voidRequestMessage;
    private static JsonModelConverter converter=new JsonModelConverter();

    @BeforeClass
    public static void setUpForValidVoidTransaction() throws IOException, URISyntaxException, InterruptedException {
        paymentClient=SetUpPaymentClient.setUpForValidHttpConnection();
        var messageProvider = new PaymentRequestMessageProvider();
        String saleRequestMessage = messageProvider.getMessage(VALID_MESSAGE_FILE_NAME);
        var saleHttpResponse = paymentClient.makePaymentTransactionRequest(saleRequestMessage);
        SaleResponse saleResponse = converter.getSaleResponseFromJson(saleHttpResponse.body().toString());
        VoidRequest voidRequest = new VoidRequest();
        voidRequest.setReferenceID(saleResponse.getUniqueID());
        voidRequest.setTransactionType("void");
        var localVoidRequest = converter.getJsonFromVoidRequest(voidRequest);
        var voidHttpResponse= paymentClient.makePaymentTransactionRequest(localVoidRequest);
        var voidResponse= converter.getVoidResponseFromJson(voidHttpResponse.body().toString());
        var voidTransactionUniqueId = voidResponse.getUniqueID();
        VoidRequest voidRequest2 = new VoidRequest();
        voidRequest2.setReferenceID(voidTransactionUniqueId);
        voidRequest2.setTransactionType("void");
        voidRequestMessage = converter.getJsonFromVoidRequest(voidRequest2);
    }
    @Test
    public void checkInvalidVoidPointingVoidReturns422() throws IOException, InterruptedException {

        var response = paymentClient.makePaymentTransactionRequest(voidRequestMessage);
        System.out.println(response.statusCode());
        int expectedResult=422;
        int actualResult=response.statusCode();

        assertEquals(expectedResult, actualResult);
    }
}
