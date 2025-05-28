CREATE DATABASE gestao_de_games;

USE gestao_de_games;

CREATE TABLE IF NOT EXISTS `usuarios` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`nome` varchar(100) NOT NULL,
	`email` varchar(60) NOT NULL,
	`senha` varchar(12) NOT NULL,
	`id_tipo_usuario` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `games` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`titulo` varchar(150) NOT NULL,
	`nome_imagem` varchar(255) NOT NULL,
	`id_genero` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `nota_games` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`nota` int NOT NULL,
	`id_game` int NOT NULL,
	`id_usuario` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tipo_usuario` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`titulo` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `genero` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`titulo` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `usuarios` ADD CONSTRAINT `usuarios_fk4` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario`(`id`);
ALTER TABLE `games` ADD CONSTRAINT `games_fk3` FOREIGN KEY (`id_genero`) REFERENCES `genero`(`id`);
ALTER TABLE `nota_games` ADD CONSTRAINT `nota_games_fk2` FOREIGN KEY (`id_game`) REFERENCES `games`(`id`) ON DELETE CASCADE;

ALTER TABLE `nota_games` ADD CONSTRAINT `nota_games_fk3` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios`(`id`) ON DELETE CASCADE;

INSERT INTO tipo_usuario (titulo) VALUES
('Administrador'),
('Avaliador');
