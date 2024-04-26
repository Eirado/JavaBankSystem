import java.util.List;

public class Main {

    // Constants for the number of customers, stores, initial balances, and account offsets
    private static final int NUM_CUSTOMERS = 5;
    private static final int NUM_STORES = 2;
    private static final int INITIAL_CUSTOMER_BALANCE = 1000;
    private static final int INITIAL_STORE_BALANCE = 0;
    private static final int CUSTOMER_ACCOUNT_OFFSET = 1;
    private static final int STORE_ACCOUNT_OFFSET = 100;
    private static final int EMPLOYEE_ACCOUNT_OFFSET = 200;

    public static void main(String[] args) {

        // Create a new bank
        Bank bank = new Bank();

        // Set up accounts for customers, stores, employees, and investment
        setupAccounts(bank, "Customer", NUM_CUSTOMERS, INITIAL_CUSTOMER_BALANCE, CUSTOMER_ACCOUNT_OFFSET);
        setupAccounts(bank, "Store", NUM_STORES, INITIAL_STORE_BALANCE, STORE_ACCOUNT_OFFSET);
        setupAccounts(bank, "Employee", NUM_STORES * 2, INITIAL_STORE_BALANCE, EMPLOYEE_ACCOUNT_OFFSET);
        setupAccounts(bank, "Employee investment", NUM_STORES * 2, INITIAL_STORE_BALANCE, EMPLOYEE_ACCOUNT_OFFSET + 100);

        // Start stores with employees
        Store[] stores = startStores(bank);

        // Print initial account details
        System.out.println("--------- INITIAL ACCOUNT DETAILS ------------");
        printAccountDetails(bank);

        // Perform transfers by customers
        System.out.println("--------- TRANSFERS ---------");
        Customer[] customers = new Customer[NUM_CUSTOMERS];
        startCustomers(stores, bank, customers);
        waitForCustomers(customers);

        // Print new account details after transfers
        System.out.println("--------- NEW ACCOUNT DETAILS ----------");
        printAccountDetails(bank);
    }

    // Method to set up accounts with specified parameters
    private static void setupAccounts(Bank bank, String ownerType, int numAccounts, double initialBalance, int accountOffset) {
        Account[] accounts = new Account[numAccounts];
        for (int i = 0; i < numAccounts; i++) {
            // Create accounts with the specified owner type, initial balance, and account ID offset
            accounts[i] = new Account(initialBalance, i + accountOffset, ownerType);
            bank.addAccount(accounts[i]); // Add the account to the bank
        }
    }

    // Method to print details of all accounts
    private static void printAccountDetails(Bank bank) {
        List<Account> allAccounts = bank.getAllAccounts();
        for (Account account : allAccounts) {
            // Print details of each account
            System.out.println(account);
        }
    }

    // Method to start stores with employees
    private static Store[] startStores(Bank bank) {
        Store[] stores = new Store[NUM_STORES];
        for (int i = 0; i < NUM_STORES; i++) {
            Employee[] employees = new Employee[2];
            for (int j = 0; j < 2; j++) {
                // Add employees to the current store
                employees[j] = addEmployee(i, j, bank);
                employees[j].start(); // Start the employee thread
            }
            // Create and store the current store with its employees
            stores[i] = new Store(bank.findAccountByID(i + STORE_ACCOUNT_OFFSET), employees, bank);
        }
        return stores;
    }

    // Method to add an employee with specified parameters
    private static Employee addEmployee(int shopIndex, int employeeIndex, Bank bank) {
        // Calculate account IDs for the employee and manager
        int employeeAccountID = (shopIndex == 0) ? employeeIndex + EMPLOYEE_ACCOUNT_OFFSET : shopIndex + EMPLOYEE_ACCOUNT_OFFSET + 1;
        int managerAccountID = (shopIndex == 0) ? employeeIndex + EMPLOYEE_ACCOUNT_OFFSET + 100 : shopIndex + EMPLOYEE_ACCOUNT_OFFSET + 101;
        // Create and return an employee with the calculated account IDs
        return new Employee(bank.findAccountByID(employeeAccountID), bank.findAccountByID(managerAccountID), bank);
    }

    // Method to start customers with accounts and stores
    private static void startCustomers(Store[] stores, Bank bank, Customer[] customers) {
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            // Start each customer with their account and the available stores
            customers[i] = new Customer(bank.findAccountByID(i + CUSTOMER_ACCOUNT_OFFSET), stores, bank);
            customers[i].start(); // Start the customer thread
        }
    }

    // Method to wait for customers to finish
    private static void waitForCustomers(Customer[] customers) {
        for (Customer customer : customers) {
            try {
                customer.join(); // Wait for the customer thread to finish
            } catch (InterruptedException e) {
                // Handle InterruptedException if occurred
                System.err.println("Exception caught while waiting for customer: " + e.getMessage());
            }
        }
    }
}
