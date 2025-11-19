package io.zipcoder;

public class SavingsAccount extends Account {
    private Double interestRate;

    public SavingsAccount(Object accountHolder, Double balance, String accountNumber, Double interestRate) {
        super(accountHolder, balance, accountNumber);
        // TODO: Implement constructor
    }

    public Double getInterestRate() {
        // TODO: Implement getter
        return null;
    }

    public void setInterestRate(Double interestRate) {
        // TODO: Implement setter
    }

    public void applyInterest() {
        // TODO: Implement method to apply interest to the balance
        // New balance = current balance + (current balance * interest rate)
    }

    @Override
    public void debit(Double amount) {
        // TODO: Implement debit method
        // Savings accounts have overdraft protection - don't allow balance to go negative
    }
}
