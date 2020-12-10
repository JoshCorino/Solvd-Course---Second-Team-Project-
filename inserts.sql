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
values("arcor");

insert into warehouses (wh_name)
values ("masitas");
insert into warehouses (wh_name)
values ("muebles");

insert into orders (companies_id, date)
values (1,"2000-02-02");
insert into orders (companies_id, date)
values (1,"2000-02-02");

insert into goods (price, volume, good_name)
values (2.2, 5.0, "oreo");
insert into goods (price, volume, good_name)
values (2.2, 10.0, "vainilla");

insert into goods (price, volume, good_name)
values (2.2, 5.0, "sila");
insert into goods (price, volume, good_name)
values (2.2, 5.0, "mesa");
insert into goods (price, volume, good_name)
values (2.2, 5.0, "cama");

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
values (3,2,30);
insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (4,2,30);
insert into warehouses_have_goods(goods_id,warehouses_id,quantity)
values (5,2,30);

insert into transport_types (transport_name)
values ("auto");
insert into transport_types (transport_name)
values ("avion");

insert into companies_have_transports (companies_id, transports_id,capacity)
values (1,1,40);
insert into companies_have_transports (companies_id, transports_id,capacity)
values (1,1,40);
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
