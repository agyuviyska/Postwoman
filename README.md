Llama Who Had No Pajam

# Postwoman

# Payment Transaction API Testing Framework

A Java-based API testing automation project built with Maven and JUnit 4 for verifying and validating Payment Transaction APIs (Sale and Void operations).

---

## Tech Stack & Prerequisites

* Language: Java 11+ (uses java.net.http.HttpClient)
* Testing Framework: JUnit 4
* Build Tool: Maven
* JSON Processing: 

---

## Project Architecture & Packages

### 1. Data Models (com.example.models)

* BaseResponse: Parent class representing the standard response payload from the transaction API.
  * Fields: uniqueID, status, usage, amount, transactionTime, message.
* SaleRequest: Model representing a payment sale request.
  * Fields: cardNumber, cvv, expirationDate, amount, usage, transactionType, cardHolder, email, address.
* SaleResponse: Subclass of BaseResponse handling sale transaction responses.
* VoidRequest: Model representing a void request payload.
  * Fields: referenceID, transactionType.
* VoidResponse: Subclass of BaseResponse handling void transaction responses.

---

### 2. Core Utilities (com.example)

* PaymentTransactionClient: HTTP client wrapper utilizing Java 11's HttpClient to dispatch POST requests with HTTP Basic Authentication headers.
* SetUpPaymentClient: Utility class providing static helper methods to initialize PaymentTransactionClient instances using external configuration properties.
* ConfigProvider: Reads environment configurations (API address, port, credentials) from config.properties.
* JsonModelConverter: Jackson-backed converter for serialization and deserialization between JSON payloads and Java model objects.
* PaymentRequestMessageProvider: File reader utility for loading JSON test payloads from the classpath/resource folders.

---

## Automated Test Suite

The project features automated API integration tests covering both positive and negative scenarios:

| Test Class | Description | Expected Result |
| :--- | :--- | :--- |
| ValidSaleTransactionTest | Tests valid sale payment transaction execution. | HTTP Status 200, Status approved, Amount match. |
| ValidVoidTransactionTest | Tests a valid void operation referencing an existing sale transaction. | Void status approved. |
| InvalidVoidTransactionTest | Attempts to perform a void on an already voided transaction. | HTTP Status 422 (Unprocessable Entity). |
| NonExistentReferenceVoidTransactionTest | Attempts to void a transaction using an invalid/non-existent referenceID. | HTTP Status 422 (Unprocessable Entity). |
| WrongBasicAuthenticationTransactionStatusCode401Test | Sends API requests with invalid Basic Auth credentials (fff/fff). | HTTP Status 401 (Unauthorized). |

---

## Execution

### Test Runner (Main.java)
The project includes a custom JUnit runner (com.example.Main) that executes the 5 test classes sequentially using JUnitCore, aggregates test outcomes into a list of Result objects, and logs failures/success status directly to the console.

### Running Tests via Command Line

Run all tests via Maven:
```bash
mvn clean test
