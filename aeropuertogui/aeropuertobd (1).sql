-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 22-01-2024 a las 02:19:27
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `aeropuertobd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aeropuerto`
--

DROP TABLE IF EXISTS `aeropuerto`;
CREATE TABLE IF NOT EXISTS `aeropuerto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `aeropuerto_un` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `aeropuerto`
--

INSERT INTO `aeropuerto` (`id`, `nombre`, `ubicacion`) VALUES
(1, 'baq', 'calle 75'),
(2, 'bog', 'calle 10'),
(3, 'juanes', 'berlin'),
(4, 'olaaa', 'calle 13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros`
--

DROP TABLE IF EXISTS `pasajeros`;
CREATE TABLE IF NOT EXISTS `pasajeros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `pasaporte` varchar(45) NOT NULL,
  `equipaje` int DEFAULT NULL,
  `vuelo_id` int DEFAULT NULL,
  `puesto` int DEFAULT NULL,
  `uuid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pasajeros_un` (`uuid`),
  KEY `vuelo_fk` (`vuelo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pasajeros`
--

INSERT INTO `pasajeros` (`id`, `nombre`, `pasaporte`, `equipaje`, `vuelo_id`, `puesto`, `uuid`) VALUES
(1, 'Isaac', '1020', 20, NULL, NULL, NULL),
(2, 'Juancho', '123456789', 20, NULL, NULL, NULL),
(3, 'madona', '1010101', 20, 1, NULL, '2f5f1a6b-78c2-4bb3-b61c-3c983b693f7d'),
(4, 'jajajaja', '090909090', 30, 1, 5, '3d5be8c2-f8f7-41ff-a36e-995e1a732c88'),
(5, 'asdasdasd', '354345345', 50, 1, 8, '92528e46-34ea-491a-9d62-9fd5fb47e396'),
(6, 'Kale', '1233344444', 10, 1, 7, '44cc397f-8cfe-41eb-929b-1bb1ab2b0afb');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelos`
--

DROP TABLE IF EXISTS `vuelos`;
CREATE TABLE IF NOT EXISTS `vuelos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `num_vuelo` int NOT NULL,
  `aerolinea` varchar(45) NOT NULL,
  `hora_salida` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `destino` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `capacidad_max` int NOT NULL,
  `aeropuerto_id` int NOT NULL,
  `aeropuerto_destino_id` int NOT NULL,
  PRIMARY KEY (`id`,`aeropuerto_id`,`aeropuerto_destino_id`),
  UNIQUE KEY `vuelos_un` (`num_vuelo`),
  KEY `fk_vuelos_aeropuerto_idx` (`aeropuerto_id`),
  KEY `vuelos_FK` (`aeropuerto_destino_id`)
) ;

--
-- Volcado de datos para la tabla `vuelos`
--

INSERT INTO `vuelos` (`id`, `num_vuelo`, `aerolinea`, `hora_salida`, `destino`, `capacidad_max`, `aeropuerto_id`, `aeropuerto_destino_id`) VALUES
(1, 123, 'airlines', '14:00', 'Los angeles CA', 30, 1, 2),
(2, 50505, 'lalala', '9:00', 'galaxy', 60, 3, 4);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  ADD CONSTRAINT `vuelo_fk` FOREIGN KEY (`vuelo_id`) REFERENCES `vuelos` (`id`);

--
-- Filtros para la tabla `vuelos`
--
ALTER TABLE `vuelos`
  ADD CONSTRAINT `fk_vuelos_aeropuerto` FOREIGN KEY (`aeropuerto_id`) REFERENCES `aeropuerto` (`id`),
  ADD CONSTRAINT `vuelos_FK` FOREIGN KEY (`aeropuerto_destino_id`) REFERENCES `aeropuerto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
