<?php
require 'session_start.php';
require 'connect_inc.php';

$delete_query = mysql_query("DELETE FROM monthlyexpenses WHERE expenseID='" . $_POST['expense_remove'] . "'");

unset($_POST['expenses_remove']);
header('Location: http://localhost/cashsaver/members_calculator.php');
?>