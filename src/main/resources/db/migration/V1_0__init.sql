create table customer (
    id int auto_increment primary key,
    first_name varchar(40),
    last_name varchar(40) not null,
    city varchar(40),
    country varchar(40),
    phone varchar(20)
);
create index on customer (first_name);
create index on customer (last_name);

create table supplier (
    id int auto_increment primary key,
    company_name varchar(40) not null,
    contact_name varchar(50),
    contact_title varchar(40),
    city varchar(40),
    country varchar(40),
    phone varchar(30),
    fax varchar(30)
);
create index on supplier (company_name);
create index on supplier (country);

create table product (
    id int auto_increment primary key,
    product_name varchar(50) not null,
    supplier_id int not null,
    unit_price decimal(12, 2) not null,
    package varchar(30),
    is_discontinued boolean not null,
    foreign key (supplier_id) references supplier (id)
);
create index on product (supplier_id);
create index on product (product_name);

create table customer_order (
    id int auto_increment primary key,
    order_date timestamp not null,
    order_number varchar(10) not null,
    customer_id int not null,
    total_amount decimal(12, 2) not null,
    unique (order_number),
    foreign key (customer_id) references customer (id)
);
create index on customer_order (customer_id);
create index on customer_order (order_date);

create table order_item (
    id int auto_increment primary key,
    order_id int not null,
    product_id int not null,
    unit_price decimal (12, 2) not null,
    quantity int not null,
    unique (order_id, product_id),
    foreign key (order_id) references customer_order (id),
    foreign key (product_id) references product (id)
);
create index on order_item (order_id);
create index on order_item (product_id);
