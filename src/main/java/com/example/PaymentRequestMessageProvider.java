package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PaymentRequestMessageProvider {
    final String MESSAGES_FOLDER_NAME = "Messages";

    public String getMessage(String fileName) throws IOException {
        InputStream inputStream;
        inputStream = getClass().getClassLoader().getResourceAsStream(MESSAGES_FOLDER_NAME + "/" + fileName);
        try {
            if (inputStream != null) {
                String messageFromFile = new String(inputStream.readAllBytes());
                return messageFromFile;
            } else {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            throw e;
        } finally {
            inputStream.close();
        }
    }
}
