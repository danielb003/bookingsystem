<!DOCTYPE html>
<?php 
require 'session_start.php';
require 'connect_inc.php';
?>
<html>
<head>
	<meta charset="utf-8">
	<title>CashSaver - Home</title>
	<link rel="stylesheet" type="text/css" href="cashsaver.css" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Slab" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito|Poppins">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arimo">
	<link rel="icon" href="dollar_sign.png">
</head>

<body>
	<div id="mainframe">
		<header id="header">
		<p id="header_title"><span id="header_title_word1">Cash</span><span id="header_title_word2">Saver</span><span id="header_title_copyright">&copy;</span></p>
		</header>
		<nav id="nav_bar">
			<ul>
				<li><a href="index.php">How Does It Work?</a></li>
				<li><a href="index.php">About Us</a></li>
				<li><a href="index.php">Contact Us</a></li>
				<li><a href="login.php">Members</a></li>
			</ul>
		</nav>
		<div id="top_index">
			<div id="top_index_content">
				<p id="top_index_c1">A simple and effective way to organise your money.</p>
				<p id="top_index_c2">With customizable budgets designed around your income.</p>
			</div>
			<form action="register.php">
				<button type="submit" id="register_button">Register</button>
			</form>
			<img src="cashsaver_background.jpg" id="top_image" width="1906" />
		</div>
		<div id="top_index_footer">
			<p id="top_index_footer_p1">Organise Your Money &emsp;|&emsp; Start Saving &emsp;|&emsp; Future Predictions</p>
			<p id="top_index_footer_p2">With no costs and no contracts it's a great service and you have nothing to lose!</p>
		</div>
		<div id="mid_index">
			<p id="mid_index_title">What We Offer</p>
			<img id="calc_image" src="calculator.png" width="150px" height="160px"/>
			<img id="graph_image" src="graph.png" width="150px" height="160px"/>
			<img id="arrow_image" src="arrow.png" width="150px" height="160px" />
			<p id="mid_index_p1">We provide you with a detailed calculator that allows you to fine tune your budget the way you like</p>
			<p id="mid_index_p2">We create accurate predictions of future savings and detail previous records in easy to read graphs and spreadsheets</p>
			<p id="mid_index_p3">You can see how much money you have left after budgeting so you can keep track of spendings</p>
		</div>
		<footer>
		<div id="footer">
			<p id="footer_p">Copyright of Daniel Bellino &emsp;|&emsp; All rights reserved</p>
		</div>
		</footer>
	</div>
</body>

</html>