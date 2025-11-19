package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class SavingsAccountTest {

    @Test
    public void testConstructorWithPerson() {
        // Given
        Person accountHolder = new Person("John", "Doe", "john@example.com", "555-1234");
        Double balance = 2000.0;
        String accountNumber = "SAV001";
        Double interestRate = 0.02; // 2% interest rate

        // When
        SavingsAccount account = new SavingsAccount(accountHolder, balance, accountNumber, interestRate);

        // Then
        Assert.assertNotNull(account);
        Assert.assertEquals(accountHolder, account.getAccountHolder());
        Assert.assertEquals(balance, account.getBalance(), 0.01);
        Assert.assertEquals(accountNumber, account.getAccountNumber());
    }

    @Test
    public void testConstructorWithBusiness() {
        // Given
        Business accountHolder = new Business("Tech Solutions Inc");
        Double balance = 10000.0;
        String accountNumber = "SAV002";
        Double interestRate = 0.03;

        // When
        SavingsAccount account = new SavingsAccount(accountHolder, balance, accountNumber, interestRate);

        // Then
        Assert.assertNotNull(account);
        Assert.assertEquals(accountHolder, account.getAccountHolder());
    }

    @Test
    public void testGetInterestRate() {
        // Given
        Person accountHolder = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        Double expectedInterestRate = 0.025;
        SavingsAccount account = new SavingsAccount(accountHolder, 5000.0, "SAV003", expectedInterestRate);

        // When
        Double actualInterestRate = account.getInterestRate();

        // Then
        Assert.assertEquals(expectedInterestRate, actualInterestRate, 0.0001);
    }

    @Test
    public void testSetInterestRate() {
        // Given
        Person accountHolder = new Person("Bob", "Jones", "bob@example.com", "555-9999");
        SavingsAccount account = new SavingsAccount(accountHolder, 3000.0, "SAV004", 0.02);
        Double newInterestRate = 0.03;

        // When
        account.setInterestRate(newInterestRate);

        // Then
        Assert.assertEquals(newInterestRate, account.getInterestRate(), 0.0001);
    }

    @Test
    public void testApplyInterest() {
        // Given
        Person accountHolder = new Person("Alice", "Brown", "alice@example.com", "555-1111");
        Double initialBalance = 1000.0;
        Double interestRate = 0.05; // 5%
        SavingsAccount account = new SavingsAccount(accountHolder, initialBalance, "SAV005", interestRate);

        // When
        account.applyInterest();

        // Then
        Double expectedBalance = 1050.0; // 1000 + (1000 * 0.05)
        Assert.assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    public void testMultipleInterestApplications() {
        // Given
        Person accountHolder = new Person("Charlie", "Wilson", "charlie@example.com", "555-2222");
        Double initialBalance = 1000.0;
        Double interestRate = 0.10; // 10%
        SavingsAccount account = new SavingsAccount(accountHolder, initialBalance, "SAV006", interestRate);

        // When
        account.applyInterest();
        account.applyInterest();

        // Then
        Double expectedBalance = 1210.0; // 1000 * 1.1 * 1.1
        Assert.assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    public void testSavingsAccountHasOverdraftProtection() {
        // Given
        Person accountHolder = new Person("David", "Miller", "david@example.com", "555-3333");
        SavingsAccount account = new SavingsAccount(accountHolder, 500.0, "SAV007", 0.02);

        // When - Attempting to overdraw
        account.debit(600.0);

        // Then - Balance should remain unchanged because savings accounts have overdraft protection
        Assert.assertEquals(500.0, account.getBalance(), 0.01);
    }

    @Test
    public void testDebitWithSufficientFunds() {
        // Given
        Person accountHolder = new Person("Eve", "Davis", "eve@example.com", "555-4444");
        SavingsAccount account = new SavingsAccount(accountHolder, 1000.0, "SAV008", 0.03);

        // When
        account.debit(300.0);

        // Then
        Assert.assertEquals(700.0, account.getBalance(), 0.01);
    }

    @Test
    public void testCreditInSavingsAccount() {
        // Given
        Person accountHolder = new Person("Frank", "Garcia", "frank@example.com", "555-5555");
        SavingsAccount account = new SavingsAccount(accountHolder, 2000.0, "SAV009", 0.025);

        // When
        account.credit(500.0);

        // Then
        Assert.assertEquals(2500.0, account.getBalance(), 0.01);
    }

    @Test
    public void testInterestOnZeroBalance() {
        // Given
        Person accountHolder = new Person("Grace", "Martinez", "grace@example.com", "555-6666");
        SavingsAccount account = new SavingsAccount(accountHolder, 0.0, "SAV010", 0.05);

        // When
        account.applyInterest();

        // Then
        Assert.assertEquals(0.0, account.getBalance(), 0.01);
    }

    @Test
    public void testTransactionsAndInterest() {
        // Given
        Person accountHolder = new Person("Henry", "Rodriguez", "henry@example.com", "555-7777");
        SavingsAccount account = new SavingsAccount(accountHolder, 1000.0, "SAV011", 0.05);

        // When
        account.credit(500.0); // Balance: 1500
        account.applyInterest(); // Balance: 1575 (1500 * 1.05)
        account.debit(575.0); // Balance: 1000

        // Then
        Assert.assertEquals(1000.0, account.getBalance(), 0.01);
    }
}
