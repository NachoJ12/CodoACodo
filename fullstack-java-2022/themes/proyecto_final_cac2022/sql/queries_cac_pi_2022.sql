/* Usuarios */
SELECT * FROM usuarios;
SELECT * FROM usuarios WHERE email = 'nacho.j@gmail.com' AND password = '123456';

/* Provincias */
SELECT * FROM provincias;

/* Localidades */
SELECT * FROM localidades;

/* Localidades relacionadas con el nombre de la provincia */
SELECT * FROM localidades INNER JOIN provincias ON localidades.id_provincia_localidad = provincias.idprovincia;

/* Pedidos */
SELECT * FROM pedidos;

/* Pedidos custom (trae nombre de provincia y localidad, metodo de pago) */
SELECT PE.idpedido, CONCAT(PE.nombre, " ", PE.apellido) nombre_y_apellido, PE.usuario, PE.email, PE.lugar_entrega, PE.codigo_postal, PE.tarjeta_titular, PE.tarjeta_numero, PE.tarjeta_vencimiento, 
LOC.nombre_localidad, PROV.nombre AS nombre_provincia, MP.tipo_de_metodo 
FROM pedidos PE 
INNER JOIN provincias PROV ON PROV.idprovincia = PE.provincia_id 
INNER JOIN localidades LOC ON LOC.idlocalidad = PE.localidad_id 
INNER JOIN metodos_de_pago MP ON MP.idmetodo_de_pago = PE.forma_de_pago
ORDER BY PE.idpedido;

/* Metodos de pago */
SELECT * FROM metodos_de_pago;


/* Drops table and database */
DROP TABLE IF EXISTS cac_integrating_proyect_2022.usuarios;
DROP DATABASE IF EXISTS cac_integrating_proyect_2022;

