package Model;

import java.util.Date;


public class AgreementPolicy {
    private int id;
    private int idNameAgreementPolicy;
    private String message;
    private Date createdAt;
    private Date UpdatedAt;
    private Date deletedAt;
    private boolean isDelete;

    public AgreementPolicy(int id, int idNameAgreementPolicy, String message, Date createdAt, Date UpdatedAt, Date deletedAt, boolean isDelete) {
        this.id = id;
        this.idNameAgreementPolicy = idNameAgreementPolicy;
        this.message = message;
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

    public int getIdNameAgreementPolicy() {
        return idNameAgreementPolicy;
    }

    public void setIdNameAgreementPolicy(int idNameAgreementPolicy) {
        this.idNameAgreementPolicy = idNameAgreementPolicy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "AgreementPolicy{" + "id=" + id + ", idNameAgreementPolicy=" + idNameAgreementPolicy + ", message=" + message + ", createdAt=" + createdAt + ", UpdatedAt=" + UpdatedAt + ", deletedAt=" + deletedAt + ", isDelete=" + isDelete + '}';
    }
    
    
}
