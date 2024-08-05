package Model;

import java.util.Date;

public class News {

    private int ID;
    private String Title;
    private String Description;
    private String ContentFirst;
    private String ContentBody;
    private String ContentEnd;
    private CategoriesNews News;
    private int CategoriesNewsID;
    private boolean Hotnews;
    private String Image;
    private String DescriptionImage;
    private Date CreatedAt;
    private Date UpdatedAt;
    private Date DeletedAt;
    private Boolean IsDelete;
    private int DeletedBy;

    public News() {
    }

    public News(int ID, String Title, String Description, String ContentFirst, String ContentBody, String ContentEnd, CategoriesNews News, boolean Hotnews, String Image, String DescriptionImage, Date CreatedAt, Date UpdatedAt, Date DeletedAt, Boolean IsDelete, int DeletedBy) {
        this.ID = ID;
        this.Title = Title;
        this.Description = Description;
        this.ContentFirst = ContentFirst;
        this.ContentBody = ContentBody;
        this.ContentEnd = ContentEnd;
        this.News = News;
        this.Hotnews = Hotnews;
        this.Image = Image;
        this.DescriptionImage = DescriptionImage;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.DeletedAt = DeletedAt;
        this.IsDelete = IsDelete;
        this.DeletedBy = DeletedBy;
    }

    public News(String Title, String Description, String ContentFirst, String ContentBody, String ContentEnd, int CategoriesNewsID, boolean Hotnews, String Image, String DescriptionImage) {
        this.Title = Title;
        this.Description = Description;
        this.ContentFirst = ContentFirst;
        this.ContentBody = ContentBody;
        this.ContentEnd = ContentEnd;
        this.CategoriesNewsID = CategoriesNewsID;
        this.Hotnews = Hotnews;
        this.Image = Image;
        this.DescriptionImage = DescriptionImage;
    }

    public News(int ID, String Title, String Description, String ContentFirst, String ContentBody, String ContentEnd, int CategoriesNewsID, boolean Hotnews, String Image, String DescriptionImage) {
        this.ID = ID;
        this.Title = Title;
        this.Description = Description;
        this.ContentFirst = ContentFirst;
        this.ContentBody = ContentBody;
        this.ContentEnd = ContentEnd;
        this.CategoriesNewsID = CategoriesNewsID;
        this.Hotnews = Hotnews;
        this.Image = Image;
        this.DescriptionImage = DescriptionImage;
    }

    public int getCategoriesNewsID() {
        return CategoriesNewsID;
    }

    public void setCategoriesNewsID(int CategoriesNewsID) {
        this.CategoriesNewsID = CategoriesNewsID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getContentFirst() {
        return ContentFirst;
    }

    public void setContentFirst(String ContentFirst) {
        this.ContentFirst = ContentFirst;
    }

    public String getContentBody() {
        return ContentBody;
    }

    public void setContentBody(String ContentBody) {
        this.ContentBody = ContentBody;
    }

    public String getContentEnd() {
        return ContentEnd;
    }

    public void setContentEnd(String ContentEnd) {
        this.ContentEnd = ContentEnd;
    }

    public CategoriesNews getNews() {
        return News;
    }

    public void setNews(CategoriesNews News) {
        this.News = News;
    }

    public boolean isHotnews() {
        return Hotnews;
    }

    public void setHotnews(boolean Hotnews) {
        this.Hotnews = Hotnews;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescriptionImage() {
        return DescriptionImage;
    }

    public void setDescriptionImage(String DescriptionImage) {
        this.DescriptionImage = DescriptionImage;
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
        return "News{" + "ID=" + ID + ", Title=" + Title + ", Description=" + Description + ", ContentFirst=" + ContentFirst + ", ContentBody=" + ContentBody + ", ContentEnd=" + ContentEnd + ", News=" + News + ", Hotnews=" + Hotnews + ", Image=" + Image + ", DescriptionImage=" + DescriptionImage + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", DeletedAt=" + DeletedAt + ", IsDelete=" + IsDelete + ", DeletedBy=" + DeletedBy + '}';
    }
}
