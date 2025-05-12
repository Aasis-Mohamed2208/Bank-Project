package bank;

import java.util.HashMap;

public class Bank {
    private static final String bankName = "National Bank";
    private final HashMap<Long, Account> accounts = new HashMap<>();

    public void welcome() {
        System.out.println("Welcome to " + bankName);
    }

    public boolean createAccount(String name, long accNo, int PIN, String type, double balance) {
        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists.");
            return false;
        }

        Account newAcc = new Account(name, accNo, PIN, type, balance);
        boolean success = DbUtil.insertAccount(newAcc);
        if (success) {
            accounts.put(accNo, newAcc);
        }
        return success;
    }

    public Account authenticateAccount(long accNo, int PIN) {
        if (accounts.containsKey(accNo)) {
            Account account = accounts.get(accNo);
            if (account.verifyPassword(PIN)) {
                return account;
            }
        } else {
            Account account = DbUtil.getAccount(accNo, PIN);
            if (account != null) {
                accounts.put(accNo, account);
                return account;
            }
        }
        System.out.println("Authentication failed.");
        return null;
    }

    public void toDeposit(long accNo, int PIN, double amount) {
        Account account = authenticateAccount(accNo, PIN);
        if (account != null) {
            account.deposit(amount);
            DbUtil.updateBalance(accNo, account.getBalance());
            System.out.println("₹" + amount + " deposited.");
        }
    }

    public void toWithdraw(long accNo, int PIN, double amount) {
        Account account = authenticateAccount(accNo, PIN);
        if (account != null && account.withdraw(amount)) {
            System.out.println("₹" + amount + " withdrawn.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void checkBalance(long accNo, int PIN) {
        Account account = authenticateAccount(accNo, PIN);
        if (account != null) {
            account.showBalance();
        }
    }

    public void showTransactionHistory(long accNo, int PIN) {
        Account account = authenticateAccount(accNo, PIN);
        if (account != null) {
            account.showTransaction();
        }
    }
}
