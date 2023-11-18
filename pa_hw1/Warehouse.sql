-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 18, 2023 at 12:48 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Warehouse`
--

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `customer_id` int(10) UNSIGNED NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `contact_person` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `post_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`customer_id`, `customer_name`, `contact_person`, `address`, `city`, `post_code`, `country`) VALUES
(1, 'Zeljko', 'zeljko@gmail.com', 'Zrenjaniska 45', 'Zrenjanin', '213555', 'Srbija'),
(2, 'Milica', 'milica@gmail.com', 'Voislava Ilica 23a', 'Novi Sad', '12212', 'Srbija'),
(3, 'Marko', 'marko@gmail.com', 'Vladike Nikolaja 2', 'Kragujevac', '34000', 'Srbija\r\n'),
(4, 'Jovan', 'jovan@gmail.com', 'Nikole Pasica 2', 'Kragujevac', '34000', 'Srbija\r\n'),
(5, 'Uros', 'uros@gmail.com', 'Karadjordjeva 2', 'Krusevac', '45555', 'Srbija'),
(6, 'Jovana', 'jovana@gmail.com', 'Kraljice marije 3', 'Beograd', '31122', 'Srbija'),
(7, 'Tamara', 'tamara@gmail.com', 'Njegoska 34', 'Beograd', '21321', 'Srbija'),
(8, 'Anja', 'anja@gmail.com', 'Vladike 22', 'Kragujevac', '34000', 'Srbija');

-- --------------------------------------------------------

--
-- Table structure for table `Employee`
--

