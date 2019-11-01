drop schema if exists abm;

create schema abm;

use abm;

create table productos(  
       id bigint not null auto_increment,
       presentacion varchar(255) not null default '',
       cantidad int(11) not null default 0,
       precio float not null default 0.0,
       descripcion varchar(255),
       primary key (id)
 );

insert into productos (presentacion,cantidad,precio,descripcion) values (?,?,?,?),
								(?,?,?,?),
								(?,?,?,?); 


