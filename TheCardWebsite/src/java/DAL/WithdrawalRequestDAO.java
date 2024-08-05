package DAL;

import Model.WithdrawalRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WithdrawalRequestDAO  extends DBContext{
    
    public void create(WithdrawalRequest request) throws SQLException {
        String sql = "INSERT INTO withdrawal_requests (user_id, account_number, bank_name, amount, status, transaction_details) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, request.getUserId());
            pstmt.setString(2, request.getAccountNumber());
            pstmt.setString(3, request.getBankName());
            pstmt.setBigDecimal(4, request.getAmount());
            pstmt.setString(5, request.getStatus());
            pstmt.setString(6, request.getTransactionDetails());
            pstmt.executeUpdate();
        }
    }

    public WithdrawalRequest getById(int id){
        String sql = "SELECT * FROM withdrawal_requests WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                 UserDAO userDao = new UserDAO();
                if (rs.next()) {
                    WithdrawalRequest request = new WithdrawalRequest();
                    request.setId(rs.getInt("id"));
                    request.setUserId(rs.getInt("user_id"));
                    request.setAccountNumber(rs.getString("account_number"));
                    request.setBankName(rs.getString("bank_name"));
                    request.setAmount(rs.getBigDecimal("amount"));
                    request.setStatus(rs.getString("status"));
                    request.setCreatedAt(rs.getTimestamp("created_at"));
                    request.setUpdatedAt(rs.getTimestamp("updated_at"));
                     request.setUser(userDao.getUserById(rs.getInt("user_id")));
                    return request;
                }
            }
        }catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<WithdrawalRequest> getAll() {
        String sql = "SELECT * FROM withdrawal_requests";
        List<WithdrawalRequest> requests = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            UserDAO userDao = new UserDAO();
            while (rs.next()) {
                WithdrawalRequest request = new WithdrawalRequest();
                request.setId(rs.getInt("id"));
                request.setUserId(rs.getInt("user_id"));
                request.setAccountNumber(rs.getString("account_number"));
                request.setBankName(rs.getString("bank_name"));
                request.setAmount(rs.getBigDecimal("amount"));
                request.setStatus(rs.getString("status"));
                request.setCreatedAt(rs.getTimestamp("created_at"));
                request.setUpdatedAt(rs.getTimestamp("updated_at"));
                request.setTransactionDetails(rs.getString("transaction_details"));
                request.setUser(userDao.getUserById(rs.getInt("user_id")));
                requests.add(request);
            }
        }catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return requests;
    }
    
    public List<WithdrawalRequest> getAllByUser(int user_id) {
        String sql = "SELECT * FROM withdrawal_requests where user_id=?";
        List<WithdrawalRequest> requests = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                WithdrawalRequest request = new WithdrawalRequest();
                request.setId(rs.getInt("id"));
                request.setUserId(rs.getInt("user_id"));
                request.setAccountNumber(rs.getString("account_number"));
                request.setBankName(rs.getString("bank_name"));
                request.setAmount(rs.getBigDecimal("amount"));
                request.setStatus(rs.getString("status"));
                request.setCreatedAt(rs.getTimestamp("created_at"));
                request.setUpdatedAt(rs.getTimestamp("updated_at"));
                request.setTransactionDetails(rs.getString("transaction_details"));
                requests.add(request);
            }
        }catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return requests;
    }

    public void update(WithdrawalRequest request) throws SQLException {
        String sql = "UPDATE withdrawal_requests SET user_id = ?, account_number = ?, bank_name = ?, amount = ?, status = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, request.getUserId());
            pstmt.setString(2, request.getAccountNumber());
            pstmt.setString(3, request.getBankName());
            pstmt.setBigDecimal(4, request.getAmount());
            pstmt.setString(5, request.getStatus());
            pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(7, request.getId());
            pstmt.executeUpdate();
        }
    }
    
    public void updateStatus(WithdrawalRequest request, String status) throws SQLException {
        String sql = "UPDATE withdrawal_requests SET status = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(3, request.getId());
            int result = pstmt.executeUpdate();
            if(result > 0 && status.equals("APPROVED")) {
                UserDAO userDao = new UserDAO();
                userDao.updateBanlance(request.getUser().getBalance() - Double.parseDouble(request.getAmount() + ""), request.getUserId());
            }
        }catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM withdrawal_requests WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
