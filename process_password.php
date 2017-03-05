<?php
require 'session_start.php';
require 'connect_inc.php';

$query = mysql_query("SELECT password FROM users WHERE ID='" . $_SESSION['id'] . "'");
$result = mysql_fetch_row($query);

if($_POST['old_password'] == $result[0]) {
	$update_query = mysql_query("UPDATE users SET password='" . $_POST['confirm_new_password'] . "' WHERE ID='" . $_SESSION['id'] . "'");
	$_SESSION['valid_password'] = true;
} else {
	$_SESSION['valid_password'] = false;
}

unset($_POST['new_pass_submit']);
header('Location: http://localhost/cashsaver/members_account.php');
?>