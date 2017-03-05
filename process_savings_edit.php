<?php
require 'session_start.php';
require 'connect_inc.php';

$update_query = mysql_query("UPDATE monthlysavings SET savingAmt='" . $_POST['edit_fs_input'] . "' WHERE memberID='" . $_SESSION['id'] . "'");

unset($_POST['edit_fs_submit']);
header('Location: http://localhost/cashsaver/members_calculator.php');
?>