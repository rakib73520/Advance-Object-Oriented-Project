-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2024 at 11:12 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `easy_explore`
--

-- --------------------------------------------------------

--
-- Table structure for table `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `place_id` varchar(255) NOT NULL,
  `myblog` text NOT NULL,
  `myblog_image` varchar(255) NOT NULL,
  `myblog_datetime` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_image` varchar(255) NOT NULL,
  `like_count` int(11) NOT NULL DEFAULT 0,
  `unlike_count` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `blog`
--

INSERT INTO `blog` (`id`, `place_id`, `myblog`, `myblog_image`, `myblog_datetime`, `user_id`, `user_name`, `user_image`, `like_count`, `unlike_count`) VALUES
(2, '1', 'Welcome to Jamuna Future Park, the epitome of entertainment, shopping, and leisure in Dhaka! Nestled in the heart of Bangladesh\'s capital, Jamuna Future Park stands tall as a beacon of modernity and convenience.\n\nSpanning over acres of land, this colossal complex boasts an array of attractions catering to every need and desire. From world-class shopping outlets featuring renowned international brands to an extensive food court offering culinary delights from around the globe, Jamuna Future Park ensures an unforgettable experience for visitors of all ages.', 'file:///C:\\Users\\Lenovo\\Downloads\\jamunablog.jpg', '2024-05-13 15:01:55', '2', 'Prapti Mojumder', 'file:///C:\\Users\\Lenovo\\Downloads\\prapti.jpg', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `discovery`
--

CREATE TABLE `discovery` (
  `id` int(11) NOT NULL,
  `place_name` varchar(255) NOT NULL,
  `place_location` varchar(255) NOT NULL,
  `place_description` varchar(255) NOT NULL,
  `place_image` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `likeunlike`
--

CREATE TABLE `likeunlike` (
  `id` int(11) NOT NULL,
  `blog_id` varchar(255) NOT NULL,
  `my_id` varchar(255) NOT NULL,
  `do_like` varchar(255) NOT NULL,
  `do_unlike` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `likeunlike`
--

INSERT INTO `likeunlike` (`id`, `blog_id`, `my_id`, `do_like`, `do_unlike`) VALUES
(1, '2', '2', '1', '0'),
(2, '2', '3', '1', '0');

-- --------------------------------------------------------

--
-- Table structure for table `places`
--

CREATE TABLE `places` (
  `id` int(11) NOT NULL,
  `place_name` varchar(255) NOT NULL,
  `place_location` varchar(255) NOT NULL,
  `place_description` varchar(255) NOT NULL,
  `place_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `places`
--

INSERT INTO `places` (`id`, `place_name`, `place_location`, `place_description`, `place_image`) VALUES
(1, 'Jamuna Future Park', 'KA-244, Kuril, Progoti Shoroni, Dhaka', 'Jamuna Future Park is a shopping mall in Dhaka, Bangladesh. It was inaugurated on 6 September 2013. Construction began in 2002, by Jamuna Builders Ltd.', 'file:///C:\\Users\\Lenovo\\Downloads\\jamuna.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `way` varchar(255) NOT NULL,
  `place_id` varchar(255) NOT NULL,
  `place_name` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_image` varchar(255) NOT NULL,
  `verify` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `way`, `place_id`, `place_name`, `user_id`, `user_name`, `user_image`, `verify`) VALUES
(1, '1. Badda ---> Basundhara (by Rickshaw)  2. Basundhara ---> Jamuna (by Rickshaw)', '1', 'Jamuna Future Park', '2', 'Prapti Mojumder', 'file:///C:\\Users\\Lenovo\\Downloads\\prapti.jpg', '1');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `experience` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`id`, `fullname`, `username`, `password`, `image`, `experience`) VALUES
(2, 'Prapti Mojumder', 'prapti', '1234', 'file:///C:\\Users\\Lenovo\\Downloads\\prapti.jpg', 106),
(3, 'Rakibul Hasan', 'rakib', '1234', 'file:///C:\\Users\\Lenovo\\Downloads\\rakib.jpg', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `discovery`
--
ALTER TABLE `discovery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `likeunlike`
--
ALTER TABLE `likeunlike`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `discovery`
--
ALTER TABLE `discovery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `likeunlike`
--
ALTER TABLE `likeunlike`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `places`
--
ALTER TABLE `places`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
