import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountNumber;
    private String pin;
    private double balance;
    private String accountType; // "Savings" or "Checking"
    private int failedLoginAttempts;
    private boolean isLocked;

    public Account(String accountNumber, String pin, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.accountType = accountType;
        this.failedLoginAttempts = 0;
        this.isLocked = false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String newPin) {
        this.pin = newPin;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lockAccount() {
        this.isLocked = true;
    }

    public void unlockAccount() {
        this.isLocked = false;
        this.failedLoginAttempts = 0;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean checkLoginAttempts() {
        if (failedLoginAttempts >= 3) {
            lockAccount();
            return false;
        }
        return true;
    }

    public void incrementFailedLoginAttempts() {
        failedLoginAttempts++;
    }
}
