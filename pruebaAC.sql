create database pruebaAC;
use pruebaAC;

create table usuario(
id_usuario int auto_increment primary key,
nombre_usuario varchar (50),
contraseña varchar(50)
);
insert into usuario(nombre_usuario,contraseña) values ("Ariel","Ariel123"),("Mishell","Mishell123");
select * from usuario;

create table cliente(
id_cliente int auto_increment primary key,
tipo_cuenta varchar(50), 
saldo_inicial decimal(10.2),
nombre varchar(30),
apellido varchar(30),
direccion varchar(30)

);
insert into cliente(tipo_cuenta,saldo_inicial,nombre,apellido,direccion) values
("Ahorro",200.87,"Fausto","Catucuamba","Colibri"),("Corriente",300.87,"Jimena","Melba","Selvalegre");
select * from cliente;