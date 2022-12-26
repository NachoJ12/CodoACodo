/* Insert usuarios */
INSERT INTO cac_integrating_proyect_2022.usuarios VALUES 
	(default, "nacho.j@gmail.com", "123456", "nachoj", "Nacho", "Justel"),
    (default, "lea.g@gmail.com", "123456", "lea", "Leandro", "Grammatico");

/* Insert provincias */
INSERT INTO cac_integrating_proyect_2022.provincias VALUES 
	(default, "Buenos Aires"),
	(default, "Mendoza");

/* Insert localidades */
INSERT INTO cac_integrating_proyect_2022.localidades VALUES
	(default, "Moreno", 1),
    (default, "Olivos", 1),
    (default, "Guaymallén", 2);

/* Insert pedido */
INSERT INTO cac_integrating_proyect_2022.pedidos VALUES 
	(default, "Nacho", "Justel", "nachoj","nacho.j@gmail.com", "Calle falsa 123", 2,1, "1636", 1, "Nacho Justel", 1111222233334444, "08/23", 123),
    (default, "Leandro", "Grammatico", "lea","lea.g@gmail.com", "Calle 28 y 157", 1,1, "1744", 1, "Leandro Grammatico", 7777222233336666, "02/27", 785),
    (default, "Nacho", "Justel", "nachoj","nacho.j@gmail.com", "Calle falsa 123", 2,1, "1636", 2, "Nacho Justel", 1111222233334444, "08/23", 123);
    
/* Insert metodos de pago */
INSERT INTO cac_integrating_proyect_2022.metodos_de_pago VALUES 
	(default, "Tarjeta de Crédito"),
	(default, "Mercado Pago");