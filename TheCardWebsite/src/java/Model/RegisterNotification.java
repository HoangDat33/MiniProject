
package Model;

import java.util.Date;

public class RegisterNotification {

    private int Id;
    private String Name;
    private String Email;
    private String Message;
    private Date CreatedAt;
    private Date UpdatedAt;
    private Date DeletedAt;
    private Boolean IsDelete;
    private int DeletedBy;
    
    public RegisterNotification () {}

    public RegisterNotification(int Id, String Name, String Email, String Message, Date CreatedAt, Date UpdatedAt, Date DeletedAt, Boolean IsDelete, int DeletedBy) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Message = Message;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.DeletedAt = DeletedAt;
        this.IsDelete = IsDelete;
        this.DeletedBy = DeletedBy;
    }

    public RegisterNotification(String Name, String Email, String Message) {
        this.Name = Name;
        this.Email = Email;
        this.Message = Message;
    }
    
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
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

    public Boolean getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(Boolean IsDelete) {
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
        return "RegisterNotification{" + "Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", Message=" + Message + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", DeletedAt=" + DeletedAt + ", IsDelete=" + IsDelete + ", DeletedBy=" + DeletedBy + '}';
    }
    
    
}
