CREATE SCHEMA `cac_integrating_proyect_2022` ;

CREATE TABLE `cac_integrating_proyect_2022`.`usuarios` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idusuario`));

CREATE TABLE `cac_integrating_proyect_2022`.`provincias` (
  `idprovincia` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idprovincia`));

CREATE TABLE `cac_integrating_proyect_2022`.`localidades` (
  `idlocalidad` INT NOT NULL AUTO_INCREMENT,
  `nombre_localidad` VARCHAR(45) NOT NULL,
  `id_provincia_localidad` INT NULL,
  PRIMARY KEY (`idlocalidad`));

CREATE TABLE `cac_integrating_proyect_2022`.`pedidos` (
  `idpedido` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `lugar_entrega` VARCHAR(80) NOT NULL,
  `localidad_id` INT NOT NULL,
  `provincia_id` INT NOT NULL,
  `codigo_postal` VARCHAR(45) NOT NULL,
  `forma_de_pago` INT NOT NULL,
  `tarjeta_titular` VARCHAR(45) NOT NULL,
  `tarjeta_numero` INT NOT NULL,
  `tarjeta_vencimiento` VARCHAR(10) NOT NULL,
  `tarjeta_clave` INT NOT NULL,
  PRIMARY KEY (`idpedido`));

/* Modificar tarjeta_numero por BIGINT ya que INT desborda el rango*/
ALTER TABLE cac_integrating_proyect_2022.pedidos MODIFY tarjeta_numero BIGINT;
/*ALTER TABLE pedidos CHANGE `tajeta_vencimiento` `tarjeta_vencimiento` VARCHAR(10);*/

CREATE TABLE `cac_integrating_proyect_2022`.`metodos_de_pago` (
  `idmetodo_de_pago` INT NOT NULL AUTO_INCREMENT,
  `tipo_de_metodo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idmetodo_de_pago`));

/*ALTER TABLE metodos_de_pago CHANGE `idmetodos_de_pago` `idmetodo_de_pago` INT;*/

-----------------
/*RELACION DESDE QUERY DE LOCALIDAD CON PROVINCIA*/
-----------
ALTER TABLE cac_integrating_proyect_2022.localidades 
ADD CONSTRAINT `localidad_provincia` FOREIGN KEY (`id_provincia_localidad`) REFERENCES `provincias` (`idprovincia`) ON DELETE CASCADE ON UPDATE CASCADE;

/* Borrar la relación*/
/*ALTER TABLE cac_integrating_proyect_2022.localidades DROP FOREIGN KEY  `localidad_provincia`;*/
---------------
/* Rleacion de pedido con localidad y provincia */
ALTER TABLE cac_integrating_proyect_2022.pedidos 
ADD CONSTRAINT `pedido_localidad` FOREIGN KEY (`localidad_id`) REFERENCES `localidades` (`idlocalidad`); /*ON DELETE CASCADE ON UPDATE CASCADE*/;

ALTER TABLE cac_integrating_proyect_2022.pedidos 
ADD CONSTRAINT `pedido_provincia` FOREIGN KEY (`provincia_id`) REFERENCES `provincias` (`idprovincia`);
--------------------
/* Relacion de pedido con metodos de pago*/
ALTER TABLE cac_integrating_proyect_2022.pedidos 
ADD CONSTRAINT `pedido_metodo_de_pago` FOREIGN KEY (`forma_de_pago`) REFERENCES `metodos_de_pago` (`idmetodo_de_pago`);

/*ALTER TABLE cac_integrating_proyect_2022.pedidos DROP FOREIGN KEY `pedido_metodo_de_pago`;*/
