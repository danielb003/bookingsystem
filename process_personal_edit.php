<?php
require 'session_start.php';
require 'connect_inc.php';

if(!empty($_POST['edit_firstname'])) {
	$fname_query = mysql_query("UPDATE users SET first_name='" . $_POST['edit_firstname'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}
if(!empty($_POST['edit_lastname'])) {
	$lname_query = mysql_query("UPDATE users SET last_name='" . $_POST['edit_lastname'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}
if(!empty($_POST['edit_email'])) {
	$email_query = mysql_query("UPDATE users SET email='" . $_POST['edit_email'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}
if(!empty($_POST['edit_street'])) {
	$street_query = mysql_query("UPDATE users SET street_address='" . $_POST['edit_street'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}
if(!empty($_POST['edit_suburb'])) {
	$suburb_query = mysql_query("UPDATE users SET suburb='" . $_POST['edit_suburb'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}
if(!empty($_POST['edit_state'])) {
	$state_query = mysql_query("UPDATE users SET state='" . $_POST['edit_state'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}
if(!empty($_POST['edit_postcode'])) {
	$postcode_query = mysql_query("UPDATE users SET postcode='" . $_POST['edit_postcode'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}
if(!empty($_POST['edit_mobile'])) {
	$mobile_query = mysql_query("UPDATE users SET mobile='" . $_POST['edit_mobile'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}

header('Location: http://localhost/cashsaver/members_account.php');
?>