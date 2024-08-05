
package Model;

import java.util.Date;

public class GetVoucher {
    private int Id;
    private Voucher Voucher;
    private User User;
    private Date CreatedAt;
    private Date UpdatedAt;
    private Date DeletedAt;
    private boolean IsDelete;
    private int DeletedBy;
    
    public GetVoucher(){}

    public GetVoucher(int Id, Voucher Voucher, User User, Date CreatedAt, Date UpdatedAt, Date DeletedAt, boolean IsDelete, int DeletedBy) {
        this.Id = Id;
        this.Voucher = Voucher;
        this.User = User;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.DeletedAt = DeletedAt;
        this.IsDelete = IsDelete;
        this.DeletedBy = DeletedBy;
    }

    public GetVoucher(Voucher Voucher, User User, Date CreatedAt, Date UpdatedAt) {
        this.Voucher = Voucher;
        this.User = User;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Voucher getVoucher() {
        return Voucher;
    }

    public void setVoucher(Voucher Voucher) {
        this.Voucher = Voucher;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
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

    public int getDeletedBy() {
        return DeletedBy;
    }

    public void setDeletedBy(int DeletedBy) {
        this.DeletedBy = DeletedBy;
    }

    @Override
    public String toString() {
        return "GetVoucher{" + "Id=" + Id + ", Voucher=" + Voucher + ", User=" + User + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", DeletedAt=" + DeletedAt + ", IsDelete=" + IsDelete + ", DeletedBy=" + DeletedBy + '}';
    }
    
    
}
