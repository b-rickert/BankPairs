package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    // Helper method to create a concrete account for testing
    // Since Account is abstract, we need a concrete implementation for testing
    private Account createTestAccount(Object accountHolder, Double balance, String accountNumber) {
        // This will need a concrete implementation like CheckingAccount
        return new CheckingAccount(accountHolder, balance, accountNumber, true);
    }

    @Test
    public void testGetAccountHolder() {
        // Given
        Person accountHolder = new Person("John", "Doe", "john@example.com", "555-1234");
        Account account = createTestAccount(accountHolder, 1000.0, "ACC001");

        // When
        Object actualHolder = account.getAccountHolder();

        // Then
        Assert.assertEquals(accountHolder, actualHolder);
    }

    @Test
    public void testGetBalance() {
        // Given
        Person accountHolder = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        Double expectedBalance = 1500.0;
        Account account = createTestAccount(accountHolder, expectedBalance, "ACC002");

        // When
        Double actualBalance = account.getBalance();

        // Then
        Assert.assertEquals(expectedBalance, actualBalance, 0.01);
    }

    @Test
    public void testGetAccountNumber() {
        // Given
        Person accountHolder = new Person("Bob", "Jones", "bob@example.com", "555-9999");
        String expectedAccountNumber = "ACC003";
        Account account = createTestAccount(accountHolder, 500.0, expectedAccountNumber);

        // When
        String actualAccountNumber = account.getAccountNumber();

        // Then
        Assert.assertEquals(expectedAccountNumber, actualAccountNumber);
    }

    @Test
    public void testCredit() {
        // Given
        Person accountHolder = new Person("Alice", "Brown", "alice@example.com", "555-1111");
        Account account = createTestAccount(accountHolder, 1000.0, "ACC004");
        Double creditAmount = 500.0;

        // When
        account.credit(creditAmount);

        // Then
        Double expectedBalance = 1500.0;
        Assert.assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    public void testDebit() {
        // Given
        Person accountHolder = new Person("Charlie", "Wilson", "charlie@example.com", "555-2222");
        Account account = createTestAccount(accountHolder, 1000.0, "ACC005");
        Double debitAmount = 300.0;

        // When
        account.debit(debitAmount);

        // Then
        Double expectedBalance = 700.0;
        Assert.assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    public void testGetTransactions() {
        // Given
        Person accountHolder = new Person("David", "Miller", "david@example.com", "555-3333");
        Account account = createTestAccount(accountHolder, 1000.0, "ACC006");

        // When
        account.credit(200.0);
        account.debit(100.0);
        Object transactions = account.getTransactions();

        // Then
        Assert.assertNotNull(transactions);
    }

    @Test
    public void testTransactionRecordingAfterCredit() {
        // Given
        Person accountHolder = new Person("Eve", "Davis", "eve@example.com", "555-4444");
        Account account = createTestAccount(accountHolder, 1000.0, "ACC007");

        // When
        account.credit(250.0);
        Object transactions = account.getTransactions();

        // Then
        Assert.assertNotNull(transactions);
        // Students should implement a way to verify that the transaction was recorded
    }

    @Test
    public void testTransactionRecordingAfterDebit() {
        // Given
        Person accountHolder = new Person("Frank", "Garcia", "frank@example.com", "555-5555");
        Account account = createTestAccount(accountHolder, 1000.0, "ACC008");

        // When
        account.debit(150.0);
        Object transactions = account.getTransactions();

        // Then
        Assert.assertNotNull(transactions);
        // Students should implement a way to verify that the transaction was recorded
    }

    @Test
    public void testSetBalance() {
        // Given
        Person accountHolder = new Person("Grace", "Martinez", "grace@example.com", "555-6666");
        Account account = createTestAccount(accountHolder, 1000.0, "ACC009");
        Double newBalance = 2000.0;

        // When
        account.setBalance(newBalance);

        // Then
        Assert.assertEquals(newBalance, account.getBalance(), 0.01);
    }
}
