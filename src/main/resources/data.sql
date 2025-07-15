-- Insertar Juegos
INSERT INTO juegos (nombre, plataforma) VALUES ('Minecraft', 'PC');
INSERT INTO juegos (nombre, plataforma) VALUES ('FIFA 24', 'Xbox');
INSERT INTO juegos (nombre, plataforma) VALUES ('God of War', 'PlayStation');

-- Insertar Infraestructura
INSERT INTO infraestructura (nombre, detalle, id_juego) VALUES ('VM1', 'Servidor rápido para gaming', 1);
INSERT INTO infraestructura (nombre, detalle, id_juego) VALUES ('VM2', 'Máquina optimizada para streaming', 2);

-- Insertar Suscripciones
INSERT INTO suscripcion (nombre, duracion, id_maquina) VALUES ('Plan Básico', '3 meses', 1);
INSERT INTO suscripcion (nombre, duracion, id_maquina) VALUES ('Plan Premium', '12 meses', 2);

-- Insertar Usuarios
INSERT INTO usuarios (nombre_usuario, pnombre, snombre, papellido, sapellido, correo, contraseña, id_suscripcion, id_maquina) 
VALUES ('juan123', 'Juan', 'Pablo', 'Pérez', 'Soto', 'juan.perez@mail.com', '123456', 1, 1);

INSERT INTO usuarios (nombre_usuario, pnombre, snombre, papellido, sapellido, correo, contraseña, id_suscripcion, id_maquina) 
VALUES ('sofiaG', 'Sofía', 'Andrea', 'González', 'López', 'sofia.gonzalez@mail.com', 'clave789', 2, 2);
