# Bank Account System - Implementation Plan for Beginners

## Overview
This document provides a step-by-step guide to implement a bank account system in Java. The project uses **Test-Driven Development (TDD)**, meaning all tests are already written for you. Your job is to make the tests pass by implementing the code.

## Before You Start

### Understanding the Project Structure
```
src/
├── main/java/io/zipcoder/     # Your implementation goes here
│   ├── Person.java             # Individual account holder
│   ├── Business.java           # Business account holder
│   ├── Account.java            # Abstract base class for all accounts
│   ├── CheckingAccount.java   # Checking account implementation
│   ├── SavingsAccount.java    # Savings account with interest
│   └── InvestmentAccount.java # Investment account with interest
└── test/java/io/zipcoder/     # Tests (DO NOT MODIFY)
    └── ...Test.java           # Test files
```

### Running Tests
- Run all tests: `mvn test`
- Run specific test: `mvn test -Dtest=PersonTest`
- Currently: 53 tests total, all failing (this is expected!)

---

## Implementation Steps

### Step 1: Implement the `Person` Class (Easiest - Start Here!)
**Goal**: Create a class to represent an individual account holder.

**What you need**: 
- 4 fields: firstName, lastName, email, phoneNumber (all Strings)
- Constructor to set all fields
- Getters and setters for each field

**Pseudo-code**:
```
CLASS Person:
    FIELDS:
        firstName (String)
        lastName (String)
        email (String)
        phoneNumber (String)
    
    CONSTRUCTOR(firstName, lastName, email, phoneNumber):
        SET this.firstName = firstName
        SET this.lastName = lastName
        SET this.email = email
        SET this.phoneNumber = phoneNumber
    
    METHOD getFirstName():
        RETURN firstName
    
    METHOD setFirstName(firstName):
        SET this.firstName = firstName
    
    METHOD getLastName():
        RETURN lastName
    
    METHOD setLastName(lastName):
        SET this.lastName = lastName
    
    METHOD getEmail():
        RETURN email
    
    METHOD setEmail(email):
        SET this.email = email
    
    METHOD getPhoneNumber():
        RETURN phoneNumber
    
    METHOD setPhoneNumber(phoneNumber):
        SET this.phoneNumber = phoneNumber
```

**Testing**: After implementing, run `mvn test -Dtest=PersonTest`. You should see 9 tests pass! ✓

---

### Step 2: Implement the `Business` Class (Also Easy!)
**Goal**: Create a class to represent a business account holder.

**What you need**:
- 1 field: businessName (String)
- Constructor to set the field
- Getter and setter for businessName

**Pseudo-code**:
```
CLASS Business:
    FIELDS:
        businessName (String)
    
    CONSTRUCTOR(businessName):
        SET this.businessName = businessName
    
    METHOD getBusinessName():
        RETURN businessName
    
    METHOD setBusinessName(businessName):
        SET this.businessName = businessName
```

**Testing**: Run `mvn test -Dtest=BusinessTest`. You should see 3 tests pass! ✓

---

### Step 3: Implement the `Account` Abstract Class (More Complex)
**Goal**: Create the base class that all account types will extend.

**What you need**:
- Fields: accountHolder (Object - can be Person or Business), balance (Double), accountNumber (String)
- A List to store transactions (use ArrayList<String>)
- Constructor to initialize all fields
- Getters and one setter (for balance)
- Methods: credit (add money), debit (subtract money), getTransactions

**Pseudo-code**:
```
ABSTRACT CLASS Account:
    FIELDS:
        accountHolder (Object)
        balance (Double)
        accountNumber (String)
        transactions (ArrayList of Strings)
    
    CONSTRUCTOR(accountHolder, balance, accountNumber):
        SET this.accountHolder = accountHolder
        SET this.balance = balance
        SET this.accountNumber = accountNumber
        CREATE new ArrayList for transactions
    
    METHOD getAccountHolder():
        RETURN accountHolder
    
    METHOD getBalance():
        RETURN balance
    
    METHOD setBalance(balance):
        SET this.balance = balance
    
    METHOD getAccountNumber():
        RETURN accountNumber
    
    METHOD credit(amount):
        ADD amount to balance
        ADD "Credit: $" + amount to transactions list
    
    METHOD debit(amount):
        SUBTRACT amount from balance
        ADD "Debit: $" + amount to transactions list
    
    METHOD getTransactions():
        RETURN transactions list
```

**Key Concepts**:
- `credit()` increases the balance (deposits)
- `debit()` decreases the balance (withdrawals)
- Track each transaction as a String in the ArrayList

**Testing**: Run `mvn test -Dtest=AccountTest`. You should see 10 tests pass! ✓

