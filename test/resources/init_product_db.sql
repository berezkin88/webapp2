drop table PRODUCT;

create table PRODUCT (
	id smallserial not null primary key,
	title varchar(255) not null,
	price real not null,
	description varchar
);

insert into PRODUCT(title, price, description) values ('product1', 10.1, 'about product1');
insert into PRODUCT(title, price, description) values ('product2', 20.2, 'about product2');
insert into PRODUCT(title, price, description) values ('product3', 30.3, 'about product3');