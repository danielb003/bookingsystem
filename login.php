<!DOCTYPE html>
<?php
require 'session_start.php';
require 'connect_inc.php';
$error = false;
if(isset($_SESSION['login_error'])) {
	$error = $_SESSION['login_error'];
}
?>
<html>
<head>
	<meta charset="utf-8">
	<title>CashSaver - Login</title>
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
		<nav id="login_nav_bar">
			<ul>
			</ul>
		</nav>
		<div id="login_background">
			<div id="login_content">
				<p id="login_title">Members Login</p>
<?php  			if($error == true) {?>
				<p id="login_error">The email or password entered was incorrect or invalid. Please try again</p>
<?php } ?>
				<form action="process_login.php" method="post">
					<input id="email_input" type="email" name="email" placeholder="Enter Email" required/>
					<br>
					<input id="password_input" type="password" name="password" placeholder="Enter Password" required/>
					<br>
					<input id="login_submit" type="submit" value="Login" name="login_submit"/>
				</form>
			</div>
		</div>
	</div>
</body>

</html>