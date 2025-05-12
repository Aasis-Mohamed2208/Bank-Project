package bank;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtil {

    private static final Logger LOGGER = Logger.getLogger(DbUtil.class.getName());

    private static final String db_url = "jdbc:mysql://localhost:3306/bank_system";
    private static final String db_user = "root";
    private static final String db_password = "aasis_ar22..";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(db_url, db_user, db_password);
    }

    public static boolean insertAccount(Account acc) {
        String sql = "INSERT INTO accounts (acc_no, name, pin, type, balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, acc.getAccNo());
            stmt.setString(2, acc.getName());
            stmt.setInt(3, acc.getPIN());
            stmt.setString(4, acc.getType());
            stmt.setDouble(5, acc.getBalance());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting account", e);
        }
        return false;
    }

    public static Account getAccount(long accNo, int pin) {
        String sql = "SELECT * FROM accounts WHERE acc_no = ? AND pin = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accNo);
            stmt.setInt(2, pin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                double balance = rs.getDouble("balance");
                return new Account(name, accNo, pin, type, balance);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving account", e);
        }
        return null;
    }

    public static void insertTransaction(long accNo, String type, double amount, LocalDateTime localDateTime) {
        String sql = "INSERT INTO transactions (acc_no, type, amount, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accNo);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            // Convert LocalDateTime to Timestamp and set it
            stmt.setTimestamp(4, Timestamp.valueOf(localDateTime));
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting transaction", e);
        }
    }


    public static List<Transaction> getTransactions(long accNo) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE acc_no = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accNo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                transactions.add(new Transaction(accNo, type, amount, timestamp.toLocalDateTime()));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving transactions", e);
        }
        return transactions;
    }

    public static void updateBalance(long accNo, double balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE acc_no = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, balance);
            stmt.setLong(2, accNo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating balance", e);
        }
    }


}
