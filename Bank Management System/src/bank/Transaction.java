package bank;

import java.time.LocalDateTime;

public class Transaction {
    private final long accNo;
    private final String type;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(long accNo, String type, double amount, LocalDateTime timestamp) {
        this.accNo = accNo;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Account Number "+ accNo +"("+ type + "): " + "₹" + amount + " on " + timestamp;
    }
}
