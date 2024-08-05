package DAL;

import static DAL.DBContext.connection;
import Model.GetVoucher;
import Model.Voucher;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetVoucherDAO extends DBContext {

    public boolean checkExistVoucher(int userId, int voucherId) {
        String sql = "SELECT `getvoucher`.`ID`,\n"
                + "    `getvoucher`.`VoucherID`,\n"
                + "    `getvoucher`.`UserID`,\n"
                + "    `getvoucher`.`CreatedAt`,\n"
                + "    `getvoucher`.`UpdatedAt`,\n"
                + "    `getvoucher`.`DeletedAt`,\n"
                + "    `getvoucher`.`IsDelete`,\n"
                + "    `getvoucher`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`getvoucher`"
                + "WHERE `getvoucher`.`UserID` = ? AND `getvoucher`.`VoucherID` = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, voucherId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public List<GetVoucher> getListVoucherByUserId(int userId) {
        List<GetVoucher> dataVoucher = new ArrayList<>();
        String sql = "SELECT `getvoucher`.`ID`,\n"
                + "    `getvoucher`.`VoucherID`,\n"
                + "    `getvoucher`.`UserID`,\n"
                + "    `getvoucher`.`CreatedAt`,\n"
                + "    `getvoucher`.`UpdatedAt`,\n"
                + "    `getvoucher`.`DeletedAt`,\n"
                + "    `getvoucher`.`IsDelete`,\n"
                + "    `getvoucher`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`getvoucher`"
                + "WHERE `getvoucher`.`UserID` = ? AND `getvoucher`.`IsDelete` = 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            VoucherDAO v = new VoucherDAO();
            UserDAO u = new UserDAO();
            while (rs.next()) {
                GetVoucher g = new GetVoucher(rs.getInt("ID"),
                        v.getVoucherByID(rs.getInt("VoucherID")),
                        u.getUserById(rs.getInt("UserID")),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataVoucher.add(g);
            }
            return dataVoucher;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertVoucher(int voucherId, int userId) {
        String sql = "INSERT INTO `dbprojectswp391_v1`.`getvoucher` "
                + "(`VoucherID`, `UserID`, `CreatedAt`) "
                + "VALUES (?, ?, NOW())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, voucherId);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public GetVoucher getVoucher(int userId, int voucherId) {
        String selectSql = "SELECT `ID`, `VoucherID`, `UserID`, `CreatedAt`, `UpdatedAt`, `DeletedAt`, `IsDelete`, `DeletedBy` "
                + "FROM `dbprojectswp391_v1`.`getvoucher` "
                + "WHERE `UserID` = ? AND `VoucherID` = ?";
        String updateSql = "UPDATE `dbprojectswp391_v1`.`getvoucher` "
                + "SET `IsDelete` = 1, `DeletedBy` = ? "
                + "WHERE `UserID` = ? AND `VoucherID` = ?";

        GetVoucher g = null;
        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql); PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

            // Lấy thông tin voucher
            selectStmt.setInt(1, userId);
            selectStmt.setInt(2, voucherId);
            try (ResultSet rs = selectStmt.executeQuery()) {
                VoucherDAO v = new VoucherDAO();
                UserDAO u = new UserDAO();

                if (rs.next()) {
                    g = new GetVoucher(rs.getInt("ID"),
                            v.getVoucherByID(rs.getInt("VoucherID")),
                            u.getUserById(rs.getInt("UserID")),
                            rs.getDate("CreatedAt"),
                            rs.getDate("UpdatedAt"),
                            rs.getDate("DeletedAt"),
                            rs.getBoolean("IsDelete"),
                            rs.getInt("DeletedBy"));

                    // Cập nhật trạng thái voucher
                    updateStmt.setInt(1, userId);
                    updateStmt.setInt(2, userId);
                    updateStmt.setInt(3, voucherId);
                    updateStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Thay thế bằng logging nếu cần
        }

        return g;
    }

    public static void main(String[] args) {
        GetVoucherDAO g = new GetVoucherDAO();
//        System.out.println(g.checkExistVoucher(1, 1));
//        g.insertVoucher(1, 1);
        for (GetVoucher get : g.getListVoucherByUserId(1)) {
            System.out.println(get);
        }
        System.out.println(g.getVoucher(1, 1));
    }
}
