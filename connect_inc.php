<?php
$mysql_host = 'localhost';
$mysql_user = 'root';
$mysql_pass = '';

$mysql_db = 'cashsaver';
$con = mysql_connect($mysql_host, $mysql_user, $mysql_pass);

if (!$con || !@mysql_select_db($mysql_db)) {
	die(mysql_error());
}
?>
