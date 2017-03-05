<?php
require 'session_start.php';
require 'connect_inc.php';

if(isset($_POST['edit_ba_submit'])) {
	$acc_name_query = mysql_query("UPDATE users SET account_name='" . $_POST['edit_account_name'] . "' WHERE ID='" . $_SESSION['id'] . "'");
	$acc_bsb_query = mysql_query("UPDATE users SET BSB='" . $_POST['edit_account_bsb'] . "' WHERE ID='" . $_SESSION['id'] . "'");
	$acc_number_query = mysql_query("UPDATE users SET account_number='" . $_POST['edit_account_number'] . "' WHERE ID='" . $_SESSION['id'] . "'");
}

header('Location: http://localhost/cashsaver/members_account.php');
?>