create schema if not exists sellers;
create sequence if not exists seller_seq start with 1 increment by 50;
create table if not exists sellers.Seller(
  id bigint DEFAULT nextval('seller_seq') not null,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  email varchar(50) unique not null,
  created_at timestamp,
  last_modified_at timestamp
);