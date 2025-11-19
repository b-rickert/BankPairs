package io.zipcoder;

public abstract class Account {
    private Object accountHolder;
    private Double balance;
    private String accountNumber;
    // TODO: Add a field to track transactions

    public Account(Object accountHolder, Double balance, String accountNumber) {
        // TODO: Implement constructor
    }

    public Object getAccountHolder() {
        // TODO: Implement getter
        return null;
    }

    public Double getBalance() {
        // TODO: Implement getter
        return null;
    }

    public void setBalance(Double balance) {
        // TODO: Implement setter
    }

    public String getAccountNumber() {
        // TODO: Implement getter
        return null;
    }

    public void credit(Double amount) {
        // TODO: Implement credit method (add money to account)
        // TODO: Record this transaction
    }

    public void debit(Double amount) {
        // TODO: Implement debit method (remove money from account)
        // TODO: Record this transaction
    }

    public Object getTransactions() {
        // TODO: Implement method to return transaction history
        return null;
    }
}

