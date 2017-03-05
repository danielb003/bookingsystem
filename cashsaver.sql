-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2017 at 02:07 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cashsaver`
--

-- --------------------------------------------------------

--
-- Table structure for table `monthlyexpenses`
--

CREATE TABLE IF NOT EXISTS `monthlyexpenses` (
  `expenseID` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `expenseDesc` varchar(255) NOT NULL,
  `expenseAmt` double NOT NULL,
  `memberID` int(11) NOT NULL,
  `expenseDate` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monthlyexpenses`
--

INSERT INTO `monthlyexpenses` (`expenseID`, `type`, `expenseDesc`, `expenseAmt`, `memberID`, `expenseDate`) VALUES
(13, 'Reccuring', 'Car Loan', 124.82, 1, '0000-00-00'),
(17, 'Reccuring', 'Interlock', 106.2, 1, '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `monthlysavings`
--

CREATE TABLE IF NOT EXISTS `monthlysavings` (
  `savingID` int(11) NOT NULL,
  `savingAmt` double NOT NULL,
  `memberID` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monthlysavings`
--

INSERT INTO `monthlysavings` (`savingID`, `savingAmt`, `memberID`) VALUES
(1, 140, 1);

-- --------------------------------------------------------

--
-- Table structure for table `paycheques`
--

CREATE TABLE IF NOT EXISTS `paycheques` (
  `payNum` int(11) NOT NULL,
  `payAmt` double NOT NULL,
  `payDate` date NOT NULL,
  `memberID` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paycheques`
--

INSERT INTO `paycheques` (`payNum`, `payAmt`, `payDate`, `memberID`) VALUES
(1, 1370.61, '2016-06-01', 1),
(2, 1251.12, '2016-06-12', 4),
(3, 1138.2, '2016-08-01', 1),
(4, 1086.35, '2016-07-16', 4),
(5, 976.5, '2016-05-04', 4);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `transactionID` int(11) NOT NULL,
  `transactionDesc` varchar(255) NOT NULL,
  `transactionAmt` double NOT NULL,
  `transactionDate` date NOT NULL,
  `memberID` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transactionID`, `transactionDesc`, `transactionAmt`, `transactionDate`, `memberID`) VALUES
(1, 'McDonald''s High St Kew trans#45218665', -16.43, '2017-02-27', 1),
(2, 'Citylink Monash Freeway ref#843924528', -5.78, '2016-08-09', 1),
(3, 'Uber Saturday Night Trip ref#362728', -23.75, '2016-09-13', 3),
(4, 'City of Stonnington Parking ref#64329542', -13.65, '2016-09-01', 1),
(5, 'City of Whitehorse Parking ref#5493754', -4.86, '2016-08-16', 3),
(6, 'Origin Energy Monthly ref#74837232', -215.3, '2017-02-28', 1),
(7, 'Lumo Energy Monthly ref#1724118', -74.39, '2016-06-14', 3),
(8, 'Traffic Infringements Court CBD ref#27942423', -204.15, '2017-02-15', 1),
(9, 'Jeremy O''Halloran cash transfer', 32, '2016-08-08', 1),
(10, 'Marisa Bellino cash transfer', 170, '2016-07-24', 1),
(12, 'KFC ref#482934732', -13.43, '2017-02-28', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(16) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `street_address` varchar(100) NOT NULL,
  `suburb` varchar(30) NOT NULL,
  `state` varchar(20) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `postcode` int(4) NOT NULL,
  `account_name` varchar(60) NOT NULL,
  `BSB` varchar(6) NOT NULL,
  `account_number` int(9) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `email`, `password`, `first_name`, `last_name`, `street_address`, `suburb`, `state`, `mobile`, `postcode`, `account_name`, `BSB`, `account_number`) VALUES
(1, 'bellino-pak@hotmail.com', '1flakjacket3', 'Daniel', 'Bellino', '7/25 Derby Street', 'St Kilda', 'Victoria', '0401387966', 3101, 'Danny Bell', '086954', 852741963),
(3, 'jim_bob@hotmail.com', '1234abcd', 'Jim', 'Bob', '33 Johnson St', 'Jimtown', 'Victoria', '0412586498', 3170, 'Jim Bob', '038451', 452018972),
(4, 'homer_simpson@hotmail.com', 'abcd1234', 'Homer', 'Simpson', '742 Evergreen Terrace', 'Springfield', 'New South Wales', '0425611753', 7513, 'Homer Simpson', '061485', 851620473),
(10, 'michael_bruce@hotmail.com', '5555abcd', 'Michael', 'Bruce', '524 St Kilda Rd', 'St Kilda', 'Queensland', '0406221457', 9146, 'Michael Bruce Savers', '085469', 524784168);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `monthlyexpenses`
--
ALTER TABLE `monthlyexpenses`
  ADD PRIMARY KEY (`expenseID`);

--
-- Indexes for table `monthlysavings`
--
ALTER TABLE `monthlysavings`
  ADD PRIMARY KEY (`savingID`);

--
-- Indexes for table `paycheques`
--
ALTER TABLE `paycheques`
  ADD PRIMARY KEY (`payNum`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transactionID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `monthlyexpenses`
--
ALTER TABLE `monthlyexpenses`
  MODIFY `expenseID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `monthlysavings`
--
ALTER TABLE `monthlysavings`
  MODIFY `savingID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `paycheques`
--
ALTER TABLE `paycheques`
  MODIFY `payNum` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transactionID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
