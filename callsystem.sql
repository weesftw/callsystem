drop database callSystem;

create database `callSystem` default charset utf8mb4 default collate utf8mb4_general_ci;

use `callSystem`;

/* create table if not exists `country`
(
	`id` int unsigned not null auto_increment,
	`name` varchar(120) unique not null,
	`code` varchar(6) not null,
    `phoneCode` smallint unsigned not null,

	constraint primary key(`id`)
)Engine=InnoDB;

/* insert into `country` (`name`, `code`, `phoneCode`) value ('Brazil', 'BR', 55);

create table if not exists `uf`
(
	`id` int unsigned not null auto_increment,
	`name` varchar(120) unique not null,
	`ddd` varchar(9) not null,
	`country` int unsigned not null,

	constraint primary key(`id`),
	constraint foreign key(`country`) references `country`(`id`)
)Engine=InnoDB;

/* insert into `uf` (`name`, `ddd`, `country`) value ('São Paulo', '011', 1);

create table if not exists `city`
(
	`id` int unsigned not null auto_increment,
	`name` varchar(120) unique not null,
	`uf` int unsigned not null,

	constraint primary key(`id`),
	constraint foreign key(`uf`) references `uf`(`id`)
)Engine=InnoDB;

/* insert into `city` (`name`, `uf`) value ('Guarulhos', 1); 

create table if not exists `neighborhood`
(
	`id` int unsigned not null auto_increment,
	`name` varchar(120) not null,
	`city` int unsigned not null,

	constraint primary key(`id`),
	constraint foreign key(`city`) references `city`(`id`)
)Engine=InnoDB;

/* insert into `neighborhood` (`name`, `city`) value ('Continental III', 1);

create table if not exists `zipCode`
(
	`id` varchar(10) not null,
	`name` varchar(120) not null,
	`neighborhood` int unsigned not null,

	constraint primary key(`id`),
	constraint foreign key(`neighborhood`) references `neighborhood`(`id`)
)Engine=InnoDB; */

/* insert into `zipCode` (`id`, `name`, `neighborhood`) value ('07085410', 'Rua João Carlos Rodrigues', 1); */

create table if not exists `department`
(
	`id` tinyint unsigned auto_increment,
    `name` varchar(50) not null,
    `privilege` bit not null default 0,
    
    constraint primary key(`id`)
)Engine=InnoDB;

insert into `department` (`name`, `privilege`) value ('ADMINISTRATOR',  1); 
insert into `department` (`name`, `privilege`) value ('TI',  1); 
insert into `department` (`name`, `privilege`) value ('RH',  0); 
insert into `department` (`name`, `privilege`) value ('ATTENDANCE',  0); 

create table if not exists `people`
(
	`cpf` varchar(14) not null,
	`firstName` varchar(60) not null,
	`lastName` varchar(60) not null,
	`phoneNumber` varchar(15) not null,
	`email` varchar(120) not null,
    `dateBorn` char(10) not null,
    `gender` enum('FEMALE', 'MALE'),
	`zipCode` varchar(10) not null,
    `photo` blob,

	constraint primary key(`cpf`)
)Engine=InnoDB;

 insert into `people` value ('1', 'Admin', '', '', 'admin@admin.com', '', 'MALE', '', null); 

create table if not exists `user`
(
	`cpf` varchar(14) not null,
	`username` varchar(20) unique not null,
	`passwd` varchar(120) not null,
    `department` tinyint unsigned not null,
	
	primary key(`cpf`),
	constraint foreign key(`cpf`) references `people`(`cpf`),
	constraint foreign key(`department`) references `department`(`id`)
)Engine=InnoDB;

insert into `user` (`cpf`, `username`, `passwd`, `department`) value ('1', 'admin', 'admin', 1);

create table if not exists `company`
(
	`cnpj` varchar(18) not null,
	`name` varchar(120) not null,
	`owner` varchar(14) not null,
	`zipCode` varchar(10) not null,

	constraint primary key(`cnpj`),
	constraint foreign key(`owner`) references `people`(`cpf`)
)Engine=InnoDB;

insert into `company` value ('2', 'Teeeee', '1', '07085410');

create table if not exists `category`
(
	`id` tinyint unsigned not null auto_increment,
	`name` varchar(120) not null,
     
    constraint primary key(`id`) 
)Engine=InnoDB;

insert into `category` (`name`) value ('CANCELAMENT');
insert into `category` (`name`) value ('FINANCIAL');
insert into `category` (`name`) value ('SUPPORT');
insert into `category` (`name`) value ('UPGRADE');

create table if not exists `product`
(
	`id` tinyint unsigned not null auto_increment,
	`name` varchar(120),
    
    constraint primary key(`id`)
)Engine=InnoDB;

insert into `product` (`name`) value ('MOVEL');
insert into `product` (`name`) value ('INTERNET');
insert into `product` (`name`) value ('TV');

create table if not exists `ticket`
(
	`id` int(10) unsigned zerofill auto_increment,
	`title` varchar(120) not null,
	`client` varchar(14) not null,
    `company` varchar(18) not null,
    `user` varchar(14) not null,
	`time` timestamp default current_timestamp,
    `category` tinyint unsigned not null,
    `product` tinyint unsigned not null,
	`description` text not null default '',
    `solution` text,
    `priority` boolean not null default false,
	`status` enum('OPEN', 'PENDENT', 'CLOSED') default 'OPEN',

	constraint primary key(`id`),
    constraint foreign key(`user`) references `user`(`cpf`),
	constraint foreign key(`client`) references `people`(`cpf`),
    constraint foreign key(`company`) references `company`(`cnpj`),
    constraint foreign key(`category`) references `category`(`id`),
    constraint foreign key(`product`) references `product`(`id`)
)Engine=InnoDB;