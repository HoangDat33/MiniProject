/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Dat
 */
public class TempTransaction {

    private int userId;
    private double amount;
    private long createTime;

    public TempTransaction() {
    }

    public TempTransaction(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
    
    public TempTransaction(int userId, double amount, long createTime) {
        this.userId = userId;
        this.amount = amount;
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    
    
}
