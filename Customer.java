import java.util.Random;

public class Customer extends Thread {

    private final Account account; // Customer's account
    private final Store[] stores; // Array of stores
    private final Bank bank; // Bank

    // Constructor
    public Customer(Account account, Store[] stores, Bank bank) {
        this.account = account;
        this.stores = stores;
        this.bank = bank;
    }

    // Override the run method from Thread
    @Override
    public void run() {
        Random random = new Random();
        // Perform purchases until the account balance is empty
        while (account.getBalance() >= 100.0) {
            // Iterate through stores
            for (Store store : stores) {
                double transferAmount = (random.nextBoolean()) ? 100.0 : 200.0; // Randomly choose transfer amount
                // Check if the account has enough balance for the transfer
                if (account.getBalance() >= transferAmount) {
                    // Transfer money from the customer's account to the store's account via the bank
                    bank.transfer(account, bank.findAccountByID(store.getAccountID()), transferAmount);
                }
            }
        }
    }
}
