import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    private final Account storeAccount;
    private final Employee[] employees;
    private final Bank bank;
    private int currentEmployeeIndex = 0;

    private final Lock lock = new ReentrantLock();

    public Store(Account storeAccount, Employee[] employees, Bank bank) {
        this.storeAccount = storeAccount;
        this.employees = employees;
        this.bank = bank;
    }

    public int getAccountID() {
        return storeAccount.getAccountID();
    }

    public void payEmployees() {

        lock.lock();
        try {
            while (storeAccount.getBalance() >= employees[currentEmployeeIndex].getSalary()) {

                bank.transfer(storeAccount, bank.findAccountByID(employees[currentEmployeeIndex].getAccountID()), 1400.0);

                employees[currentEmployeeIndex].investSalary();

                currentEmployeeIndex = (currentEmployeeIndex + 1) % employees.length;
            }
        } finally {

            lock.unlock();
        }
    }
}
