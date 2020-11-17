-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2020 a las 15:29:59
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
  `cantidad_al` int(11) DEFAULT NULL,
  `precio_compra` decimal(10,2) DEFAULT NULL,
  `precio_venta` decimal(10,2) DEFAULT NULL,
  `ganancia` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`codigo_al`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alimentos`
--

INSERT INTO `alimentos` (`codigo_al`, `tipo_al`, `nombre_al`, `cantidad_al`, `precio_compra`, `precio_venta`, `ganancia`) VALUES
('AL0001', 'ROPA', 'PULOVERS', 748, '10.00', '20.00', '7480.00'),
('AL0002', 'CALZADO', 'BOTAS', 606, '30.00', '45.00', '9090.00'),
('AL0003', 'COSMETICOS', 'BRILLO DE LABIOS', 477, '10.00', '25.00', '7155.00'),
('AL0004', 'ROPA INTERIOR', 'CALENTICOS', 482, '20.00', '60.00', '19280.00'),
('AL0005', 'CERAMICA', 'TAZAS DE CAFE', 442, '30.00', '75.00', '19890.00'),
('AL0006', 'ACCESORIOS', 'SD', 123, '123.00', '213.00', '11070.00'),
('AL0007', 'ACCESORIOS', 'FDG', 113, '123.00', '123.00', '0.00'),
('AL0008', 'ACCESORIOS', 'HG', 208, '123.00', '123.00', '0.00'),
('AL0009', 'ACCESORIOS', 'JHS', 114, '123.00', '123.00', '0.00'),
('AL0010', 'ACCESORIOS', 'HGFD', 122, '123.00', '132.00', '1098.00'),
('AL0011', 'ACCESORIOS', 'HGFX', 120, '123.00', '231.00', '12960.00'),
('AL0012', 'ACCESORIOS', 'SDF', 120, '123.00', '123.00', '0.00'),
('AL0013', 'ACCESORIOS', 'SGBXZ', 121, '123.00', '123.00', '0.00'),
('AL0014', 'ACCESORIOS', 'DGV', 121, '123.00', '123.00', '0.00'),
('AL0015', 'ACCESORIOS', 'AAAAAAAAAAAA', 0, '1.00', '1.00', '0.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intentos`
--

CREATE TABLE IF NOT EXISTS `intentos` (
  `numero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mac`
--

CREATE TABLE IF NOT EXISTS `mac` (
  `mac` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_deuda`
--

CREATE TABLE IF NOT EXISTS `registro_deuda` (
  `numero` varchar(20) DEFAULT NULL,
  `titular` text,
  `descripcion` text,
  `cantidad` int(11) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `inversion` decimal(10,2) DEFAULT NULL,
  `ganancia` decimal(10,2) DEFAULT NULL,
  `fecha` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registro_deuda`
--

INSERT INTO `registro_deuda` (`numero`, `titular`, `descripcion`, `cantidad`, `total`, `inversion`, `ganancia`, `fecha`) VALUES
('00000003', 'ALEJANDRO', 'PULOVERS', 12, '430.00', '120.00', '120.00', '03/10/2020'),
('00000003', 'ALEJANDRO', 'BOTAS', 2, '430.00', '60.00', '30.00', '03/10/2020'),
('00000003', 'ALEJANDRO', 'BRILLO DE LABIOS', 4, '430.00', '40.00', '60.00', '03/10/2020'),
('00000004', 'CLAUDIA', 'BRILLO DE LABIOS', 3, '465.00', '30.00', '45.00', '03/10/2020'),
('00000004', 'CLAUDIA', 'TAZAS DE CAFE', 2, '465.00', '60.00', '90.00', '03/10/2020'),
('00000004', 'CLAUDIA', 'CALENTICOS', 4, '465.00', '80.00', '160.00', '03/10/2020');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_venta`
--

CREATE TABLE IF NOT EXISTS `registro_venta` (
  `numero` varchar(20) DEFAULT NULL,
  `descripcion` text,
  `cantidad` int(11) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `inversion` decimal(10,2) DEFAULT NULL,
  `ganancia` decimal(10,2) DEFAULT NULL,
  `fecha` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registro_venta`
--

INSERT INTO `registro_venta` (`numero`, `descripcion`, `cantidad`, `total`, `inversion`, `ganancia`, `fecha`) VALUES
('00000005', 'TAZAS DE CAFE', 5, '400.00', '150.00', '225.00', '17/10/2020'),
('00000005', 'BRILLO DE LABIOS', 1, '400.00', '10.00', '15.00', '17/10/2020'),
('00000006', 'PULOVERS', 4, '1544.00', '40.00', '40.00', '17/10/2020'),
('00000006', 'BOTAS', 6, '1544.00', '180.00', '90.00', '17/10/2020'),
('00000006', 'CALENTICOS', 1, '1544.00', '20.00', '40.00', '17/10/2020'),
('00000006', 'TAZAS DE CAFE', 2, '1544.00', '60.00', '90.00', '17/10/2020'),
('00000006', 'FDG', 4, '1544.00', '492.00', '0.00', '17/10/2020'),
('00000006', 'JHS', 3, '1544.00', '369.00', '0.00', '17/10/2020'),
('00000006', 'SGBXZ', 1, '1544.00', '123.00', '0.00', '17/10/2020'),
('00000007', 'BRILLO DE LABIOS', 2, '3581.00', '20.00', '30.00', '17/10/2020'),
('00000007', 'DGV', 1, '3581.00', '123.00', '0.00', '17/10/2020'),
('00000007', 'SGBXZ', 1, '3581.00', '123.00', '0.00', '17/10/2020'),
('00000007', 'SDF', 3, '3581.00', '369.00', '0.00', '17/10/2020'),
('00000007', 'HGFX', 3, '3581.00', '369.00', '324.00', '17/10/2020'),
('00000007', 'HGFD', 1, '3581.00', '123.00', '9.00', '17/10/2020'),
('00000007', 'JHS', 6, '3581.00', '738.00', '0.00', '17/10/2020'),
('00000007', 'HG', 5, '3581.00', '615.00', '0.00', '17/10/2020'),
('00000007', 'FDG', 6, '3581.00', '738.00', '0.00', '17/10/2020');

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
('US0001', 'ALE', 'MASCULINO', 'ADMINISTRADOR', 'ale'),
('US0002', 'AS', 'MASCULINO', 'NORMAL', 'as');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
