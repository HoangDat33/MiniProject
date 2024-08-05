
package Model;

import java.util.Date;


public class Voucher {
    private int Id;
    private String Title;
    private String PurchaseOffer;
    private String ApplyDescription;
    private String Image;
    private int ApplyBrandID;
    private int ApplyProductID;
    private Date FromDate;
    private Date ToDate;
    private double Pricefrom;
    private double Discount;
    private double DiscountMax;
    private int Quantity;
    private Date CreatedAt;
    private Date UpdatedAt;
    private Date DeletedAt;
    private boolean IsDelete;
    private int DeletedBy;

    
    public Voucher(){}
    public Voucher(int Id, String Title, String PurchaseOffer, String ApplyDescription, String Image, int ApplyBrandID, int ApplyProductID, Date FromDate, Date ToDate, double Pricefrom, double Discount, double DiscountMax,int Quantity, Date CreatedAt, Date UpdatedAt, Date DeletedAt, boolean IsDelete, int DeletedBy) {
        this.Id = Id;
        this.Title = Title;
        this.PurchaseOffer = PurchaseOffer;
        this.ApplyDescription = ApplyDescription;
        this.Image = Image;
        this.ApplyBrandID = ApplyBrandID;
        this.ApplyProductID = ApplyProductID;
        this.FromDate = FromDate;
        this.ToDate = ToDate;
        this.Pricefrom = Pricefrom;
        this.Discount = Discount;
        this.DiscountMax = DiscountMax;
        this.Quantity = Quantity;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.DeletedAt = DeletedAt;
        this.IsDelete = IsDelete;
        this.DeletedBy = DeletedBy;
    }

    public Voucher(String Title, String PurchaseOffer, String ApplyDescription, String Image, int ApplyBrandID, int ApplyProductID, Date FromDate, Date ToDate, double Pricefrom, double Discount, double DiscountMax, int Quantity) {
        this.Title = Title;
        this.PurchaseOffer = PurchaseOffer;
        this.ApplyDescription = ApplyDescription;
        this.Image = Image;
        this.ApplyBrandID = ApplyBrandID;
        this.ApplyProductID = ApplyProductID;
        this.FromDate = FromDate;
        this.ToDate = ToDate;
        this.Pricefrom = Pricefrom;
        this.Discount = Discount;
        this.DiscountMax = DiscountMax;
        this.Quantity = Quantity;
    }
    
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPurchaseOffer() {
        return PurchaseOffer;
    }

    public void setPurchaseOffer(String PurchaseOffer) {
        this.PurchaseOffer = PurchaseOffer;
    }

    public String getApplyDescription() {
        return ApplyDescription;
    }

    public void setApplyDescription(String ApplyDescription) {
        this.ApplyDescription = ApplyDescription;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getApplyBrandID() {
        return ApplyBrandID;
    }

    public void setApplyBrandID(int ApplyBrandID) {
        this.ApplyBrandID = ApplyBrandID;
    }

    public int getApplyProductID() {
        return ApplyProductID;
    }

    public void setApplyProductID(int ApplyProductID) {
        this.ApplyProductID = ApplyProductID;
    }

    public Date getFromDate() {
        return FromDate;
    }

    public void setFromDate(Date FromDate) {
        this.FromDate = FromDate;
    }

    public Date getToDate() {
        return ToDate;
    }

    public void setToDate(Date ToDate) {
        this.ToDate = ToDate;
    }

    public double getPricefrom() {
        return Pricefrom;
    }

    public void setPricefrom(double Pricefrom) {
        this.Pricefrom = Pricefrom;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public double getDiscountMax() {
        return DiscountMax;
    }

    public void setDiscountMax(double DiscountMax) {
        this.DiscountMax = DiscountMax;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
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
        return "Voucher{" + "Id=" + Id + ", Title=" + Title + ", PurchaseOffer=" + PurchaseOffer + ", ApplyDescription=" + ApplyDescription + ", Image=" + Image + ", ApplyBrandID=" + ApplyBrandID + ", ApplyProductID=" + ApplyProductID + ", FromDate=" + FromDate + ", ToDate=" + ToDate + ", Pricefrom=" + Pricefrom + ", Discount=" + Discount + ", DiscountMax=" + DiscountMax + ", Quantity=" + Quantity + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", DeletedAt=" + DeletedAt + ", IsDelete=" + IsDelete + ", DeletedBy=" + DeletedBy + '}';
    }
}

