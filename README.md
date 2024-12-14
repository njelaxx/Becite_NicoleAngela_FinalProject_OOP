
# ATM System - OOP-based Solution

## I. Project Overview

This Virtual ATM system is designed to simulate the behavior of an Automated Teller Machine (ATM). The system allows users to perform basic banking operations, such as checking account balance, depositing funds, withdrawing funds, changing PIN, and viewing account types. It also includes user authentication, where accounts can be locked after multiple failed login attempts. The system is built using Object-Oriented Programming (OOP) principles to ensure clear, maintainable, and scalable code.

The system's core functionality is supported by the following features:
- **Login Authentication**: Secure login process with account number and PIN.
- **Transactions**: Ability to deposit and withdraw funds, with receipt generation.
- **Account Management**: Users can check their balance, account type, and change their PIN.
- **Account Locking**: Accounts are locked after multiple failed login attempts.

## II. Explanation of How OOP Principles Were Applied

### 1. **Encapsulation**
Encapsulation is implemented by organizing data (such as account details and transaction history) into classes and restricting access to them via private fields. Public methods (getters and setters) are used to access and modify these fields in a controlled manner.

- **Example**: 
  - The `Account` class has private fields for the account number, balance, PIN, and lock status.
  - Public methods like `getBalance()`, `getAccountNumber()`, `setPin()`, etc., allow controlled access to these fields.

### 2. **Inheritance**
Inheritance is demonstrated through the use of a base class `ATMTransaction`, which is extended by both `DepositTransaction` and `WithdrawTransaction`. This allows both classes to reuse common functionality and provides a clear hierarchical relationship.

- **Example**: 
  - `ATMTransaction` is the parent class.
  - `DepositTransaction` and `WithdrawTransaction` inherit from `ATMTransaction` and override its methods to implement specific transaction behaviors.

### 3. **Polymorphism**
Polymorphism is implemented via method overriding. The `generateReceipt()` method is overridden in both the `DepositTransaction` and `WithdrawTransaction` classes to generate specific transaction receipts.

- **Example**: 
  - Both `DepositTransaction` and `WithdrawTransaction` classes override the `generateReceipt()` method of the `ATMTransaction` class to provide different receipt formats and transaction details.

### 4. **Abstraction**
Abstraction is demonstrated through the use of abstract classes. The `ATMTransaction` class is abstract, which means it defines methods (`performTransaction()` and `generateReceipt()`) that must be implemented by its subclasses, while hiding the implementation details.

- **Example**: 
  - The `ATMTransaction` class is abstract and defines the structure of transactions.
  - The subclasses `DepositTransaction` and `WithdrawTransaction` implement the abstract methods to carry out specific actions.

## III. Details of the Chosen SDG and Its Integration Into the Project

The chosen **Sustainable Development Goal (SDG)** for this project is **SDG 8: Decent Work and Economic Growth**. This SDG aims to promote inclusive and sustainable economic growth, employment, and decent work for all.

### Integration of SDG 8 into the ATM System:
- The ATM system promotes **financial inclusion** by offering accessible banking services. By implementing basic banking features such as deposits, withdrawals, and account management, the system ensures users can manage their finances effectively.
- The **account security features**, such as PIN protection and failed login attempts, enhance the safety of users' transactions, fostering trust in financial services.
- The system could serve as a prototype for a real-world banking solution that helps in reducing barriers to accessing banking services, thus supporting economic participation.

## IV. Instructions for Running the Program

### Prerequisites:
- **Java 8 or higher** is required to run this program.
- Basic knowledge of how to use the command line (Terminal/Command Prompt) for compiling and running Java programs.

### Steps to Run:
1. **Clone or download the project** to your local machine.
2. **Navigate to the project directory** where the Java files are located using the terminal/command prompt.
3. **Compile the Java files**:
   ```bash
   javac ATM.java Account.java ATMTransaction.java DepositTransaction.java WithdrawTransaction.java
   ```
   This will compile all the Java files into bytecode.
4. **Run the program**:
   ```bash
   java ATM
   ```
   This will start the ATM system. Follow the on-screen instructions to use the ATM features, such as logging in, depositing/withdrawing funds, and changing your PIN.

### Example Usage:
1. On running the program, you will be prompted to log in with an **account number** and **PIN**.
2. After logging in, you will be presented with an ATM menu where you can choose to:
   - Deposit funds.
   - Withdraw funds.
   - Check your balance.
   - Change your PIN.
   - View account type.
   - Exit the ATM system.

