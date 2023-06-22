CREATE TABLE telefonos (
  telefono_id INT NOT NULL AUTO_INCREMENT,
  numero INT NOT NULL,
  codigo_ciudad INT NOT NULL,
  codigo_pais INT NOT NULL,
  PRIMARY KEY (telefono_id),
  CONSTRAINT numero_telefono_unique UNIQUE (numero)
);

CREATE TABLE usuarios (
  usuario_id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  correo VARCHAR(50) NOT NULL,
  clave VARCHAR(50) NOT NULL,
  creado DATETIME,
  modificado DATETIME,
  is_active INT,
  PRIMARY KEY (usuario_id),
  CONSTRAINT correo_usuario_unique UNIQUE (correo)
);

