create database PhoneManagement_3
go
use PhoneManagement_3
go

create table tblRole (
	roleID char (10) primary key,
	roleName nvarchar(50)
)
go

create table tblUsers (
	userID char(10) primary key,
	fullName nvarchar(50),
	address nvarchar(50),
	phone nvarchar(10),
	password nvarchar,
	roleID char(10) foreign key references tblRole(roleID)
)
go

create table tblCatagory (
	catagoryID char (10) primary key,
	catagoryName nvarchar(50)
)
go

create table tblOrders (
 orderID char(10) primary key,
 orderDate Date,
 totalPrice float,
 userID char(10) foreign key references tblUsers(userID)
)
go

create table tblProducts (
 productID char(10) primary key,
 productName nvarchar(50),
 price float(20),
 quantity int,
 catagoryID char(10) foreign key references tblCatagory(catagoryID)
)
go

create table tblOrderDetail (
 orderDetailId char(10) primary key(productID,orderID),
 price float,
 quantity int,
 orderID char(10) foreign key references tblOrders(orderID),
 productID char(10) foreign key references tblProducts(productID),
)
go

ALTER TABLE tblProducts
ALTER COLUMN price float(20);


insert into tblCatagory values ('SS','SamSung')
insert into tblCatagory values ('OP','Oppo')
insert into tblCatagory values ('VV','ViVo')
insert into tblCatagory values ('AP','Apple')

Select productID,productName,price,quantity,catagoryID
from tblProducts
where productName like '%%' and catagoryID like '%OP%'


insert into tblRole values ('AD','Admin')
insert into tblRole values ('US','User')

insert into tblUsers(userID,fullName,address,phone,password,roleID) values ('U004','Le Duc Minh', 'Q9', '0122545865','1','US')

insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('SS01', 'Note 9', '10000', '10', 'SS')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('SS02','Note 10','19000','8','SS')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('SS03','S20','20000','9','SS')

insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('AP01','Iphone 8','19000','3','AP')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('AP02','Iphone X','30000','5','AP')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('AP03','Iphone 12 Pro Max','40000','10','AP')

insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('OP01','OPPO Reno5','8690','4','OP')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('OP02','OPPO A15','3490','9','OP')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('OP03','OPPO Find X2','19990','4','OP')

insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('VV01','Vivo Y51','5590','4','VV')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('VV02','Vivo V20','7790','9','VV')
insert into tblProducts(productID,productName,price,quantity,catagoryID) values ('VV03','Vivo Y50','5090','4','VV')


select Count(orderID) as NoofOrder
from tblOrders

select Count(orderDetailId) as NoofOrder
from tblOrderDetail


insert into tblO values ('AD','Admin')

INSERT INTO tblOrders(orderID,orderDate,totalPrice,userID) values('O3','12/23/2012','19000','U002')

INSERT INTO tblOrderDetail(orderDetailId,price,quantity,orderID,productID) values('D1','50000','4','O1','VV01')

SELECT productName
FROM tblProducts
Where productID = 'SS01' And quantity >= 