---

### Step 4: Implement the `CheckingAccount` Class (Inheritance!)
**Goal**: Create a checking account that extends Account and adds overdraft protection.

**What you need**:
- 1 new field: overdraftProtection (boolean)
- Constructor that calls the parent (super) constructor
- Getter and setter for overdraftProtection
- Override the debit() method to check overdraft rules

**Pseudo-code**:
```
CLASS CheckingAccount EXTENDS Account:
    FIELDS:
        overdraftProtection (boolean)
    
    CONSTRUCTOR(accountHolder, balance, accountNumber, overdraftProtection):
        CALL parent constructor with (accountHolder, balance, accountNumber)
        SET this.overdraftProtection = overdraftProtection
    
    METHOD getOverdraftProtection():
        RETURN overdraftProtection
    
    METHOD setOverdraftProtection(overdraftProtection):
        SET this.overdraftProtection = overdraftProtection
    
    OVERRIDE METHOD debit(amount):
        IF overdraftProtection is TRUE:
            IF amount > balance:
                DO NOT allow the transaction (balance stays the same)
                DO NOT record a transaction
            ELSE:
                CALL parent debit method (super.debit(amount))
        ELSE:
            CALL parent debit method (super.debit(amount))
            # This allows negative balance
```

**Key Concepts**:
- `super()` calls the parent class constructor
- `super.debit(amount)` calls the parent class method
- Overdraft protection TRUE = cannot go negative
- Overdraft protection FALSE = can go negative

**Testing**: Run `mvn test -Dtest=CheckingAccountTest`. You should see 9 tests pass! ✓

---

### Step 5: Implement the `SavingsAccount` Class (Interest Calculations!)
**Goal**: Create a savings account that earns interest and has overdraft protection.

**What you need**:
- 1 new field: interestRate (Double)
- Constructor that calls the parent constructor
- Getter and setter for interestRate
- Method to apply interest: applyInterest()
- Override debit() to prevent overdrafts

**Pseudo-code**:
```
CLASS SavingsAccount EXTENDS Account:
    FIELDS:
        interestRate (Double)
    
    CONSTRUCTOR(accountHolder, balance, accountNumber, interestRate):
        CALL parent constructor with (accountHolder, balance, accountNumber)
        SET this.interestRate = interestRate
    
    METHOD getInterestRate():
        RETURN interestRate
    
    METHOD setInterestRate(interestRate):
        SET this.interestRate = interestRate
    
    METHOD applyInterest():
        CALCULATE interestAmount = balance * interestRate
        ADD interestAmount to balance
        # Example: balance = 1000, rate = 0.05
        # interestAmount = 1000 * 0.05 = 50
        # new balance = 1000 + 50 = 1050
    
    OVERRIDE METHOD debit(amount):
        IF amount > balance:
            DO NOT allow the transaction
            DO NOT record a transaction
        ELSE:
            CALL parent debit method (super.debit(amount))
```

**Key Concepts**:
- Interest formula: `new balance = current balance + (current balance × interest rate)`
- Savings accounts ALWAYS have overdraft protection (cannot go negative)
- Use `setBalance()` method to update the balance after calculating interest

**Testing**: Run `mvn test -Dtest=SavingsAccountTest`. You should see 11 tests pass! ✓

---

### Step 6: Implement the `InvestmentAccount` Class (Final Class!)
**Goal**: Create an investment account that earns interest but allows overdrafts.

**What you need**:
- 1 new field: interestRate (Double)
- Constructor that calls the parent constructor
- Getter and setter for interestRate
- Method to apply interest: applyInterest()
- Override debit() to allow negative balances

**Pseudo-code**:
```
CLASS InvestmentAccount EXTENDS Account:
    FIELDS:
        interestRate (Double)
    
    CONSTRUCTOR(accountHolder, balance, accountNumber, interestRate):
        CALL parent constructor with (accountHolder, balance, accountNumber)
        SET this.interestRate = interestRate
    
    METHOD getInterestRate():
        RETURN interestRate
    
    METHOD setInterestRate(interestRate):
        SET this.interestRate = interestRate
    
    METHOD applyInterest():
        CALCULATE interestAmount = balance * interestRate
        ADD interestAmount to balance
        # Note: Interest applies even if balance is negative!
        # Example: balance = -100, rate = 0.10
        # interestAmount = -100 * 0.10 = -10
        # new balance = -100 + (-10) = -110
    
    OVERRIDE METHOD debit(amount):
        CALL parent debit method (super.debit(amount))
        # Always allow the transaction, even if it makes balance negative
```

