import java.text.SimpleDateFormat;
import java.util.Date;

public class WithdrawTransaction extends ATMTransaction {

    public WithdrawTransaction(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public boolean performTransaction() {
        return account.withdraw(amount);
    }

    @Override
    public void generateReceipt() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String transactionDate = sdf.format(new Date()); // Get current date and time
        System.out.println("\n\t\t------------------- TRANSACTION RECEIPT -------------------");
        System.out.println("\t\t\tTransaction Type: Withdrawal");
        System.out.println("\t\t\tDate: " + transactionDate);
        System.out.println("\t\t\tAccount Number: " + account.getAccountNumber());
        System.out.println("\t\t\tAmount Withdrawn: $" + String.format("%.2f", amount));
        System.out.println("\t\t\tNew Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("\t\t------------------------------------------------------------");
    }
}
