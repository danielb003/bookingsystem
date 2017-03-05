<?php
require 'session_start.php';
require 'connect_inc.php';

if(isset($_POST['login_submit'])) {
	$user = $_POST['email'];
	$password = $_POST['password'];

	$result = mysql_query("SELECT * FROM users WHERE email='$user' AND password='$password'") or die("Failed to query databse " . mysql_error());

	$row = mysql_fetch_array($result);
	if($row['email'] == $user && $row['password'] == $password) {
		$_SESSION['email'] = $_POST['email'];
		$_SESSION['password'] = $_POST['password'];
		$_SESSION['id'] = $row['ID'];
		header('Location: http://localhost/cashsaver/members_calculator.php');
		$_SESSION['valid_password'] = true;
	} else {
		echo '<script src="login.js">';
		echo 'alert("This is a javascript alert inside php");';
		echo '</script>';
		$_SESSION['login_error'] = true;
		header('Location: http://localhost/cashsaver/login.php');
	}
}
?>