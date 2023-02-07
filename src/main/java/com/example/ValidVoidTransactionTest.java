package com.example;

import com.example.models.SaleResponse;
import com.example.models.VoidRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


public class ValidVoidTransactionTest {
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
        voidRequestMessage = converter.getJsonFromVoidRequest(voidRequest);
    }
    @Test
    public void checkValidVoidStatusApproved() throws IOException, InterruptedException {

        var voidHttpResponse= paymentClient.makePaymentTransactionRequest(voidRequestMessage);
        var voidResponse= converter.getVoidResponseFromJson(voidHttpResponse.body().toString());
        String expectedResult = "approved";
        String actualResult = voidResponse.getStatus();

        assertEquals(expectedResult,actualResult);
    }
}
