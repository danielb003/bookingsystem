<?php
require 'session_start.php';
require 'connect_inc.php';
if(isset($_POST['register_submit'])) {
	$_SESSION['valid_email'] = 0;
	$insert = true;
	$query = mysql_query("SELECT email FROM users");
	while($validate_email = mysql_fetch_assoc($query)) {
		if($_POST['register_email'] == $validate_email['email']) {
			$insert = false;
		}
	}
	if(!$insert) {
		$_SESSION['valid_email'] = 1;
		unset($_POST['register_submit']);
		header('Location: http://localhost/cashsaver/register.php');
	} else {
		$_SESSION['email'] = $_POST['register_email'];
		$_SESSION['password'] = $_POST['register_confirm_password'];
		$query2 = "INSERT INTO users (email, password, first_name, last_name, street_address, suburb, state, postcode, mobile, account_name, BSB, account_number) VALUES ('$_POST[register_email]','$_POST[register_confirm_password]','$_POST[register_firstname]','$_POST[register_lastname]','$_POST[register_street]','$_POST[register_suburb]','$_POST[register_state]','$_POST[register_postcode]','$_POST[register_mobile]','$_POST[register_account_name]','$_POST[register_bsb]','$_POST[register_account_num]')";
		mysql_query($query2,$con);

		$query3 = mysql_query("SELECT ID FROM users WHERE email='$_SESSION[email]'");
		while($id = mysql_fetch_array($query3)) {
			$_SESSION['id'] = $id['ID'];
		}
		unset($_POST['register_submit']);
		header('Location: http://localhost/cashsaver/members.php');
	}
	
} else {
	$_SESSION['valid_email'] = 2;
	header('Location: http://localhost/cashsaver/register.php');
}
?>