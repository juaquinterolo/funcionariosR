-- ==========================================
-- Script de creación de la base de datos
-- Proyecto: Gestión de Funcionarios
-- ==========================================

-- 1️Crear la base de datos y seleccionarla
CREATE DATABASE IF NOT EXISTS gestion_funcionarios;
USE gestion_funcionarios;

-- 2️Crear tabla: funcionarios
CREATE TABLE funcionarios (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    tipo_identificacion VARCHAR(20) NOT NULL,
    numero_identificacion VARCHAR(20) NOT NULL UNIQUE,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    estado_civil VARCHAR(20),
    sexo CHAR(1),
    direccion VARCHAR(100),
    telefono VARCHAR(20),
    fecha_nacimiento DATE
) ENGINE=InnoDB;

-- 3️Crear tabla: familiares
CREATE TABLE familiares (
    id_familiar INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT NOT NULL,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    parentesco VARCHAR(30) NOT NULL,
    fecha_nacimiento DATE,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id_funcionario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 4️Crear tabla: estudios
CREATE TABLE estudios (
    id_estudio INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT NOT NULL,
    universidad VARCHAR(100) NOT NULL,
    nivel_estudio VARCHAR(50) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id_funcionario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 5️Insertar datos de ejemplo
INSERT INTO funcionarios (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento)
VALUES 
('CC', '1001234567', 'Juan', 'Pérez López', 'Casado', 'M', 'Calle 10 #12-34', '3004567890', '1985-03-22'),
('CC', '1009876543', 'María', 'Gómez Ruiz', 'Soltera', 'F', 'Carrera 45 #56-78', '3109876543', '1990-08-15');

INSERT INTO familiares (id_funcionario, nombres, apellidos, parentesco, fecha_nacimiento)
VALUES
(1, 'Ana', 'Pérez Gómez', 'Hija', '2010-05-10'),
(1, 'Laura', 'López Martínez', 'Esposa', '1987-02-01'),
(2, 'Carlos', 'Gómez Ruiz', 'Padre', '1960-07-15');

INSERT INTO estudios (id_funcionario, universidad, nivel_estudio, titulo)
VALUES
(1, 'Universidad de Antioquia', 'Profesional', 'Ingeniería de Sistemas'),
(1, 'Universidad Nacional', 'Posgrado', 'Especialización en Gerencia de Proyectos'),
(2, 'Politécnico Jaime Isaza Cadavid', 'Tecnólogo', 'Gestión Empresarial');
