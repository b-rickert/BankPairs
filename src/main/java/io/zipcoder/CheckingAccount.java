package io.zipcoder;

public class CheckingAccount extends Account {
    private boolean overdraftProtection;

    public CheckingAccount(Object accountHolder, Double balance, String accountNumber, boolean overdraftProtection) {
        super(accountHolder, balance, accountNumber);
        this.overdraftProtection = overdraftProtection;
    }

    public boolean getOverdraftProtection() {
        return overdraftProtection;
    }

    public void setOverdraftProtection(boolean overdraftProtection) {
        this.overdraftProtection = overdraftProtection;
    }

    @Override
    public void debit(Double amount) {
        if (overdraftProtection && getBalance() - amount < 0) {
            return;
        }
        super.debit(amount);
    }
}
