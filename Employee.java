
public class Employee extends Thread {
    private final Account mainAccount;
    private final Account investmentAccount;
    private final Bank bank;

    public Employee(Account salaryAccount, Account investmentAccount, Bank bank) {
        this.mainAccount = salaryAccount;
        this.investmentAccount = investmentAccount;
        this.bank = bank;
    }
    public int getAccountID() {
        return mainAccount.getAccountID();
    }

    public Double getSalary() {
        return 1400.0;
    }

    public void investSalary() {
        bank.transfer(mainAccount, investmentAccount, getSalary() * 0.20);
    }
}
