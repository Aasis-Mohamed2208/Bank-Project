package bank;

import java.time.LocalDateTime;
import java.util.List;

public class Account {
    private final String name;
    private final long accNo;
    private final int PIN;
    private final String type;
    private double balance;

    public Account(String name, long accNo, int PIN, String type, double balance) {
        this.name = name;
        this.accNo = accNo;
        this.PIN = PIN;
        this.type = type;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public long getAccNo() {
        return accNo;
    }

    public int getPIN() {
        return PIN;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPassword(int pin) {
        return this.PIN == pin;
    }

    public void deposit(double amount) {
        balance += amount;
        DbUtil.insertTransaction(accNo, "Deposit", amount, LocalDateTime.now());
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            DbUtil.insertTransaction(accNo, "Withdraw", amount, LocalDateTime.now());
            return true;
        }
        return false;
    }

    public void showBalance() {
        System.out.println("Current balance: ₹" + balance);
    }

    public void showTransaction() {
        List<Transaction> txs = DbUtil.getTransactions(accNo);
        if (txs.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction tx : txs) {
                System.out.println(tx);
            }
        }
    }
}
