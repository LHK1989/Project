drop database if exists project2;
create database project2;
use project2;

create table user(
	usernum int unsigned auto_increment,
	name varchar(10),
    id varchar(10),
	phonenumber varchar(13),
	address varchar(50),
    password varchar(25) not null,
    role varchar(10) default 'customer',
    car varchar(20),
	primary key(usernum)
);


create table brand(
	brandid int unsigned auto_increment primary key,
    brandname varchar(20) not null unique
);

create table size(
	sizeid int unsigned auto_increment primary key,
    size varchar(15)
);

create table tire(
	tirenum int unsigned auto_increment,
	brandid_fk int unsigned,
    size_fk int unsigned,
    tirename varchar(35),
    price int,
    stock int not null,
    primary key(tirenum),
    foreign key (brandid_fk) references brand(brandid),
    foreign key (size_fk) references size(sizeid)
);

create table service(
	serviceid int unsigned auto_increment,
    servicename varchar(20) not null,
    price int not null,
    primary key(serviceid)
);


create table orders(
	ordernum int unsigned auto_increment primary key,
	orderusernum int unsigned null,
    orderdate datetime not null,
	name varchar(10),
    phone varchar(13),
    quantity int null,
    servicetype_fk int unsigned,
    tire_fk int unsigned,
    foreign key (tire_fk) references tire(tirenum),
    foreign key (servicetype_fk) references service(serviceid),
    foreign key (orderusernum) references user(usernum)
);

create table board(
	boardid int unsigned auto_increment primary key,
    writeuser int unsigned,
    title varchar(20) not null,
    content text,
    board_date datetime default now(),
    foreign key(writeuser) references user(usernum)
);


insert into brand(brandname) values ('michelin');
insert into brand(brandname) values ('firelli');
insert into brand(brandname) values ('hankook');
insert into brand(brandname) values ('nexen');
insert into brand(brandname) values ('continental');
insert into brand(brandname) values ('kumho');

insert into size(size) values ('17inch');
insert into size(size) values ('18inch');
insert into size(size) values ('19inch');
insert into size(size) values ('20inch');
insert into size(size) values ('21inch');

insert into tire(brandid_fk, size_fk, tirename, price, stock) values (1 , 3, 'cross 235/35r19', 260000, 20 );
insert into tire(brandid_fk, size_fk, tirename, price, stock) values (1 , 4, 'tour 245/50r20', 210000, 20 );
insert into tire(brandid_fk, size_fk, tirename, price, stock) values (1 , 2, 'tour 245/50r18', 220000, 20 );

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(1, 1, 'Michelin Pilot Sport 17', 250000, 20),
(1, 2, 'Michelin Pilot Sport 18', 270000, 20),
(1, 3, 'Michelin Pilot Sport 19', 290000, 20),
(1, 4, 'Michelin Pilot Sport 20', 310000, 20),
(1, 5, 'Michelin Pilot Sport 21', 330000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(2, 1, 'Pirelli P Zero 17', 260000, 20),
(2, 2, 'Pirelli P Zero 18', 280000, 20),
(2, 3, 'Pirelli P Zero 19', 300000, 20),
(2, 4, 'Pirelli P Zero 20', 320000, 20),
(2, 5, 'Pirelli P Zero 21', 340000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(3, 1, 'Hankook Ventus 17', 220000, 20),
(3, 2, 'Hankook Ventus 18', 240000, 20),
(3, 3, 'Hankook Ventus 19', 260000, 20),
(3, 4, 'Hankook Ventus 20', 280000, 20),
(3, 5, 'Hankook Ventus 21', 300000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(4, 1, 'Nexen N’FERA 17', 210000, 20),
(4, 2, 'Nexen N’FERA 18', 230000, 20),
(4, 3, 'Nexen N’FERA 19', 250000, 20),
(4, 4, 'Nexen N’FERA 20', 270000, 20),
(4, 5, 'Nexen N’FERA 21', 290000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(5, 1, 'Continental ContiSport 17', 270000, 20),
(5, 2, 'Continental ContiSport 18', 290000, 20),
(5, 3, 'Continental ContiSport 19', 310000, 20),
(5, 4, 'Continental ContiSport 20', 330000, 20),
(5, 5, 'Continental ContiSport 21', 350000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(6, 1, 'Kumho Ecsta 17', 200000, 20),
(6, 2, 'Kumho Ecsta 18', 220000, 20),
(6, 3, 'Kumho Ecsta 19', 240000, 20),
(6, 4, 'Kumho Ecsta 20', 260000, 20),
(6, 5, 'Kumho Ecsta 21', 280000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(1, 1, 'Michelin CrossClimate 17', 255000, 20),
(1, 2, 'Michelin CrossClimate 18', 275000, 20),
(1, 3, 'Michelin CrossClimate 19', 295000, 20),
(1, 4, 'Michelin CrossClimate 20', 315000, 20),
(1, 5, 'Michelin CrossClimate 21', 335000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(2, 1, 'Pirelli Winter Sottozero 17', 265000, 20),
(2, 2, 'Pirelli Winter Sottozero 18', 285000, 20),
(2, 3, 'Pirelli Winter Sottozero 19', 305000, 20),
(2, 4, 'Pirelli Winter Sottozero 20', 325000, 20),
(2, 5, 'Pirelli Winter Sottozero 21', 345000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(3, 1, 'Hankook Kinergy 17', 225000, 20),
(3, 2, 'Hankook Kinergy 18', 245000, 20),
(3, 3, 'Hankook Kinergy 19', 265000, 20),
(3, 4, 'Hankook Kinergy 20', 285000, 20),
(3, 5, 'Hankook Kinergy 21', 305000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(4, 1, 'Nexen Roadian 17', 215000, 20),
(4, 2, 'Nexen Roadian 18', 235000, 20),
(4, 3, 'Nexen Roadian 19', 255000, 20),
(4, 4, 'Nexen Roadian 20', 275000, 20),
(4, 5, 'Nexen Roadian 21', 295000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(5, 1, 'Continental EcoContact 17', 275000, 20),
(5, 2, 'Continental EcoContact 18', 295000, 20),
(5, 3, 'Continental EcoContact 19', 315000, 20),
(5, 4, 'Continental EcoContact 20', 335000, 20),
(5, 5, 'Continental EcoContact 21', 355000, 20);

insert into tire(brandid_fk, size_fk, tirename, price, stock) values
(6, 1, 'Kumho Solus 17', 205000, 20),
(6, 2, 'Kumho Solus 18', 225000, 20),
(6, 3, 'Kumho Solus 19', 245000, 20),
(6, 4, 'Kumho Solus 20', 265000, 20),
(6, 5, 'Kumho Solus 21', 285000, 20);

insert into service(servicename, price) values ('tire change',30000);
insert into service(servicename, price) values ('tire repair',15000);

insert into user(name, id, phonenumber, address, password,role, car) values ('어드민','admin','000-0000-0000','서울시 종로구 가회동','1234','admin','k7');

insert into orders(orderdate,name,phone) values (current_timestamp(),'김철수','123-1234-1234');

select * from orders left outer join user on user.usernum = orders.orderusernum order by orderdate asc;