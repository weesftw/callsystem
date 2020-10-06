drop database callSystem;

create database `callSystem` default charset utf8mb4 default collate utf8mb4_general_ci;

use `callSystem`;

create table if not exists `department`
(
	`id` tinyint unsigned auto_increment,
    `name` varchar(50) not null,
    `privilege` bit not null default 0,
    
    constraint primary key(`id`)
)Engine=InnoDB;

insert into `department` (`name`, `privilege`) value ('Administrator',  1);
insert into `department` (`name`, `privilege`) value ('TI',  1);
insert into `department` (`name`, `privilege`) value ('RH',  0);
insert into `department` (`name`, `privilege`) value ('Attendance',  0);

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

insert into `user` (`cpf`, `username`, `passwd`, `department`) value ('1', 'admin', 'admin', 4);

create table if not exists `company`
(
	`cnpj` varchar(18) not null,
	`owner` varchar(14) not null,
	`name` varchar(120) not null,
	`zipCode` varchar(10) not null,

	constraint primary key(`cnpj`, `owner`),
	constraint foreign key(`owner`) references `people`(`cpf`)
)Engine=InnoDB;

create table if not exists `category`
(
	`id` tinyint unsigned not null auto_increment,
	`name` varchar(120) not null,
     
    constraint primary key(`id`) 
)Engine=InnoDB;

insert into `category` (`name`) value ('Cancelament');
insert into `category` (`name`) value ('Financial');
insert into `category` (`name`) value ('Support');
insert into `category` (`name`) value ('Upgrade');

create table if not exists `provider`
(
	`id` tinyint unsigned not null auto_increment,
	`name` varchar(120) not null,

	constraint primary key(`id`)
)Engine=InnoDB;

create table if not exists `product`
(
	`id` tinyint unsigned not null auto_increment,
	`name` varchar(120),
	`provider` tinyint unsigned not null,
    
    constraint primary key(`id`),
	constraint foreign key(`provider`) references `provider`(`id`)
)Engine=InnoDB;

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
	`status` enum('Open', 'Pendent', 'Closed') default 'Open',

	constraint primary key(`id`),
    constraint foreign key(`user`) references `user`(`cpf`),
	constraint foreign key(`client`) references `people`(`cpf`),
    constraint foreign key(`company`) references `company`(`cnpj`),
    constraint foreign key(`category`) references `category`(`id`),
    constraint foreign key(`product`) references `product`(`id`)
)Engine=InnoDB;

create table if not exists `sell`
(
	`id` int unsigned auto_increment,
	`product` tinyint unsigned not null,
	`cpf` varchar(14),
	`cnpj` varchar(18),
	`date` timestamp default current_timestamp,
	`observation` varchar(256),

	constraint primary key(`id`),
	constraint foreign key(`cpf`) references `people`(`cpf`),
	constraint foreign key(`cnpj`) references `company`(`cnpj`)
)Engine=InnoDB;