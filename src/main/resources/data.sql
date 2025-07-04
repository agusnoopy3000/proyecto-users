-- Insertar Juegos
INSERT INTO juegos (id, nombre, plataforma) VALUES (1, 'Minecraft', 'PC');
INSERT INTO juegos (id, nombre, plataforma) VALUES (2, 'FIFA 24', 'Xbox');
INSERT INTO juegos (id, nombre, plataforma) VALUES (3, 'God of War', 'PlayStation');

-- Insertar Infraestructura
INSERT INTO infraestructura (id, nombre, detalle, id_juego) VALUES (1, 'VM1', 'Servidor rápido para gaming', 1);
INSERT INTO infraestructura (id, nombre, detalle, id_juego) VALUES (2, 'VM2', 'Máquina optimizada para streaming', 2);

-- Insertar Suscripciones
INSERT INTO suscripcion (id, nombre, duracion, id_maquina) VALUES (1, 'Plan Básico', '3 meses', 1);
INSERT INTO suscripcion (id, nombre, duracion, id_maquina) VALUES (2, 'Plan Premium', '12 meses', 2);

-- Insertar Usuarios
INSERT INTO usuarios (id, nombre_usuario, pnombre, snombre, papellido, sapellido, correo, contraseña, id_suscripcion, id_maquina) 
VALUES (1, 'juan123', 'Juan', 'Pablo', 'Pérez', 'Soto', 'juan.perez@mail.com', '123456', 1, 1);

INSERT INTO usuarios (id, nombre_usuario, pnombre, snombre, papellido, sapellido, correo, contraseña, id_suscripcion, id_maquina) 
VALUES (2, 'sofiaG', 'Sofía', 'Andrea', 'González', 'López', 'sofia.gonzalez@mail.com', 'clave789', 2, 2);
