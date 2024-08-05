-- Drop database if exits
DROP DATABASE IF EXISTS DBProjectSWP391_V1;

-- Create database
CREATE DATABASE DBProjectSWP391_V1;

-- Use database
USE DBProjectSWP391_V1;

-- Setup
SET @sql = NULL;
SELECT GROUP_CONCAT('ALTER TABLE ', table_name, ' DROP FOREIGN KEY ', constraint_name, ';') INTO @sql
FROM information_schema.table_constraints
WHERE constraint_type = 'FOREIGN KEY'
AND table_schema = DATABASE();

SET @sql = NULL;
SELECT GROUP_CONCAT('DROP TABLE IF EXISTS ', table_name, ';') INTO @sql
FROM information_schema.tables
WHERE table_schema = DATABASE();

-- Create table UserRole
CREATE TABLE UserRole (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    RoleName NVARCHAR(255),
    Description NVARCHAR(255),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN DEFAULT 0
);
-- Trigger UserRole;
DELIMITER //
CREATE TRIGGER before_UserRoles_insert
BEFORE INSERT ON UserRole
FOR EACH ROW
BEGIN
    IF NEW.UpdatedAt IS NOT NULL AND NEW.UpdatedAt < NEW.CreatedAt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UpdatedAt cannot be before CreatedAt';
    END IF;
    IF NEW.DeletedAt IS NOT NULL AND (NEW.DeletedAt < NEW.CreatedAt OR NEW.DeletedAt < NEW.UpdatedAt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DeletedAt cannot be before CreatedAt or UpdatedAt';
    END IF;
END;
//
CREATE TRIGGER before_UserRoles_update
BEFORE UPDATE ON UserRole
FOR EACH ROW
BEGIN
    IF NEW.UpdatedAt IS NOT NULL AND NEW.UpdatedAt < NEW.CreatedAt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UpdatedAt cannot be before CreatedAt';
    END IF;
    IF NEW.DeletedAt IS NOT NULL AND (NEW.DeletedAt < NEW.CreatedAt OR NEW.DeletedAt < NEW.UpdatedAt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DeletedAt cannot be before CreatedAt or UpdatedAt';
    END IF;
END;
//
DELIMITER ;


-- Create tabale user
CREATE TABLE User (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName NVARCHAR(55),
    LastName NVARCHAR(55),
    Email VARCHAR(55),
    Phone VARCHAR(10),
    UserBalance DOUBLE DEFAULT 0,
    RoleID INT DEFAULT 2,
    IsMember Boolean default False,
    GetNotifications Boolean default False,
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN DEFAULT 0,
    DeletedBy INT,
    FOREIGN KEY (RoleID) REFERENCES UserRole(ID)
);

-- Trigger User;
DELIMITER //
CREATE TRIGGER before_Users_insert
BEFORE INSERT ON User
FOR EACH ROW
BEGIN
	IF NEW.UserBalance < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UserBalance cannot be negative';
    END IF;
    IF NEW.UpdatedAt IS NOT NULL AND NEW.UpdatedAt < NEW.CreatedAt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UpdatedAt cannot be before CreatedAt';
    END IF;
    IF NEW.DeletedAt IS NOT NULL AND (NEW.DeletedAt < NEW.CreatedAt OR NEW.DeletedAt < NEW.UpdatedAt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DeletedAt cannot be before CreatedAt or UpdatedAt';
    END IF;
END;
//
CREATE TRIGGER before_Users_update
BEFORE UPDATE ON User
FOR EACH ROW
BEGIN
	IF NEW.UserBalance < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UserBalance cannot be negative';
    END IF;
    IF NEW.UpdatedAt IS NOT NULL AND NEW.UpdatedAt < NEW.CreatedAt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UpdatedAt cannot be before CreatedAt';
    END IF;
    IF NEW.DeletedAt IS NOT NULL AND (NEW.DeletedAt < NEW.CreatedAt OR NEW.DeletedAt < NEW.UpdatedAt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DeletedAt cannot be before CreatedAt or UpdatedAt';
    END IF;
END;
//
DELIMITER ;

-- Create table authentication
CREATE TABLE Authentication (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    UserKey VARCHAR(40), -- Username / GoogleID
    PassWord VARCHAR(255),
    VerifyEmail VARCHAR(40),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN DEFAULT 0,
    DeletedByID INT,
    FOREIGN KEY (UserID) REFERENCES User(ID) 
);

-- Trigger authentication
DELIMITER //
CREATE TRIGGER before_Authentication_insert
BEFORE INSERT ON Authentication
FOR EACH ROW
BEGIN
    IF NEW.UpdatedAt IS NOT NULL AND NEW.UpdatedAt < NEW.CreatedAt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UpdatedAt cannot be before CreatedAt';
    END IF;
    IF NEW.DeletedAt IS NOT NULL AND (NEW.DeletedAt < NEW.CreatedAt OR NEW.DeletedAt < NEW.UpdatedAt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DeletedAt cannot be before CreatedAt or UpdatedAt';
    END IF;
END;
//
CREATE TRIGGER before_Authentication_update
BEFORE UPDATE ON Authentication
FOR EACH ROW
BEGIN
    IF NEW.UpdatedAt IS NOT NULL AND NEW.UpdatedAt < NEW.CreatedAt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'UpdatedAt cannot be before CreatedAt';
    END IF;
    IF NEW.DeletedAt IS NOT NULL AND (NEW.DeletedAt < NEW.CreatedAt OR NEW.DeletedAt < NEW.UpdatedAt) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DeletedAt cannot be before CreatedAt or UpdatedAt';
    END IF;
END;
//
DELIMITER ;

-- Create table brands
CREATE TABLE Brands (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    BrandName NVARCHAR(50),
    Image VARCHAR(40),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT
);

-- Create table ProductCategories
CREATE TABLE ProductCategories (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    BrandID INT,
    Name NVARCHAR(40),
    Price DOUBLE CHECK (Price >= 0),
    Image VARCHAR(40),
    Quantity INT CHECK (Quantity >= 0),
    Description NVARCHAR(255),
    Discount DOUBLE,
    DiscountFrom DATE,
    DiscountTo DATE,
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
    FOREIGN KEY (BrandID) REFERENCES Brands(ID),
    CONSTRAINT chk_discount_range CHECK (Discount >= 0 AND Discount <= 30),
    CONSTRAINT chk_discount_date CHECK (DiscountFrom < DiscountTo),
    CONSTRAINT chk_deleted_by CHECK (DeletedBy IS NULL OR IsDelete = TRUE)
);


-- Create table ProductCard
CREATE TABLE ProductCard (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ProductCategoriesID INT,
    Seri VARCHAR(20),
    Code VARCHAR(20),
    CreatedAt DATE,
    ExpiredDate DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
    FOREIGN KEY (ProductCategoriesID) REFERENCES ProductCategories(ID)
);

-- Create table Cart
CREATE TABLE Cart (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
    FOREIGN KEY (UserID) REFERENCES User(ID)
);

-- Create table CartItem
CREATE TABLE CartItem (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CartID INT NOT NULL,
    ProductCategoriesID INT NOT NULL,
    Quantity INT NOT NULL,
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
    FOREIGN KEY (CartID) REFERENCES Cart(ID),
    FOREIGN KEY (ProductCategoriesID) REFERENCES ProductCategories(ID),
    CONSTRAINT chk_quantity CHECK (Quantity > 0)
);

-- Create table Orders
CREATE TABLE Orders (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    TotalQuantity INT,
    TotalAmount DOUBLE,
    Status VARCHAR(15) CHECK (Status IN ('Paid', 'Unpaid')),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
    FOREIGN KEY (UserID) REFERENCES User(ID),
    CONSTRAINT chk_total_quantity CHECK (TotalQuantity > 0),
    CONSTRAINT chk_total_amount CHECK (TotalAmount >= 0)
);

-- Create table Orders
CREATE TABLE OrderDetails (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    ProductCategoriesID INT,
    ProductCategoriesName NVARCHAR(55),
    BrandName NVARCHAR(55),
    ProductCardID INT,
    ProductPrice DOUBLE,
    Discount DOUBLE,
    Amount Double,
    FOREIGN KEY (OrderID) REFERENCES Orders(ID),
    FOREIGN KEY (ProductCategoriesID) REFERENCES ProductCategories(ID),
    FOREIGN KEY (ProductCardID) REFERENCES ProductCard(ID),
    CONSTRAINT chk_product_price CHECK (ProductPrice >= 0),
    CONSTRAINT chk_discount CHECK (Discount >= 0)
);

-- Create table ReportProductCard
CREATE TABLE ReportProductCard ( 
	ID INT auto_increment primary key,
    UserID INT,
    OrderDetailID INT,
    ProductCardID INT,
    ProductCategoriesName NVARCHAR(55),-- THÊM ĐỂ LOG
    BrandName NVARCHAR(55),-- THÊM ĐỂ LOG
    Status NVARCHAR(55) CHECK (Status IN ('Replied & Compensated', 'Replied & No compensation','No repli & No compensation yet')),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
    AcceptBy INT,
	FOREIGN KEY (UserID) REFERENCES User(ID),
    FOREIGN KEY (ProductCardID) REFERENCES ProductCard(ID),
    FOREIGN KEY (OrderDetailID) REFERENCES OrderDetails(ID)
);

-- Create table Comment
CREATE TABLE Comment (
	ID INT auto_increment primary key,
    UserID INT,
    ProductCategoriesID INT,
	ProductCategoriesName NVARCHAR(55),-- THÊM ĐỂ LOG
    BrandName NVARCHAR(55), -- THÊM ĐỂ LOG
    Title NVARCHAR(55),
    Message NVARCHAR(300),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
	FOREIGN KEY (UserID) REFERENCES User(ID),
    FOREIGN KEY (ProductCategoriesID) REFERENCES ProductCategories(ID)
);


-- Create table Transaction
CREATE TABLE Transaction (
	ID INT auto_increment primary key,
    UserID INT,
    OrderID INT,
    Amount decimal,
    Type NVARCHAR(55) CHECK (Type IN ('Deposit', 'Payment')),
    PaymentCode VARCHAR(55),
    BankCode VARCHAR(55),
	Status NVARCHAR(55) CHECK (Status IN ('Pending','Failed', 'Success')),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
    FOREIGN KEY (UserID) REFERENCES User(ID),
	FOREIGN KEY (OrderID) REFERENCES Orders(ID)
);
-- ========================================================== Bổ sung


-- Create table CategoriesNews
CREATE TABLE CategoriesNews (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Title NVARCHAR(55) NOT NULL,          -- Tên của danh mục
    CreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    UpdatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    DeletedAt DATETIME,                   -- Thời gian xóa
    IsDelete BOOLEAN DEFAULT FALSE,       -- Trạng thái xóa
    DeletedBy INT                         -- Người xóa
);

-- Create table News
CREATE TABLE News (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Title NVARCHAR(55) NOT NULL,          -- Tiêu đề bài viết
    Description NVARCHAR(500),            -- Mô tả ngắn về bài viết
    ContentFirst NVARCHAR(500),               -- Nội dung bài viết
    ContentBody NVARCHAR(2000), 
    ContentEnd NVARCHAR(500), 
    CategoriesID INT NOT NULL,            -- ID danh mục bài viết
    Hotnews Boolean default False,        -- Hotnews
    Image VARCHAR(50),
    DescriptionImage VARCHAR(55),
    CreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    UpdatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    DeletedAt DATETIME,                   -- Thời gian xóa
    IsDelete BOOLEAN DEFAULT FALSE,       -- Trạng thái xóa
    DeletedBy INT,                        -- Người xóa
    FOREIGN KEY (CategoriesID) REFERENCES CategoriesNews(ID) -- Khóa ngoại tham chiếu đến bảng CategoriesNews
);

CREATE TABLE RegiterNotification (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(100), 
    Email NVARCHAR(100),            
    Message NVARCHAR(200),       
    CreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    UpdatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    DeletedAt DATETIME,                   
    IsDelete BOOLEAN DEFAULT FALSE,       
    DeletedBy INT                        
);


CREATE TABLE CommentNews (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(100), 
    Email NVARCHAR(100),            
    Message NVARCHAR(200), 
    NewsID INT,
    CreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    UpdatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    DeletedAt DATETIME,                   
    IsDelete BOOLEAN DEFAULT FALSE,       
    DeletedBy INT,
	FOREIGN KEY (NewsID) REFERENCES News(ID) 
);

CREATE TABLE Voucher (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Title NVARCHAR(100) NOT NULL,
    PurchaseOffer NVARCHAR(200),
    ApplyDescription NVARCHAR(500),
    Image VARCHAR(50),
    ApplyBrandID INT,
    ApplyProductID INT,
    FromDate DATE NOT NULL,
    ToDate DATE NOT NULL,
    PriceFrom DOUBLE NOT NULL,
    Discount DOUBLE NOT NULL,
    DiscountMax Double NOT NULL,
    Quantity int,
    CreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    UpdatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    DeletedAt DATETIME,                   
    IsDelete BOOLEAN DEFAULT FALSE,       
    DeletedBy INT
);


CREATE TABLE GetVoucher (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    VoucherID INT NOT NULL,
    UserID INT NOT NULL,
    CreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    UpdatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    DeletedAt DATETIME,                   
    IsDelete BOOLEAN DEFAULT FALSE,       
    DeletedBy INT,
    FOREIGN KEY (VoucherID) REFERENCES Voucher(ID),
    FOREIGN KEY (UserID) REFERENCES User(ID)
);


-- =============================================
CREATE TABLE FavoriteProduct (
	ID INT auto_increment primary key,
    UserID INT,
    ProductCategoriesID INT,
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
	FOREIGN KEY (UserID) REFERENCES User(ID),
    FOREIGN KEY (ProductCategoriesID) REFERENCES ProductCategories(ID)
);

CREATE TABLE RegisterMember (
	ID INT auto_increment primary key,
    UserID INT,
    Email1 varchar(50) NOT NULL,
    Email2 varchar(50) NOT NULL,
    Message nvarchar(100),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN,
    DeletedBy INT,
	FOREIGN KEY (UserID) REFERENCES User(ID)
);

-- =====Sơn đỗ
CREATE TABLE NameAgreementPolicy (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(55),
    Status NVARCHAR(55) CHECK (Status IN ('Active','Inactive')),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN DEFAULT 0
);

CREATE TABLE AgreementPolicy (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDNameAgreementPolicy int,
    Message NVARCHAR(3000),
    CreatedAt DATE,
    UpdatedAt DATE,
    DeletedAt DATE,
    IsDelete BOOLEAN DEFAULT 0,
    FOREIGN KEY (IDNameAgreementPolicy) REFERENCES NameAgreementPolicy(ID)
);



-- Insert database hieu:
CREATE TABLE withdrawal_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT(11) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    bank_name VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(ID)
);

ALTER TABLE withdrawal_requests
ADD COLUMN transaction_details TEXT;

























-- ==========================================================
-- TRIGGER;

-- Trigger to update Quantity on insert into ProductCard
DELIMITER //

-- Trigger for AFTER INSERT on ProductCard
CREATE TRIGGER ProductCard_AfterInsert
AFTER INSERT ON ProductCard
FOR EACH ROW
BEGIN
    DECLARE brandIsDeleted BOOLEAN;
    
    SELECT b.IsDelete INTO brandIsDeleted
    FROM ProductCategories pc
    JOIN brands b ON pc.BrandID = b.ID
    WHERE pc.ID = NEW.ProductCategoriesID;
    
    IF NEW.IsDelete = FALSE AND brandIsDeleted = FALSE THEN
        UPDATE ProductCategories
        SET Quantity = Quantity + 1
        WHERE ID = NEW.ProductCategoriesID;
    END IF;
END //

-- Trigger for AFTER DELETE on ProductCard
CREATE TRIGGER ProductCard_AfterDelete
AFTER DELETE ON ProductCard
FOR EACH ROW
BEGIN
    DECLARE brandIsDeleted BOOLEAN;
    
    SELECT b.IsDelete INTO brandIsDeleted
    FROM ProductCategories pc
    JOIN brands b ON pc.BrandID = b.ID
    WHERE pc.ID = OLD.ProductCategoriesID;
    
    IF OLD.IsDelete = FALSE AND brandIsDeleted = FALSE THEN
        UPDATE ProductCategories
        SET Quantity = Quantity - 1
        WHERE ID = OLD.ProductCategoriesID;
    END IF;
END //

-- Trigger for AFTER UPDATE on ProductCard
-- CREATE TRIGGER ProductCard_AfterUpdate
-- AFTER UPDATE ON ProductCard
-- FOR EACH ROW
-- BEGIN
--     DECLARE brandIsDeleted BOOLEAN;
--     
--     SELECT b.IsDelete INTO brandIsDeleted
--     FROM ProductCategories pc
--     JOIN brands b ON pc.BrandID = b.ID
--     WHERE pc.ID = NEW.ProductCategoriesID;
--     
--     IF OLD.IsDelete != NEW.IsDelete AND brandIsDeleted = FALSE THEN
--         IF OLD.IsDelete = TRUE AND NEW.IsDelete = FALSE THEN
--             UPDATE ProductCategories
--             SET Quantity = Quantity + 1
--             WHERE ID = NEW.ProductCategoriesID;
--         ELSEIF OLD.IsDelete = FALSE AND NEW.IsDelete = TRUE THEN
--             UPDATE ProductCategories
--             SET Quantity = Quantity - 1
--             WHERE ID = NEW.ProductCategoriesID;
--         END IF;
--     END IF;
-- END //
CREATE TRIGGER ProductCard_AfterUpdate
AFTER UPDATE ON ProductCard
FOR EACH ROW
BEGIN
    DECLARE brandIsDeleted BOOLEAN;
    
    SELECT b.IsDelete INTO brandIsDeleted
    FROM ProductCategories pc
    JOIN brands b ON pc.BrandID = b.ID
    WHERE pc.ID = NEW.ProductCategoriesID;
    
    IF OLD.IsDelete != NEW.IsDelete AND brandIsDeleted = FALSE THEN
        IF OLD.IsDelete = TRUE AND NEW.IsDelete = FALSE THEN
            UPDATE ProductCategories
            SET Quantity = Quantity + 1
            WHERE ID = NEW.ProductCategoriesID;
        ELSEIF OLD.IsDelete = FALSE AND NEW.IsDelete = TRUE THEN
            UPDATE ProductCategories
            SET Quantity = Quantity - 1
            WHERE ID = NEW.ProductCategoriesID;
        END IF;
    END IF;

    -- Reset discount if the expiration date is over
    IF NEW.ExpiredDate < CURDATE() THEN
    -- Set IsDelete to TRUE and DeletedAt to current date in ProductCard
        UPDATE ProductCard
        SET IsDelete = TRUE, DeletedAt = NOW()
        WHERE ID = NEW.ID;
        UPDATE ProductCategories
        SET Discount = 0, DiscountFrom = NULL, DiscountTo = NULL
        WHERE ID = NEW.ProductCategoriesID;
    END IF;
END //

DELIMITER ;

-- Trigger for updating brands
DELIMITER $$

CREATE TRIGGER Brand_AfterUpdate
AFTER UPDATE ON brands
FOR EACH ROW
BEGIN
    IF NEW.IsDelete = TRUE AND OLD.IsDelete = FALSE THEN
        -- Update the ProductCard entries associated with the ProductCategories of the brand
        UPDATE ProductCard p
        JOIN ProductCategories c ON p.ProductCategoriesID = c.ID
        SET p.DeletedAt = NOW(), p.IsDelete = TRUE, p.DeletedBy = NEW.DeletedBy
        WHERE c.BrandID = NEW.ID;

        -- Update the ProductCategories associated with the brand
        UPDATE ProductCategories
        SET DeletedAt = NOW(), IsDelete = TRUE, DeletedBy = NEW.DeletedBy
        WHERE BrandID = NEW.ID;
    END IF;
END $$

DELIMITER ;
