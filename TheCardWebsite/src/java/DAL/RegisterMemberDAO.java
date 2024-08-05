/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import static DAL.DBContext.connection;
import Model.MemberRegis;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dat
 */
public class RegisterMemberDAO extends DBContext {

    public boolean checkExistEmail(String email) {
        String sql = "SELECT * FROM dbprojectswp391_v1.registermember \n"
                + "WHERE Email1 = ? \n"
                + "   OR Email2 = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, email);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
            return true;
        }
    }

    public static void main(String[] args) {
        RegisterMemberDAO rgm = new RegisterMemberDAO();
        UserDAO udao = new UserDAO();
        User u = udao.getUserById(1);
        MemberRegis mr = new MemberRegis(u, "hehe@gmail.com", "haha@gmail.com", "Hãy kiểm tra giúp tôi!");
        rgm.updateStatus("2");

    }

    public boolean insertRegisMember(MemberRegis memberRegis) {
        boolean insertSuccess = false;
        try {
            String sql = "INSERT INTO `dbprojectswp391_v1`.`registermember` "
                    + "(`UserID`, `Email1`, `Email2`, `Message`, `CreatedAt`) "
                    + "VALUES (?, ?, ?, ?, NOW())";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, memberRegis.getUser().getID());
            stm.setString(2, memberRegis.getEmail1());
            stm.setString(3, memberRegis.getEmail2());
            stm.setString(4, memberRegis.getMsgString());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                insertSuccess = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(RegisterMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insertSuccess;
    }

    public List<MemberRegis> getListRegisMem() {
        List<MemberRegis> listRegisMem = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbprojectswp391_v1.registermember";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserDAO udao = new UserDAO();
                User user = udao.getUserById(rs.getInt("UserID"));
                MemberRegis memberRegis = new MemberRegis(rs.getInt("ID"), user,
                        rs.getString("Email1"), rs.getString("Email2"), rs.getString("Message"),
                        rs.getDate("CreatedAt"), rs.getDate("UpdatedAt"), rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"), rs.getInt("DeletedBy"));
                listRegisMem.add(memberRegis);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRegisMem;
    }

    public MemberRegis getMemberRegist(int smid) {
        MemberRegis memberRegis = new MemberRegis();
        try {
            String sql = "SELECT * FROM dbprojectswp391_v1.registermember where ID = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, smid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserDAO udao = new UserDAO();
                User user = udao.getUserById(rs.getInt("UserID"));
                memberRegis = new MemberRegis(rs.getInt("ID"), user,
                        rs.getString("Email1"), rs.getString("Email2"), rs.getString("Message"),
                        rs.getDate("CreatedAt"), rs.getDate("UpdatedAt"), rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"), rs.getInt("DeletedBy"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memberRegis;
    }

    public void updateStatus(String idform) {
        try {
            String sql = "UPDATE `dbprojectswp391_v1`.`registermember`\n"
                    + "SET\n"
                    + "`UpdatedAt` = NOW(),\n"
                    + "`IsDelete` = 1\n" // Removed the extra comma here
                    + "WHERE `ID` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, idform);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MemberRegis getMemberRegistByUID(int imID) {
        MemberRegis memberRegis = new MemberRegis();
        try {
            String sql = "SELECT * FROM dbprojectswp391_v1.registermember where UserID = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, imID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserDAO udao = new UserDAO();
                User user = udao.getUserById(rs.getInt("UserID"));
                memberRegis = new MemberRegis(rs.getInt("ID"), user,
                        rs.getString("Email1"), rs.getString("Email2"), rs.getString("Message"),
                        rs.getDate("CreatedAt"), rs.getDate("UpdatedAt"), rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"), rs.getInt("DeletedBy"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memberRegis;
    }

}
