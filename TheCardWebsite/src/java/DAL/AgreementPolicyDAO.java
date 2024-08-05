package DAL;

import static DAL.DBContext.connection;
import Model.AgreementPolicy;
import Model.NameAgreementPolicy;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AgreementPolicyDAO extends DBContext{
    
  
    public List<NameAgreementPolicy> showAgreementPolicy(){
        List<NameAgreementPolicy> list = new ArrayList<>();
        String sql = "SELECT * FROM NameAgreementPolicy;";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new NameAgreementPolicy(rs.getInt("ID"), 
                        rs.getString("Name"), 
                        rs.getString("Status"), 
                        rs.getDate("CreatedAt"), 
                        rs.getDate("UpdatedAt"), 
                        rs.getDate("DeletedAt"), 
                        rs.getBoolean("IsDelete")));
            }
            return list;
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public NameAgreementPolicy getAgreementPolicyByName(String name) {
        String sql = "SELECT * FROM NameAgreementPolicy where Name  = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                NameAgreementPolicy y = new NameAgreementPolicy(rs.getInt("ID"), 
                        rs.getString("Name"), 
                        rs.getString("Status"), 
                        rs.getDate("CreatedAt"), 
                        rs.getDate("UpdatedAt"), 
                        rs.getDate("DeletedAt"), 
                        rs.getBoolean("IsDelete"));
                return y;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    public AgreementPolicy getAgreementPolicyByID(String id) {
        String sql = "SELECT * FROM AgreementPolicy where ID  = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                AgreementPolicy y = new AgreementPolicy(rs.getInt("ID"), 
                        rs.getInt("IDNameAgreementPolicy"), 
                        rs.getString("Message"), 
                        rs.getDate("CreatedAt"), 
                        rs.getDate("UpdatedAt"), 
                        rs.getDate("DeletedAt"), 
                        rs.getBoolean("IsDelete"));
                return y;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public NameAgreementPolicy getNameAgreementPolicyByID(String id) {
        String sql = "SELECT * FROM NameAgreementPolicy where ID  = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                NameAgreementPolicy y = new NameAgreementPolicy(rs.getInt("ID"), 
                        rs.getString("Name"), 
                        rs.getString("Status"), 
                        rs.getDate("CreatedAt"), 
                        rs.getDate("UpdatedAt"), 
                        rs.getDate("DeletedAt"), 
                        rs.getBoolean("IsDelete"));
                return y;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    
    public void updateNameAgreementPolicy(String id, String name, String status) {
        String sql = "Update NameAgreementPolicy set Name = ?,Status= ?, UpdatedAt =now() where ID = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, status);
            ps.setString(3, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateAgreementPolicy(String id, String message) {
        String sql = "Update AgreementPolicy set Message = ?, UpdatedAt =now() where IDNameAgreementPolicy = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, message);
            ps.setString(2, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void deleteAgreementPolicy(String id) {
        String sql = "DELETE FROM AgreementPolicy WHERE IDNameAgreementPolicy = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void deleteNameAgreementPolicy(String id) {
        String sql = "DELETE FROM NameAgreementPolicy WHERE ID = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void createNameAgreementPolicy(String name, String status) {
        String sql = "INSERT INTO NameAgreementPolicy (Name, Status, CreatedAt, UpdatedAt) VALUES  (?, ?, NOW(), NOW());";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, status);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void createAgreementPolicy(int idname, String message) {
        String sql = "INSERT INTO AgreementPolicy (IDNameAgreementPolicy, Message, CreatedAt, UpdatedAt) values(?,?,NOW(),NOW());";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idname);
            ps.setString(2, message);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    public static void main(String[] args) {
        AgreementPolicyDAO d = new AgreementPolicyDAO();
        System.out.println(d.getAgreementPolicyByName("Thỏa thuận người dùng"));
        //d.createNameAgreementPolicy("1", "Active");
    }
}
