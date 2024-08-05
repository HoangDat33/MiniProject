package Model;

import java.util.Date;


public class NameAgreementPolicy {
    private int id;
    private String name;
    private String status;
    private Date createdAt;
    private Date UpdatedAt;
    private Date deletedAt;
    private boolean isDelete;

    public NameAgreementPolicy(int id, String name, String status, Date createdAt, Date UpdatedAt, Date deletedAt, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.UpdatedAt = UpdatedAt;
        this.deletedAt = deletedAt;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
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

    @Override
    public String toString() {
        return "NameAgreementPolicy{" + "id=" + id + ", name=" + name + ", status=" + status + ", createdAt=" + createdAt + ", UpdatedAt=" + UpdatedAt + ", deletedAt=" + deletedAt + ", isDelete=" + isDelete + '}';
    }
    
    
}