**Key Concepts**:
- Same interest formula as SavingsAccount
- NO overdraft protection (can go negative)
- Interest applies to negative balances (makes them more negative!)

**Testing**: Run `mvn test -Dtest=InvestmentAccountTest`. You should see 12 tests pass! ✓

---

## Final Testing

After implementing all classes, run:
```bash
mvn test
```

You should see:
```
Tests run: 53, Failures: 0, Errors: 0, Skipped: 0
```

**Success!** All 53 tests passing! 🎉

---

## Common Beginner Mistakes to Avoid

### 1. Forgetting to initialize fields in the constructor
❌ **Wrong**:
```java
public Person(String firstName, String lastName, String email, String phoneNumber) {
    // Fields are not set!
}
```

✅ **Correct**:
```java
public Person(String firstName, String lastName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
}
```

### 2. Forgetting to return values in getters
❌ **Wrong**:
```java
public String getFirstName() {
    return null; // This will fail tests!
}
```

✅ **Correct**:
```java
public String getFirstName() {
    return firstName;
}
```

### 3. Not calling super() in subclass constructors
❌ **Wrong**:
```java
public CheckingAccount(Object accountHolder, Double balance, String accountNumber, boolean overdraftProtection) {
    this.overdraftProtection = overdraftProtection;
    // Parent fields are not initialized!
}
```

✅ **Correct**:
```java
public CheckingAccount(Object accountHolder, Double balance, String accountNumber, boolean overdraftProtection) {
    super(accountHolder, balance, accountNumber); // Call parent constructor
    this.overdraftProtection = overdraftProtection;
}
```

### 4. Forgetting to initialize the ArrayList
❌ **Wrong**:
```java
private ArrayList<String> transactions; // null by default

public Account(...) {
    // ArrayList is never created!
}
```

✅ **Correct**:
```java
private ArrayList<String> transactions;

public Account(...) {
    this.transactions = new ArrayList<>(); // Create the ArrayList
}
```

### 5. Modifying balance directly instead of using setBalance()
❌ **Wrong** (in applyInterest):
```java
public void applyInterest() {
    balance = balance + (balance * interestRate); // Won't work if balance is private!
}
```

✅ **Correct**:
```java
public void applyInterest() {
    Double interestAmount = getBalance() * interestRate;
    setBalance(getBalance() + interestAmount);
}
```

---

## Object-Oriented Programming Concepts Used

### Encapsulation
- Private fields with public getters and setters
- This protects data and controls access

### Inheritance
- CheckingAccount, SavingsAccount, and InvestmentAccount all extend Account
- They inherit fields and methods from the parent class
- Use `super()` to call parent constructor
- Use `super.methodName()` to call parent methods

### Polymorphism
- accountHolder can be either a Person or a Business (Object type)
- Different account types override the debit() method differently

### Abstraction
- Account is abstract - you can't create an Account directly
- You must create a specific type (Checking, Savings, or Investment)

---

## Tips for Success

1. **Work incrementally**: Complete one class at a time
2. **Test frequently**: Run tests after each implementation
3. **Read error messages**: They tell you exactly what's wrong
4. **Use the tests as documentation**: Read the test files to understand what's expected
5. **Don't modify the test files**: Your job is to make the existing tests pass
6. **Ask for help**: If stuck, review the pseudo-code or ask your instructor/pair partner

---

## Progress Checklist

- [ ] Person class implemented (9 tests)
- [ ] Business class implemented (3 tests)
- [ ] Account class implemented (10 tests)
- [ ] CheckingAccount class implemented (9 tests)
- [ ] SavingsAccount class implemented (11 tests)
- [ ] InvestmentAccount class implemented (12 tests)
- [ ] All 53 tests passing! 🎉

---

## Additional Resources

### Java Syntax Reminders

**Creating an ArrayList**:
```java
import java.util.ArrayList; // At the top of the file

private ArrayList<String> myList = new ArrayList<>();
```

**Adding to an ArrayList**:
```java
myList.add("item");
```

**Calling parent constructor**:
```java
super(parameter1, parameter2);
```

**Calling parent method**:
```java
super.methodName(arguments);
```

**Conditional logic**:
```java
if (condition) {
    // do something
} else {
    // do something else
}
```

### Understanding the Tests

Each test file has comments explaining what it's testing. For example:
- `PersonTest` tests that getters return the correct values
- `AccountTest` tests that credit/debit update the balance correctly
- `CheckingAccountTest` tests overdraft protection logic

Read the test names and you'll understand what needs to work!

---

Good luck! Remember: coding is learned by doing. Don't be afraid to try, fail, and try again. That's how you learn! 🚀
