<!DOCTYPE html>
<?php 
require 'session_start.php';
require 'connect_inc.php';
$query = mysql_query("SELECT transactionDate, transactionDesc, transactionAmt FROM transactions WHERE memberID='" . $_SESSION['id'] . "'");
$query2 = mysql_query("SELECT payAmt FROM paycheques WHERE memberID='" . $_SESSION['id'] . "' ORDER BY payDate");
$query3 = mysql_query("SELECT transactionDate, transactionDesc, transactionAmt FROM transactions WHERE memberID='" . $_SESSION['id'] . "' ORDER BY transactionDate desc");
$query4 = mysql_query("SELECT first_name FROM users WHERE ID='" . $_SESSION['id'] . "'");
/*while($firstname = mysql_fetch_assoc($query4)) {
		$fname = $firstname['first_name'];
}*/
$fname = mysql_fetch_row($query4);
/*if($result = mysql_query($query)) {
	while($query_row = mysql_fetch_assoc($result)) {
		$user = $query_row['email'];
		$password = $query_row['password'];

		echo 'the users email is ' . $user . '<br>And their password is ' . $password . '<br>';
	}
} else {
	echo mysql_error();
}*/

?>
<html>
<head>
	<meta charset="utf-8">
	<title>CashSaver - Members</title>
	<link rel="stylesheet" type="text/css" href="cashsaver.css" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Slab" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito|Poppins">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arimo">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Condensed">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="icon" href="dollar_sign.png">
</head>

<body>
	<div id="mainframe">
		<header id="header">
		<p id="header_title"><span id="header_title_word1">Cash</span><span id="header_title_word2">Saver</span><span id="header_title_copyright">&copy;</span></p>
		</header>
		<nav id="members_nav_bar">
			<ul>
				<li><a href="members.php">Dashboard</a></li>
				<li><a href="members_calculator.php">Calculator</a></li>
				<li><a href="index.php">Support &amp; FAQ's</a></li>
				<li><a href="members_account.php">Account</a></li>
			</ul>
		</nav>
		<div id="members_background">
			<div id="dashboard_title">
					<p>Welcome <?php echo $fname[0]; ?>!</p>
			</div>
			<div id="dashboard">
				<div id="dashboard_content">
					<p id="dashboard_content_p1">Remainder of your current pay cheque</p>
					<?php
					$trans_total = $after_exp = $total_pay = 0;
					while($row = mysql_fetch_assoc($query)) {
						$trans_total += $row['transactionAmt'];
					}
					while($pay_left = mysql_fetch_assoc($query2)) {
						$after_exp = $pay_left['payAmt'];
						$total_pay = $pay_left['payAmt'];
					}
					if($trans_total < 0 && $_SESSION['apply_calc'] == true) {
						$_SESSION['recent_paycheque'] += $trans_total;
						$_SESSION['apply_calc'] = false;
					} else if($trans_total >= 0 && $_SESSION['apply_calc'] == true) {
						$_SESSION['recent_paycheque'] -= $trans_total;
						$_SESSION['apply_calc'] = false;
					}
					
					echo "<p id='dashboard_content_p2'>$" . number_format($_SESSION['recent_paycheque'],2) . "</p>";
					echo "<p id='dashboard_content_p3'>of $" . number_format($total_pay,2) . "</p>";
					?>
					<div id="dashboard_content_seperator"></div>
				</div>
				<div id="dashboard_graph">
				</div>
				<div id="dashboard_overview">
					<table id="dashboard_overview_table">
						<tr id="dashboard_overview_header_row">
							<th id="table_date">Date</th>
							<th id="table_desc">Description</th>
							<th id="table_amt">Amount</th>
						</tr>
						<?php
						$first_day_of_month = date('Y-m-01');
						$last_day_of_month = date('Y-m-t');
						while($row = mysql_fetch_assoc($query3)) {
							if($row['transactionDate'] >= $first_day_of_month && $row['transactionDate'] <= $last_day_of_month) {
								if($row['transactionAmt'] < 0) {
									echo "<tr class='transaction_row_neg'>";
									echo "<td>" . $row['transactionDate'] . "</td>";
									echo "<td>" . $row['transactionDesc'] . "</td>";
									echo "<td>$" . number_format($row['transactionAmt'],2) . "</td>";
								} else {
									echo "<tr class='transaction_row_pos'>";
									echo "<td>" . $row['transactionDate'] . "</td>";
									echo "<td>" . $row['transactionDesc'] . "</td>";
									echo "<td>$" . number_format($row['transactionAmt'],2) . "</td>";
									echo "</tr>";
								}
							}
						}
						?>
					</table>
				</div>
				<div id="dashboard_summary">
					<p id="dashboard_summary_title">Cash available today</p>
					<?php
						$timestamp = date('Y-m-d');

						$daysRemaining = date('t', strtotime($timestamp)) - date('j', strtotime($timestamp));

						$available_today = $_SESSION['recent_paycheque'] / $daysRemaining;
						
						echo "<p id='dashboard_summary_total'>$" . number_format($available_today,2) . "</p>";
					?>
				</div>
				<!-- A hidden input named public_token will be appended to this form
				once the user has completed the Link flow. Link will then submit the
				form, sending the public_token to your server. -->
				<form id="some-id" method="POST" action="/authenticate"></form>

				<!-- See the Parameter Reference for additional documentation. -->
				<script
				  src="https://cdn.plaid.com/link/v2/stable/link-initialize.js"
				  data-client-name="Client Name"
				  data-form-id="some-id"
				  data-key="test_key"
				  data-product="auth"
				  data-env="tartan">
				</script>
			</div>
		</div>
		<footer>
		<div id="footer">
			<p id="footer_p">Copyright of Daniel Bellino &emsp;|&emsp; All rights reserved</p>
		</div>
		</footer>
	</div>
</body>

</html>