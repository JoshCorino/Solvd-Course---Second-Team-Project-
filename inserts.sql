delete from orders_have_goods;
ALTER TABLE orders_have_goods auto_increment = 1;
delete from allowed_transports;
ALTER TABLE allowed_transports auto_increment = 1;
delete from companies_have_transports;
ALTER TABLE companies_have_transports auto_increment = 1;
delete from transport_types;
ALTER TABLE transport_types auto_increment = 1;
delete from warehouses_have_goods;
ALTER TABLE warehouses_have_goods auto_increment = 1;
delete from orders;
ALTER TABLE orders auto_increment = 1;
delete from companies;
ALTER TABLE companies auto_increment = 1;
delete from goods;
ALTER TABLE goods auto_increment = 1;
delete from warehouses;
ALTER TABLE warehouses auto_increment = 1;

insert into companies(name)
values("Applebee's");

insert into warehouses (wh_name)
values ("crackers");
insert into warehouses (wh_name)
values ("furniture");

insert into orders (companies_id, date)
values (1,"2000-02-02");
insert into orders (companies_id, date)
values (1,"2000-02-02");

insert into goods (price, volume, good_name)
values (2.2, 2.0, "cookie");
insert into goods (price, volume, good_name)
values (2.2, 6.0, "cake");
insert into goods (price, volume, good_name)
values (2.2, 3.0, "bread");

insert into goods (price, volume, good_name)
values (2.2, 3.0, "chair");
insert into goods (price, volume, good_name)
values (2.2, 20.0, "mattress");
insert into goods (price, volume, good_name)
values (2.2, 13.0, "table");

insert into orders_have_goods (goods_id,orders_id,quantity)
values (1,1,2);
insert into orders_have_goods (goods_id,orders_id,quantity)
values (3,1,1);

insert into orders_have_goods (goods_id,orders_id,quantity)
values (5,2,3);

insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (1,1,30);
insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (2,1,30);
insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (3,1,30);

insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (4,2,30);
insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (5,2,30);
insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (6,2,30);

insert into transport_types (transport_name)
values ("car");
insert into transport_types (transport_name)
values ("plane");

insert into companies_have_transports (companies_id, transports_id,capacity)
values (1,1,10);
insert into companies_have_transports (companies_id, transports_id,capacity)
values (1,2,40);

insert into allowed_transports (warehouses_id, transport_types_id)
values (1,2);
insert into allowed_transports (warehouses_id, transport_types_id)
values (1,1);

select * from orders;
select * from orders_have_goods;
select * from allowed_transports;
select * from companies;
select * from companies_have_transports;
select * from goods;
select * from transport_types;
select * from warehouses;
select * from warehouses_have_goods;
