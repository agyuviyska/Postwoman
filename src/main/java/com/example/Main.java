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

    }
}
