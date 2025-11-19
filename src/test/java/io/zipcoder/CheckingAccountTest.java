package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class CheckingAccountTest {

    @Test
    public void testConstructorWithPerson() {
        // Given
        Person accountHolder = new Person("John", "Doe", "john@example.com", "555-1234");
        Double balance = 1000.0;
        String accountNumber = "CHK001";
        boolean overdraftProtection = true;

        // When
        CheckingAccount account = new CheckingAccount(accountHolder, balance, accountNumber, overdraftProtection);

        // Then
        Assert.assertNotNull(account);
        Assert.assertEquals(accountHolder, account.getAccountHolder());
        Assert.assertEquals(balance, account.getBalance(), 0.01);
        Assert.assertEquals(accountNumber, account.getAccountNumber());
    }

    @Test
    public void testConstructorWithBusiness() {
        // Given
        Business accountHolder = new Business("Acme Corp");
        Double balance = 5000.0;
        String accountNumber = "CHK002";
        boolean overdraftProtection = false;

        // When
        CheckingAccount account = new CheckingAccount(accountHolder, balance, accountNumber, overdraftProtection);

        // Then
        Assert.assertNotNull(account);
        Assert.assertEquals(accountHolder, account.getAccountHolder());
    }

    @Test
    public void testGetOverdraftProtection() {
        // Given
        Person accountHolder = new Person("Jane", "Smith", "jane@example.com", "555-5678");
        boolean expectedOverdraftProtection = true;
        CheckingAccount account = new CheckingAccount(accountHolder, 1000.0, "CHK003", expectedOverdraftProtection);

        // When
        boolean actualOverdraftProtection = account.getOverdraftProtection();

        // Then
        Assert.assertEquals(expectedOverdraftProtection, actualOverdraftProtection);
    }

    @Test
    public void testSetOverdraftProtection() {
        // Given
        Person accountHolder = new Person("Bob", "Jones", "bob@example.com", "555-9999");
        CheckingAccount account = new CheckingAccount(accountHolder, 1000.0, "CHK004", true);

        // When
        account.setOverdraftProtection(false);

        // Then
        Assert.assertFalse(account.getOverdraftProtection());
    }

    @Test
    public void testDebitWithOverdraftProtectionEnabled() {
        // Given
        Person accountHolder = new Person("Alice", "Brown", "alice@example.com", "555-1111");
        CheckingAccount account = new CheckingAccount(accountHolder, 500.0, "CHK005", true);

        // When - Attempting to overdraw
        account.debit(600.0);

        // Then - Balance should remain unchanged because overdraft protection is enabled
        Assert.assertEquals(500.0, account.getBalance(), 0.01);
    }

    @Test
    public void testDebitWithOverdraftProtectionDisabled() {
        // Given
        Person accountHolder = new Person("Charlie", "Wilson", "charlie@example.com", "555-2222");
        CheckingAccount account = new CheckingAccount(accountHolder, 500.0, "CHK006", false);

        // When - Attempting to overdraw
        account.debit(600.0);

        // Then - Balance should go negative because overdraft protection is disabled
        Assert.assertEquals(-100.0, account.getBalance(), 0.01);
    }

    @Test
    public void testDebitWithSufficientFunds() {
        // Given
        Person accountHolder = new Person("David", "Miller", "david@example.com", "555-3333");
        CheckingAccount account = new CheckingAccount(accountHolder, 1000.0, "CHK007", true);

        // When
        account.debit(300.0);

        // Then
        Assert.assertEquals(700.0, account.getBalance(), 0.01);
    }

    @Test
    public void testCreditInCheckingAccount() {
        // Given
        Person accountHolder = new Person("Eve", "Davis", "eve@example.com", "555-4444");
        CheckingAccount account = new CheckingAccount(accountHolder, 1000.0, "CHK008", true);

        // When
        account.credit(500.0);

        // Then
        Assert.assertEquals(1500.0, account.getBalance(), 0.01);
    }

    @Test
    public void testMultipleTransactions() {
        // Given
        Person accountHolder = new Person("Frank", "Garcia", "frank@example.com", "555-5555");
        CheckingAccount account = new CheckingAccount(accountHolder, 1000.0, "CHK009", false);

        // When
        account.credit(200.0);
        account.debit(300.0);
        account.credit(100.0);

        // Then
        Assert.assertEquals(1000.0, account.getBalance(), 0.01);
    }
}
