public class Account {

    private Double balance;
    private final int accountID;

    public Account(Double balance, int accountID) {
        this.balance = balance;
        this.accountID = accountID;
    }

    public Double getBalance() {
        return balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public void withdraw(Double amount) {

        this.balance -= amount;
    }

    public void deposit(Double amount) {

        this.balance += amount;
    }
}
