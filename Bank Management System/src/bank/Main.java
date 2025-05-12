package bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.welcome();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login to Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Account Number: ");
                    long accNo = sc.nextLong();
                    System.out.print("Set 4-digit PIN: ");
                    int PIN = sc.nextInt();
                    sc.nextLine(); // clear newline
                    System.out.print("Enter Account type (Savings/Current): ");
                    String accType = sc.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    double balance = sc.nextDouble();

                    boolean created = bank.createAccount(name, accNo, PIN, accType, balance);
                    System.out.println(created ? "Account created successfully!" : "Account creation failed.");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    long loginAccNo = sc.nextLong();
                    System.out.print("Enter the PIN: ");
                    int loginPIN = sc.nextInt();

                    Account user = bank.authenticateAccount(loginAccNo, loginPIN);
                    if (user != null) {
                        int opt;
                        do {
                            System.out.println("\n--- Account Menu ---");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Transaction History");
                            System.out.println("5. Logout");
                            System.out.print("Choose option: ");
                            opt = sc.nextInt();

                            switch (opt) {
                                case 1:
                                    System.out.print("Enter deposit amount: ");
                                    double depAmount = sc.nextDouble();
                                    bank.toDeposit(loginAccNo, loginPIN, depAmount);
                                    break;
                                case 2:
                                    System.out.print("Enter amount to withdraw: ");
                                    double wdAmount = sc.nextDouble();
                                    bank.toWithdraw(loginAccNo, loginPIN, wdAmount);
                                    break;
                                case 3:
                                    bank.checkBalance(loginAccNo, loginPIN);
                                    break;
                                case 4:
                                    bank.showTransactionHistory(loginAccNo, loginPIN);
                                    break;
                                case 5:
                                    System.out.println("Logged Out.");
                                    break;
                                default:
                                    System.out.println("Invalid Option.");
                            }
                        } while (opt != 5);
                    }
                    break;

                case 3:
                    System.out.println("Thank you for Banking with us!");
                    break;

                default:
                    System.out.println("Invalid Option.");
            }
        } while (choice != 3);

        sc.close();
    }
}