CREATE TABLE `Employee` (
  `employee_id` int(10) UNSIGNED NOT NULL,
  `username` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `birth_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Employee`
--

INSERT INTO `Employee` (`employee_id`, `username`, `last_name`, `first_name`, `birth_date`) VALUES
(1, 'bojan', 'Petrovic', 'Bojan', '1998-03-01'),
(2, 'milos', 'Obradovic', 'Milos', '2000-02-05'),
(3, 'stefan', 'Stefanovic', 'Stefan', '1995-05-06'),
(4, 'darko', 'Jovanovic', 'Darko', '1990-05-07'),
(5, 'ognjen', 'Obradovic', 'Ognjen', '1988-10-11');

-- --------------------------------------------------------

--
-- Table structure for table `Order`
--

CREATE TABLE `Order` (
  `order_id` int(10) UNSIGNED NOT NULL,
  `order_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `customer_id` int(10) UNSIGNED NOT NULL,
  `employee_id` int(10) UNSIGNED DEFAULT NULL,
  `shipper_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Order`
--

INSERT INTO `Order` (`order_id`, `order_date`, `customer_id`, `employee_id`, `shipper_id`) VALUES
(1, '2023-11-17 14:03:29', 5, 1, 2),
(2, '2023-11-17 13:39:14', 4, 1, 2),
(3, '2023-11-18 00:32:43', 7, 4, 4),
(4, '2023-11-18 00:32:43', 8, 5, 6),
(5, '2023-11-18 00:33:10', 3, 5, 6),
(6, '2023-11-18 00:33:10', 2, 2, 3),
(7, '2023-11-18 00:33:30', 4, 5, 2),
(8, '2023-11-18 00:34:00', 1, 3, 5),
(9, '2023-11-18 00:34:00', 2, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `OrderDetail`
--

CREATE TABLE `OrderDetail` (
  `order_detail_id` int(10) UNSIGNED NOT NULL,
  `order_id` int(10) UNSIGNED NOT NULL,
  `product_id` int(10) UNSIGNED NOT NULL,
  `quantity` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `OrderDetail`
--

INSERT INTO `OrderDetail` (`order_detail_id`, `order_id`, `product_id`, `quantity`) VALUES
(1, 6, 7, 5),
(2, 5, 8, 1),
(3, 4, 3, 2),
(4, 3, 2, 1),
(5, 2, 5, 2),
(6, 1, 10, 1),
(7, 7, 4, 3),
(8, 8, 9, 1),
(9, 9, 6, 2);

-- --------------------------------------------------------

--
-- Table structure for table `Product`
--

CREATE TABLE `Product` (
  `product_id` int(10) UNSIGNED NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `supplier_id` int(10) UNSIGNED NOT NULL,
  `product_category` varchar(255) NOT NULL,
  `price_per_unit` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Product`
--

INSERT INTO `Product` (`product_id`, `product_name`, `supplier_id`, `product_category`, `price_per_unit`) VALUES
(1, 'Laptop HP 250G', 3, 'Tech', 1000),
(2, 'iPhone X', 3, 'Tech', 1300),
(3, 'Samsung Galaxy S21', 3, 'Tech', 1500),
(4, 'PlayStation 5', 1, 'Tech', 500),
(5, 'GoPro Hero', 1, 'Tech', 800),
(6, 'Comfy Sofa', 4, 'Furniture', 200),
(7, 'Urban Chair', 4, 'Furniture', 100),
(8, 'Work Desk', 4, 'Furniture', 300),
(9, 'SmartWatch Samsung', 5, 'Tech', 300),
(10, 'BlissBuds', 2, 'Tech', 200);

-- --------------------------------------------------------

--
-- Table structure for table `Shipper`
--

CREATE TABLE `Shipper` (
  `shipper_id` int(10) UNSIGNED NOT NULL,
  `shipper_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Shipper`
--

INSERT INTO `Shipper` (`shipper_id`, `shipper_name`, `phone`) VALUES
(1, 'GlobalLogistic', '06962354658'),
(2, 'LogisticDao', '0696546423'),
(3, 'AeroTrans', '0693256487'),
(4, 'SeaLink Logistics', '0643258426'),
(5, 'ExpressFlow', '0621479523'),
(6, 'OmniLogix', '0625465874');

-- --------------------------------------------------------

--
-- Table structure for table `Supplier`
--

CREATE TABLE `Supplier` (
  `supplier_id` int(10) UNSIGNED NOT NULL,
  `supplier_name` varchar(255) NOT NULL,
  `contact_person` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `post_code` int(10) UNSIGNED NOT NULL,
  `country` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Supplier`
--

INSERT INTO `Supplier` (`supplier_id`, `supplier_name`, `contact_person`, `address`, `city`, `post_code`, `country`, `phone`) VALUES
(1, 'DirectSource', 'direc@gmail.com', 'Oslobodjenja 2', 'Kragujevac', 34000, 'Srbija', '0632542156'),
(2, 'ProLogix', 'prolog@gmail.com', 'Vojvode Putnika 2', 'Beograd', 31562, 'Srbija', '0693254658'),
(3, 'TechSupply Solutions', 'techsol@gmail.com', 'Pozeska 32', 'Beograd', 44566, 'Srbija', '069213546'),
(4, 'GlobalTrade Partners', 'globaltrade@gmail.com', 'Krajiska 4', 'Novi sad', 23256, 'Srbija', '062135546'),
(5, 'PrimeSource Connect', 'prime@gmail.com', 'Humska 2', 'Beograd', 31256, 'Srbija', '0652134568');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `Employee`
--
ALTER TABLE `Employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `uq_username` (`username`);

--
-- Indexes for table `Order`
--
ALTER TABLE `Order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `fk_Orders_customer_id_idx` (`customer_id`),
  ADD KEY `fk_Orders_employee_id_idx` (`employee_id`),
  ADD KEY `fk_Orders_shipper_id_idx` (`shipper_id`);

--
-- Indexes for table `OrderDetail`
--
ALTER TABLE `OrderDetail`
  ADD PRIMARY KEY (`order_detail_id`),
  ADD KEY `fk_OrderDetails_order_id_idx` (`order_id`),
  ADD KEY `fk_OrderDetails_product_id_idx` (`product_id`);

--
-- Indexes for table `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `fk_Products_supplier_id_idx` (`supplier_id`);

--
-- Indexes for table `Shipper`
--
ALTER TABLE `Shipper`
  ADD PRIMARY KEY (`shipper_id`),
  ADD UNIQUE KEY `phone_UNIQUE` (`phone`);

--
-- Indexes for table `Supplier`
--
ALTER TABLE `Supplier`
  ADD PRIMARY KEY (`supplier_id`),
  ADD UNIQUE KEY `phone_UNIQUE` (`phone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Customer`
--
ALTER TABLE `Customer`
  MODIFY `customer_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `Employee`
--
ALTER TABLE `Employee`
  MODIFY `employee_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Order`
--
ALTER TABLE `Order`
  MODIFY `order_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `OrderDetail`
--
ALTER TABLE `OrderDetail`
  MODIFY `order_detail_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `Product`
--
ALTER TABLE `Product`
  MODIFY `product_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `Shipper`
--
ALTER TABLE `Shipper`
  MODIFY `shipper_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Supplier`
--
ALTER TABLE `Supplier`
  MODIFY `supplier_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Order`
--
ALTER TABLE `Order`
  ADD CONSTRAINT `fk_Orders_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Orders_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `Employee` (`employee_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `OrderDetail`
--
ALTER TABLE `OrderDetail`
  ADD CONSTRAINT `fk_OrderDetails_order_id` FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_OrderDetails_product_id` FOREIGN KEY (`product_id`) REFERENCES `Product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Product`
--
ALTER TABLE `Product`
  ADD CONSTRAINT `fk_Products_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `Supplier` (`supplier_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
