import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private final Lock lock = new ReentrantLock();

    private final List<Account> accounts;

    public Bank() {

        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public Account findAccountByID(int id) {
        return accounts.stream()
                .filter(account -> account.getAccountID() == id)
                .findFirst()
                .orElse(null);
    }

    public void transfer(Account fromAccount, Account toAccount, Double amount) {

        lock.lock(); // add a lock
        try {

            if (fromAccount.getBalance() >= amount) {

                toAccount.deposit(amount);
                fromAccount.withdraw(amount);

                System.out.println("---- Transfer successful ----");
                System.out.println("From " + fromAccount.getOwnerType() + " account with ID: " + fromAccount.getAccountID());
                System.out.println("To " + toAccount.getOwnerType() + " account with ID: " + toAccount.getAccountID());
                System.out.println("Amount transferred: " + amount);
                System.out.println(toAccount.getOwnerType() + " account balance after transfer: " + toAccount.getBalance());
                System.out.println(fromAccount.getOwnerType() + " account balance after transfer: " + fromAccount.getBalance());

            } else {
                System.out.println("No balance left");
            }
        } finally {

            lock.unlock();
        }
    }
}
