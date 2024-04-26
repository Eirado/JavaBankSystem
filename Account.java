import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Account {

    // Properties
    private Double balance; // Balance of the account
    private final int accountID; // Unique account ID
    private String ownerType; // Type of the account owner
    private final PropertyChangeSupport propertyChangeSupport; // Support for property change listeners

    // Constructor
    public Account(Double balance, int accountID, String ownerType) {
        this.balance = balance;
        this.accountID = accountID;
        this.ownerType = ownerType;
        this.propertyChangeSupport = new PropertyChangeSupport(this); // Initialize property change support
    }

    // Getter for balance
    public Double getBalance() {
        return balance;
    }

    // Getter for ownerType
    public String getOwnerType() {
        return ownerType;
    }

    // Getter for accountID
    public int getAccountID() {
        return accountID;
    }

    // Method to withdraw money from the account
    public void withdraw(Double amount) {
        Double oldBalance = balance; // Store the old balance
        this.balance -= amount; // Update the balance
        // Notify listeners about the change in balance
        propertyChangeSupport.firePropertyChange("balance", oldBalance, balance);
    }

    // Method to deposit money into the account
    public void deposit(Double amount) {
        Double oldBalance = balance; // Store the old balance
        this.balance += amount; // Update the balance
        // Notify listeners about the change in balance
        propertyChangeSupport.firePropertyChange("balance", oldBalance, balance);
    }

    // Method to provide a string representation of the account
    public String toString() {
        return ownerType + " account with ID: " + accountID + ", Balance: " + balance;
    }

    // Method to add a property change listener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener); // Add the listener
    }

    // Method to remove a property change listener
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener); // Remove the listener
    }
}
