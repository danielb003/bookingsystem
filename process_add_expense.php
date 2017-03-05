<?php
require 'session_start.php';
require 'connect_inc.php';

$insert_query = mysql_query("INSERT INTO monthlyexpenses(type, expenseDesc, expenseAmt, memberID) VALUES ('" . $_POST['expenses_add_type'] . "', '" . $_POST['expenses_add_desc'] . "', '" . $_POST['expenses_add_amt'] . "', '" . $_SESSION['id'] . "')");

unset($_POST['expenses_add']);
header('Location: http://localhost/cashsaver/members_calculator.php');
?>