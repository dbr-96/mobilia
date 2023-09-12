-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-09-2023 a las 14:41:01
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mobilia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contractbyperson`
--

CREATE TABLE `contractbyperson` (
  `contractId` int(11) NOT NULL,
  `personId` int(11) NOT NULL,
  `role` enum('lessee','proprietor','solidaryDebtor') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `contractbyperson`
--

INSERT INTO `contractbyperson` (`contractId`, `personId`, `role`) VALUES
(13, 1, 'lessee'),
(13, 2, 'proprietor'),
(14, 2, 'lessee'),
(15, 3, 'lessee'),
(16, 4, 'lessee'),
(16, 3, 'solidaryDebtor'),
(17, 5, 'lessee'),
(14, 3, 'proprietor'),
(16, 1, 'proprietor'),
(17, 4, 'proprietor'),
(15, 4, 'proprietor'),
(14, 5, 'solidaryDebtor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contracts`
--

CREATE TABLE `contracts` (
  `contractId` int(11) NOT NULL,
  `contractCode` varchar(25) NOT NULL,
  `state` enum('Active','Inactive') NOT NULL DEFAULT 'Active',
  `propertyId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `contracts`
--

INSERT INTO `contracts` (`contractId`, `contractCode`, `state`, `propertyId`) VALUES
(13, 'CONTRATO001', 'Active', 1),
(14, 'CONTRATO002', 'Active', 2),
(15, 'CONTRATO003', 'Inactive', 3),
(16, 'CONTRATO004', 'Active', 4),
(17, 'CONTRATO005', 'Active', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persons`
--

CREATE TABLE `persons` (
  `personId` int(11) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `secondName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) NOT NULL,
  `secondLastName` varchar(20) NOT NULL,
  `identityDocument` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persons`
--

INSERT INTO `persons` (`personId`, `firstName`, `secondName`, `lastName`, `secondLastName`, `identityDocument`, `email`) VALUES
(1, 'Juan', 'Pablo', 'Perez', 'Gomez', '12345678', 'juan.perez@email.com'),
(2, 'Maria', NULL, 'Lopez', 'Rodriguez', '98765432', 'maria.lopez@email.com'),
(3, 'Carlos', 'Alberto', 'Gonzalez', 'Perez', '56789012', 'carlos.gonzalez@email.com'),
(4, 'Ana', 'Maria', 'Gomez', 'Fernandez', '23456789', 'ana.gomez@email.com'),
(5, 'David', 'Fernando', 'Rios', 'Padilla', '45612398', 'david.rios@email.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `properties`
--

CREATE TABLE `properties` (
  `propertyId` int(11) NOT NULL,
  `address` varchar(30) NOT NULL,
  `type` enum('House','Apartment','Local') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `properties`
--

INSERT INTO `properties` (`propertyId`, `address`, `type`) VALUES
(1, 'Cra 12 30-14', 'House'),
(2, 'Cll 23 1-08', 'Apartment'),
(3, 'Cra 40 76-33', 'Local'),
(4, 'Cll 101 8-04', 'House');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `contractbyperson`
--
ALTER TABLE `contractbyperson`
  ADD KEY `FK_contractId` (`contractId`),
  ADD KEY `FK_personId` (`personId`);

--
-- Indices de la tabla `contracts`
--
ALTER TABLE `contracts`
  ADD PRIMARY KEY (`contractId`),
  ADD UNIQUE KEY `UC_contractCode` (`contractCode`),
  ADD KEY `FK_propertyId` (`propertyId`);

--
-- Indices de la tabla `persons`
--
ALTER TABLE `persons`
  ADD PRIMARY KEY (`personId`),
  ADD UNIQUE KEY `UC_identityDocument` (`identityDocument`),
  ADD UNIQUE KEY `UC_email` (`email`);

--
-- Indices de la tabla `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`propertyId`),
  ADD UNIQUE KEY `UC_address` (`address`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `contracts`
--
ALTER TABLE `contracts`
  MODIFY `contractId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `persons`
--
ALTER TABLE `persons`
  MODIFY `personId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `properties`
--
ALTER TABLE `properties`
  MODIFY `propertyId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contractbyperson`
--
ALTER TABLE `contractbyperson`
  ADD CONSTRAINT `FK_contractId` FOREIGN KEY (`contractId`) REFERENCES `contracts` (`contractId`),
  ADD CONSTRAINT `FK_personId` FOREIGN KEY (`personId`) REFERENCES `persons` (`personId`);

--
-- Filtros para la tabla `contracts`
--
ALTER TABLE `contracts`
  ADD CONSTRAINT `FK_propertyId` FOREIGN KEY (`propertyId`) REFERENCES `properties` (`propertyId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
