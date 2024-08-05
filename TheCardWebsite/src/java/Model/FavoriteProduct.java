/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;

public class FavoriteProduct {
    private int id;
    private User u;
    private ProductCategories pc;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private boolean isDelete;
    private int deleteBy;

    public FavoriteProduct() {
    }

    public FavoriteProduct(int id, User u, ProductCategories pc, Date createdAt, Date updatedAt, Date deletedAt, boolean isDelete, int delêtBy) {
        this.id = id;
        this.u = u;
        this.pc = pc;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDelete = isDelete;
        this.deleteBy = deleteBy;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public ProductCategories getPc() {
        return pc;
    }

    public void setPc(ProductCategories pc) {
        this.pc = pc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(int delêtBy) {
        this.deleteBy = delêtBy;
    }
    
    
}
