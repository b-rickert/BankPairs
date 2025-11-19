package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class InvestmentAccountTest {

    @Test
    public void testConstructorWithPerson() {
        // Given
        Person accountHolder = new Person("John", "Doe", "john@example.com", "555-1234");
        Double balance = 10000.0;
        String accountNumber = "INV001";
        Double interestRate = 0.07; // 7% interest rate

        // When
        InvestmentAccount account = new InvestmentAccount(accountHolder, balance, accountNumber, interestRate);

        // Then
        Assert.assertNotNull(account);
        Assert.assertEquals(accountHolder, account.getAccountHolder());
        Assert.assertEquals(balance, account.getBalance(), 0.01);
        Assert.assertEquals(accountNumber, account.getAccountNumber());
    }

    @Test
    public void testConstructorWithBusiness() {
        // Given
        Business accountHolder = new Business("Global Industries");
        Double balance = 50000.0;
        String accountNumber = "INV002";
        Double interestRate = 0.08;

        // When
        InvestmentAccount account = new InvestmentAccount(accountHolder, balance, accountNumber, interestRate);

        // Then
        Assert.assertNotNull(account);
        Assert.assertEquals(accountHolder, account.getAccountHolder());
    }

    @Test
    public void testGetInterestRate() {
        // Given
        Person accountHolder = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        Double expectedInterestRate = 0.06;
        InvestmentAccount account = new InvestmentAccount(accountHolder, 15000.0, "INV003", expectedInterestRate);

        // When
        Double actualInterestRate = account.getInterestRate();

        // Then
        Assert.assertEquals(expectedInterestRate, actualInterestRate, 0.0001);
    }

    @Test
    public void testSetInterestRate() {
        // Given
        Person accountHolder = new Person("Bob", "Jones", "bob@example.com", "555-9999");
        InvestmentAccount account = new InvestmentAccount(accountHolder, 20000.0, "INV004", 0.05);
        Double newInterestRate = 0.09;

        // When
        account.setInterestRate(newInterestRate);

        // Then
        Assert.assertEquals(newInterestRate, account.getInterestRate(), 0.0001);
    }

    @Test
    public void testApplyInterest() {
        // Given
        Person accountHolder = new Person("Alice", "Brown", "alice@example.com", "555-1111");
        Double initialBalance = 10000.0;
        Double interestRate = 0.10; // 10%
        InvestmentAccount account = new InvestmentAccount(accountHolder, initialBalance, "INV005", interestRate);

        // When
        account.applyInterest();

        // Then
        Double expectedBalance = 11000.0; // 10000 + (10000 * 0.10)
        Assert.assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    public void testMultipleInterestApplications() {
        // Given
        Person accountHolder = new Person("Charlie", "Wilson", "charlie@example.com", "555-2222");
        Double initialBalance = 5000.0;
        Double interestRate = 0.05; // 5%
        InvestmentAccount account = new InvestmentAccount(accountHolder, initialBalance, "INV006", interestRate);

        // When
        account.applyInterest();
        account.applyInterest();

        // Then
        Double expectedBalance = 5512.50; // 5000 * 1.05 * 1.05
        Assert.assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    public void testInvestmentAccountAllowsOverdraft() {
        // Given
        Person accountHolder = new Person("David", "Miller", "david@example.com", "555-3333");
        InvestmentAccount account = new InvestmentAccount(accountHolder, 1000.0, "INV007", 0.07);

        // When - Attempting to overdraw (investment accounts don't have overdraft protection)
        account.debit(1500.0);

        // Then - Balance should go negative
        Assert.assertEquals(-500.0, account.getBalance(), 0.01);
    }

    @Test
    public void testDebitWithSufficientFunds() {
        // Given
        Person accountHolder = new Person("Eve", "Davis", "eve@example.com", "555-4444");
        InvestmentAccount account = new InvestmentAccount(accountHolder, 8000.0, "INV008", 0.06);

        // When
        account.debit(3000.0);

        // Then
        Assert.assertEquals(5000.0, account.getBalance(), 0.01);
    }

    @Test
    public void testCreditInInvestmentAccount() {
        // Given
        Person accountHolder = new Person("Frank", "Garcia", "frank@example.com", "555-5555");
        InvestmentAccount account = new InvestmentAccount(accountHolder, 12000.0, "INV009", 0.08);

        // When
        account.credit(3000.0);

        // Then
        Assert.assertEquals(15000.0, account.getBalance(), 0.01);
    }

    @Test
    public void testHighInterestRate() {
        // Given
        Person accountHolder = new Person("Grace", "Martinez", "grace@example.com", "555-6666");
        Double initialBalance = 100000.0;
        Double interestRate = 0.15; // 15% - Higher than savings account
        InvestmentAccount account = new InvestmentAccount(accountHolder, initialBalance, "INV010", interestRate);

        // When
        account.applyInterest();

        // Then
        Double expectedBalance = 115000.0;
        Assert.assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    public void testTransactionsAndInterest() {
        // Given
        Person accountHolder = new Person("Henry", "Rodriguez", "henry@example.com", "555-7777");
        InvestmentAccount account = new InvestmentAccount(accountHolder, 10000.0, "INV011", 0.10);

        // When
        account.credit(5000.0); // Balance: 15000
        account.applyInterest(); // Balance: 16500 (15000 * 1.10)
        account.debit(6500.0); // Balance: 10000

        // Then
        Assert.assertEquals(10000.0, account.getBalance(), 0.01);
    }

    @Test
    public void testInterestOnNegativeBalance() {
        // Given
        Person accountHolder = new Person("Ivy", "Lee", "ivy@example.com", "555-8888");
        InvestmentAccount account = new InvestmentAccount(accountHolder, -1000.0, "INV012", 0.10);

        // When
        account.applyInterest();

        // Then
        // Interest on negative balance: -1000 + (-1000 * 0.10) = -1100
        Assert.assertEquals(-1100.0, account.getBalance(), 0.01);
    }
}
