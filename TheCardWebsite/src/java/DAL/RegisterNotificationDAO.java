package DAL;

import static DAL.DBContext.connection;
import Model.CategoriesNews;
import Model.RegisterNotification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterNotificationDAO extends DBContext {

    public void insertRegisterNotification(RegisterNotification register) {
        String sql = "INSERT INTO `dbprojectswp391_v1`.`regiternotification` "
                + "(`Name`, `Email`, `Message`, `CreatedAt`, `IsDelete`) "
                + "VALUES (?, ?, ?, NOW(), ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, register.getName());
            ps.setString(2, register.getEmail());
            ps.setString(3, register.getMessage());
            ps.setInt(4, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean getEmailRegistered(String email) {
        String sql = "SELECT `regiternotification`.`ID`,\n"
                + "    `regiternotification`.`Name`,\n"
                + "    `regiternotification`.`Email`,\n"
                + "    `regiternotification`.`Message`,\n"
                + "    `regiternotification`.`CreatedAt`,\n"
                + "    `regiternotification`.`UpdatedAt`,\n"
                + "    `regiternotification`.`DeletedAt`,\n"
                + "    `regiternotification`.`IsDelete`,\n"
                + "    `regiternotification`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`regiternotification`"
                + "WHERE `regiternotification`.`Email` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public void acceptRegister(int id) {
        String sql = "UPDATE `dbprojectswp391_v1`.`regiternotification`\n"
                + "SET\n"
                + "`UpdatedAt` = NOW(),\n"
                + "`IsDelete` = 0\n"
                + "WHERE `ID` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteRegister(int id, int adminId) {
        String sql = "UPDATE `dbprojectswp391_v1`.`regiternotification`\n"
                + "SET\n"
                + "`UpdatedAt` = NOW(),\n"
                + "`DeletedAt` = NOW(),\n"
                + "`IsDelete` = 1,\n"
                + "`DeletedBy` = ?\n"
                + "WHERE `ID` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, adminId);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<RegisterNotification> getUserRegisterNotification() {
        List<RegisterNotification> data = new ArrayList<>();
        String sql = "SELECT `regiternotification`.`ID`,\n"
                + "    `regiternotification`.`Name`,\n"
                + "    `regiternotification`.`Email`,\n"
                + "    `regiternotification`.`Message`,\n"
                + "    `regiternotification`.`CreatedAt`,\n"
                + "    `regiternotification`.`UpdatedAt`,\n"
                + "    `regiternotification`.`DeletedAt`,\n"
                + "    `regiternotification`.`IsDelete`,\n"
                + "    `regiternotification`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`regiternotification`;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RegisterNotification rn = new RegisterNotification(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Message"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(rn);
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<RegisterNotification> getSendRegisterNotification() {
        List<RegisterNotification> data = new ArrayList<>();
        String sql = "SELECT `regiternotification`.`ID`,\n"
                + "    `regiternotification`.`Name`,\n"
                + "    `regiternotification`.`Email`,\n"
                + "    `regiternotification`.`Message`,\n"
                + "    `regiternotification`.`CreatedAt`,\n"
                + "    `regiternotification`.`UpdatedAt`,\n"
                + "    `regiternotification`.`DeletedAt`,\n"
                + "    `regiternotification`.`IsDelete`,\n"
                + "    `regiternotification`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`regiternotification`"
                + "WHERE `regiternotification`.`IsDelete` = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RegisterNotification rn = new RegisterNotification(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Message"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(rn);
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<RegisterNotification> getUserRegisterNotificationAdmin(String email) {
        List<RegisterNotification> data = new ArrayList<>();
        String sql = "SELECT `regiternotification`.`ID`,\n"
                + "    `regiternotification`.`Name`,\n"
                + "    `regiternotification`.`Email`,\n"
                + "    `regiternotification`.`Message`,\n"
                + "    `regiternotification`.`CreatedAt`,\n"
                + "    `regiternotification`.`UpdatedAt`,\n"
                + "    `regiternotification`.`DeletedAt`,\n"
                + "    `regiternotification`.`IsDelete`,\n"
                + "    `regiternotification`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`regiternotification`"
                + "WHERE `regiternotification`.`Email` LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + email + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RegisterNotification rn = new RegisterNotification(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Message"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(rn);
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        RegisterNotificationDAO u = new RegisterNotificationDAO();
        RegisterNotification r = new RegisterNotification(1,
                "ABC", "abc", null, null,
                null, null, false, 0);
        //u.insertRegisterNotification(r);
        for (RegisterNotification k : u.getUserRegisterNotificationAdmin("dungpahe")) {
            System.out.println(k);
        }
    }
}
