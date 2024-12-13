public abstract class ATMTransaction {
    protected Account account;
    protected double amount;

    public ATMTransaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public abstract boolean performTransaction();
    public abstract void generateReceipt();
}
