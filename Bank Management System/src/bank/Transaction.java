package bank;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
        String formattedTime = timestamp.format(format);
        return "Account Number "+ accNo +"("+ type + "): " + "₹" + amount + " on " + formattedTime;
    }
}
