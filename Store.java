import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Store implements PropertyChangeListener {
    private final Account storeAccount;
    private final Employee[] employees;
    private final Bank bank;
    private int currentEmployeeIndex = 0;
    private final double paymentThreshold = 1400.0;

    private final Lock lock = new ReentrantLock();

    public Store(Account storeAccount, Employee[] employees, Bank bank) {
        this.storeAccount = storeAccount;
        this.employees = employees;
        this.bank = bank;
        // Register Store as a listener to changes in storeAccount balance
        this.storeAccount.addPropertyChangeListener(this);
    }

    public int getAccountID() {
        return storeAccount.getAccountID();
    }

    // Implementing propertyChange method
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("balance".equals(evt.getPropertyName())) {
            double newBalance = (Double) evt.getNewValue();
            if (newBalance >= paymentThreshold) {
                payEmployees();
            }
        }
    }

    public void payEmployees() {
        // Acquire the lock to ensure thread-safe transfer
        lock.lock();
        try {
            while (storeAccount.getBalance() >= employees[currentEmployeeIndex].getSalary()) {
                // Transfer salary to the employee
                bank.transfer(storeAccount, bank.findAccountByID(employees[currentEmployeeIndex].getAccountID()), 1400.0);
                employees[currentEmployeeIndex].investSalary();
                // Move to the next employee
                currentEmployeeIndex = (currentEmployeeIndex + 1) % employees.length;
            }
        } finally {
            // Release the lock
            lock.unlock();
        }
    }
}
