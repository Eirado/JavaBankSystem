import java.util.Random;

public class Customer extends Thread {  // Deve ser uma thread
    private final Account account; // Deve possuir uma conta com um saldo inicial de R$ 1.000,00 at main
    private final Store[] stores;
    private final Bank bank;

    public Customer(Account account, Store[] stores, Bank bank) {
        this.account = account;
        this.stores = stores;
        this.bank = bank;
    }

    @Override          // Override the run method from Thread
  public void run() {  // Deve realizar compras, de R$ 100,00 ou R$ 200,00, alternando as lojas, até o saldo da conta estar vazio.
    Random random = new Random();
    while (account.getBalance() >= 100.0) {
        for (Store store : stores) {
            double transferAmount = (random.nextBoolean()) ? 100.0 : 200.0;
            if (account.getBalance() >= transferAmount) {
                bank.transfer(account, bank.findAccountByID(store.getAccountID()), transferAmount);
            }
        }
    }
}
}
