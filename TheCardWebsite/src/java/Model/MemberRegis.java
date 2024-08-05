/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Dat
 */
public class MemberRegis {

    private int Id;
    private User user;
    private String email1;
    private String email2;
    private String msgString;
    private Date CreatedAt;
    private Date UpdatedAt;
    private Date DeletedAt;
    private boolean IsDelete;
    private int DeletedByID;

    public MemberRegis() {
    }

    public MemberRegis(int Id, User user, String email1, String email2, String msgString, Date CreatedAt, Date UpdatedAt, Date DeletedAt, boolean IsDelete, int DeletedByID) {
        this.Id = Id;
        this.user = user;
        this.email1 = email1;
        this.email2 = email2;
        this.msgString = msgString;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.DeletedAt = DeletedAt;
        this.IsDelete = IsDelete;
        this.DeletedByID = DeletedByID;
    }

    public MemberRegis(User user, String email1, String email2, String msgString) {
        this.user = user;
        this.email1 = email1;
        this.email2 = email2;
        this.msgString = msgString;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    public Date getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(Date DeletedAt) {
        this.DeletedAt = DeletedAt;
    }

    public boolean isIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(boolean IsDelete) {
        this.IsDelete = IsDelete;
    }

    public int getDeletedByID() {
        return DeletedByID;
    }

    public void setDeletedByID(int DeletedByID) {
        this.DeletedByID = DeletedByID;
    }

    public String getMsgString() {
        return msgString;
    }

    public void setMsgString(String msgString) {
        this.msgString = msgString;
    }
    
    
}
