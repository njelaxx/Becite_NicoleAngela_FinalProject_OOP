import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class ATM {
    private static final int CONSOLE_WIDTH = 80;
    private Account currentAccount;
    private static final DecimalFormat currencyFormat = new DecimalFormat("$###,###.00");
    private static Map<String, Account> accounts = new HashMap<>();  // Store accounts by account number

    public ATM() {
        loadAccounts();
    }

    // Load accounts from a file
    private void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            accounts = (Map<String, Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\t\t\tNo existing accounts found. Starting fresh.");
        }
    }

    // Save accounts to the file
    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("\t\t\tError saving accounts.");
        }
    }

    public boolean login(String accountNumber, String pin) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            currentAccount = account;
            return true;
        }
        return false;
    }

    public void registerAccount(String accountNumber, String pin, String accountType) {
        if (!accounts.containsKey(accountNumber)) {
            Account newAccount = new Account(accountNumber, pin, 0.00, accountType);
            accounts.put(accountNumber, newAccount);
            saveAccounts();  // Save the new account to file
            System.out.println("\t\t\tAccount registered successfully!");
        } else {
            System.out.println("\t\t\tAccount number already exists.");
        }
    }

    public void changePin(String oldPin, String newPin) {
        if (currentAccount.getPin().equals(oldPin)) {
            currentAccount.setPin(newPin);
            System.out.println("\t\t\tPIN changed successfully.");
        } else {
            System.out.println("\t\t\tIncorrect PIN.");
        }
    }

    public void deposit(double amount, boolean generateReceipt) {
        ATMTransaction depositTransaction = new DepositTransaction(currentAccount, amount);
        if (depositTransaction.performTransaction()) {
            if (generateReceipt) {
                depositTransaction.generateReceipt();
            }
        } else {
            System.out.println("\t\t\tInvalid deposit amount.");
        }
    }

    public void withdraw(double amount, boolean generateReceipt) {
        ATMTransaction withdrawTransaction = new WithdrawTransaction(currentAccount, amount);
        if (withdrawTransaction.performTransaction()) {
            if (generateReceipt) {
                withdrawTransaction.generateReceipt();
            }
        } else {
            System.out.println("\t\t\tInsufficient funds or invalid withdrawal amount.");
        }
    }

    public void checkBalance() {
        System.out.println("\t\t\tCurrent Balance: " + currencyFormat.format(currentAccount.getBalance()));
    }

    public void checkAccountType() {
        System.out.println("\t\t\tAccount Type: " + currentAccount.getAccountType());
    }

    public void formatCurrency(double amount) {
        System.out.println("\t\t\tAmount: " + currencyFormat.format(amount));
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 20) / 2) + "s", " █████╗ ████████╗███╗   ███╗"));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 20) / 2) + "s", "██╔══██╗╚══██╔══╝████╗ ████║"));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 20) / 2) + "s", "███████║   ██║   ██╔████╔██║"));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 20) / 2) + "s", "██╔══██║   ██║   ██║╚██╔╝██║"));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 20) / 2) + "s", "██║  ██║   ██║   ██║ ╚═╝ ██║"));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 20) / 2) + "s", "╚═╝  ╚═╝   ╚═╝   ╚═╝     ╚═╝"));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 30) / 2) + "s", "====================================="));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 20) / 2) + "s", "   Welcome to the V-ATM System!"));
            System.out.println(String.format("%" + ((CONSOLE_WIDTH + 30) / 2) + "s", "====================================="));
            System.out.println("\t\t\t1. Register Account");
            System.out.println("\t\t\t2. Login");
            System.out.print("\t\t\tSelect an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                System.out.print("\t\t\tEnter new Account Number: ");
                String newAccountNumber = scanner.nextLine();
                System.out.print("\t\t\tEnter new PIN: ");
                String newPin = scanner.nextLine();
                System.out.print("\t\t\ttEnter Account Type (Savings/Checking): ");
                String newAccountType = scanner.nextLine();
                atm.registerAccount(newAccountNumber, newPin, newAccountType);
            } else {
                System.out.print("\t\t\tEnter Account Number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("\t\t\tEnter PIN: ");
                String pin = scanner.nextLine();

                if (atm.login(accountNumber, pin)) {
                    System.out.println("\t\t\tLogin successful!");
                    boolean running = true;
                    while (running) {
                        System.out.println("\t\t\tATM Menu:");
                        System.out.println("\t\t\t1. Deposit");
                        System.out.println("\t\t\t2. Withdraw");
                        System.out.println("\t\t\t3. Check Balance");
                        System.out.println("\t\t\t4. Change PIN");
                        System.out.println("\t\t\t5. Check Account Type");
                        System.out.println("\t\t\t6. Exit");
                        System.out.print("\t\t\tSelect an option: ");
                        int option = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        boolean generateReceipt = true;
                        switch (option) {
                            case 1:
                                System.out.print("\t\t\tEnter deposit amount: ");
                                double depositAmount = scanner.nextDouble();
                                scanner.nextLine();  // Consume newline
                                System.out.print("\t\t\tGenerate receipt (yes/no): ");
                                String receiptChoice = scanner.nextLine();
                                generateReceipt = receiptChoice.equalsIgnoreCase("yes");
                                atm.deposit(depositAmount, generateReceipt);
                                break;
                            case 2:
                                System.out.print("\t\t\tEnter withdrawal amount: ");
                                double withdrawAmount = scanner.nextDouble();
                                scanner.nextLine();  // Consume newline
                                System.out.print("\t\t\tGenerate receipt (yes/no): ");
                                receiptChoice = scanner.nextLine();
                                generateReceipt = receiptChoice.equalsIgnoreCase("yes");
                                atm.withdraw(withdrawAmount, generateReceipt);
                                break;
                            case 3:
                                atm.checkBalance();
                                break;
                            case 4:
                                System.out.print("\t\t\tEnter current PIN: ");
                                String currentPin = scanner.nextLine();
                                System.out.print("\t\t\tEnter new PIN: ");
                                String updatedPin = scanner.nextLine();
                                atm.changePin(currentPin, updatedPin);
                                break;
                            case 5:
                                atm.checkAccountType();
                                break;
                            case 6:
                                running = false;
                                System.out.println("\t\t\tThank you for using the ATM!");
                                break;
                            default:
                                System.out.println("\t\t\tInvalid option. Try again.");
                        }
                    }
                } else {
                    System.out.println("\t\t\tLogin failed. Account not found or incorrect PIN.");
                }
            }
        }
    }
}
