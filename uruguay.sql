-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-09-2020 a las 04:42:58
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `uruguay`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimentos`
--

CREATE TABLE IF NOT EXISTS `alimentos` (
  `codigo_al` varchar(20) NOT NULL DEFAULT '',
  `tipo_al` varchar(50) DEFAULT NULL,
  `nombre_al` varchar(50) DEFAULT NULL,
  `cantidad_al` text,
  `precio_compra` decimal(10,2) DEFAULT NULL,
  `precio_venta` decimal(10,2) DEFAULT NULL,
  `ganancia` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`codigo_al`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alimentos`
--

INSERT INTO `alimentos` (`codigo_al`, `tipo_al`, `nombre_al`, `cantidad_al`, `precio_compra`, `precio_venta`, `ganancia`) VALUES
('AL0001', 'ROPA', 'PULOVERS', '765', '10.00', '20.00', '7650.00'),
('AL0002', 'CALZADO', 'BOTAS', '616', '30.00', '45.00', '9240.00'),
('AL0003', 'COSMETICOS', 'BRILLO DE LABIOS', '488', '10.00', '25.00', '7320.00'),
('AL0004', 'ROPA INTERIOR', 'CALENTICOS', '487', '20.00', '60.00', '19480.00'),
('AL0005', 'CERAMICA', 'TAZAS DE CAFE', '477', '30.00', '75.00', '21465.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dias`
--

CREATE TABLE IF NOT EXISTS `dias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dia` text NOT NULL,
  `mes` text NOT NULL,
  `año` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `dias`
--

INSERT INTO `dias` (`id`, `dia`, `mes`, `año`) VALUES
(1, '30', '5', '2020');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_deuda`
--

CREATE TABLE IF NOT EXISTS `registro_deuda` (
  `numero` varchar(20) DEFAULT NULL,
  `titular` text,
  `descripcion` text,
  `cantidad` text,
  `total` decimal(10,2) DEFAULT NULL,
  `inversion` decimal(10,2) DEFAULT NULL,
  `ganancia` decimal(10,2) DEFAULT NULL,
  `fecha` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_venta`
--

CREATE TABLE IF NOT EXISTS `registro_venta` (
  `numero` varchar(20) DEFAULT NULL,
  `descripcion` text,
  `cantidad` text,
  `total` decimal(10,2) DEFAULT NULL,
  `inversion` decimal(10,2) DEFAULT NULL,
  `ganancia` decimal(10,2) DEFAULT NULL,
  `fecha` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registro_venta`
--

INSERT INTO `registro_venta` (`numero`, `descripcion`, `cantidad`, `total`, `inversion`, `ganancia`, `fecha`) VALUES
('00000001', 'PULOVERS', '1', '20.00', '10.00', '10.00', '08/09/2020'),
('00000002', 'PULOVERS', '4', '1625.00', '40.00', '40.00', '08/09/2020'),
('00000002', 'BOTAS', '1', '1625.00', '30.00', '15.00', '08/09/2020'),
('00000002', 'CALENTICOS', '10', '1625.00', '200.00', '400.00', '08/09/2020'),
('00000002', 'TAZAS DE CAFE', '12', '1625.00', '360.00', '540.00', '08/09/2020');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `codigo_us` varchar(20) NOT NULL DEFAULT '',
  `nombre_us` varchar(50) DEFAULT NULL,
  `sexo_us` varchar(10) DEFAULT NULL,
  `tipo_us` varchar(50) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo_us`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`codigo_us`, `nombre_us`, `sexo_us`, `tipo_us`, `pass`) VALUES
('US0001', 'A', 'MASCULINO', 'ADMINISTRADOR', 'a');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
