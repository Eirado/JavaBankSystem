public class Account {

    private Double balance;
    private final int accountID;
    private String ownerType;

    public Account(Double balance, int accountID, String ownerType) {
        this.balance = balance;
        this.accountID = accountID;
        this.ownerType = ownerType;
    }

    public Double getBalance() {
        return balance;
    }

    public String getOwnerType() { return ownerType; }

    public int getAccountID() {
        return accountID;
    }

    public void withdraw(Double amount) {

        this.balance -= amount;
    }

    public void deposit(Double amount) {

        this.balance += amount;
    }

     public String toString() {
        return ownerType + " account with ID: " + accountID + ", Balance: " + balance;
    }
}
