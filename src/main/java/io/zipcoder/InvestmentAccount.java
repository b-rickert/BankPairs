package io.zipcoder;

public class InvestmentAccount extends Account {
    private Double interestRate;

    public InvestmentAccount(Object accountHolder, Double balance, String accountNumber, Double interestRate) {
        super(accountHolder, balance, accountNumber);
        this.interestRate = interestRate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        setBalance(getBalance() + getBalance() * interestRate);
    }

    @Override
    public void debit(Double amount) {
        super.debit(amount);
    }
}
