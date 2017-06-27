create database if NOT exists servermail;


create user 'usermail'@'localhost' identified by 'mailpassword';

create user 'usermail'@'%' identified by 'mailpassword';

GRANT ALL ON  servermail.* to 'usermail'@'localhost';
GRANT ALL ON  servermail.* to 'usermail'@'%';
GRANT ALL ON servermail.* TO 'usermail'@'127.0.0.1' IDENTIFIED BY 'mailpassword';

FLUSH PRIVILEGES;


-- service user
GRANT ALL ON servermail.* TO 'serviceuser'@'127.0.0.1' IDENTIFIED BY 'servicepassword';
FLUSH PRIVILEGES;

GRANT ALL ON servermail.* TO 'serviceuser'@'localhost' IDENTIFIED BY 'servicepassword';
FLUSH PRIVILEGES;


USE servermail;


CREATE TABLE `virtual_domains` (
`id`  INT NOT NULL AUTO_INCREMENT,
`vd_name` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `virtual_users` (
`id` INT NOT NULL AUTO_INCREMENT,
`domain_id` INT NOT NULL,
`vu_password` VARCHAR(106) NOT NULL,
`email` VARCHAR(120) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `email` (`email`),
FOREIGN KEY (domain_id) REFERENCES virtual_domains(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `virtual_aliases` (
`id` INT NOT NULL AUTO_INCREMENT,
`domain_id` INT NOT NULL,
`source` varchar(100) NOT NULL,
`destination` varchar(100) NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (domain_id) REFERENCES virtual_domains(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `servermail`.`virtual_domains`
(`id` ,`vd_name`)
VALUES
('1', 'example.com'),
('2', 'hostname.example.com');


INSERT INTO `servermail`.`virtual_users`
(`id`, `domain_id`, `vu_password` , `email`)
VALUES
('1', '1', ENCRYPT('firstpassword', CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))), 'email1@example.com'),
('2', '1', ENCRYPT('secondpassword', CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))), 'email2@example.com');


INSERT INTO `servermail`.`virtual_aliases`
(`id`, `domain_id`, `source`, `destination`)
VALUES
('1', '1', 'alias@example.com', 'email1@example.com');