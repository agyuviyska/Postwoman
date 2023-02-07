package com.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
    public static void main(String[] args) {
        ArrayList<Result> results = new ArrayList<Result>();
        results.add(JUnitCore.runClasses(ValidSaleTransactionTest.class));
        results.add(JUnitCore.runClasses(InvalidVoidTransactionTest.class));
        results.add(JUnitCore.runClasses(NonExistentReferenceVoidTransactionTest.class));
        results.add(JUnitCore.runClasses(ValidVoidTransactionTest.class));
        results.add(JUnitCore.runClasses(WrongBasicAuthenticationTransactionStatusCode401Test.class));

        for (Result result:results){
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.out.println(result.wasSuccessful());
        }








    }
}
