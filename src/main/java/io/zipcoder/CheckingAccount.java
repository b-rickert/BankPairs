package io.zipcoder;

public class CheckingAccount extends Account {
    private boolean overdraftProtection;

    public CheckingAccount(Object accountHolder, Double balance, String accountNumber, boolean overdraftProtection) {
        super(accountHolder, balance, accountNumber);
        // TODO: Implement constructor
    }

    public boolean getOverdraftProtection() {
        // TODO: Implement getter
        return false;
    }

    public void setOverdraftProtection(boolean overdraftProtection) {
        // TODO: Implement setter
    }

    @Override
    public void debit(Double amount) {
        // TODO: Implement debit method
        // If overdraftProtection is true, don't allow balance to go negative
        // If overdraftProtection is false, allow balance to go negative
    }
}
