<!DOCTYPE html>
<?php 
require 'session_start.php';
require 'connect_inc.php';

$_SESSION['apply_calc'] = true;
$query = mysql_query("SELECT payAmt FROM paycheques WHERE memberID='" . $_SESSION['id'] . "' ORDER BY payDate desc");
$query2 = mysql_query("SELECT type, expenseDesc, expenseAmt FROM monthlyexpenses WHERE memberID='" . $_SESSION['id'] . "' ORDER BY expenseAmt desc");
$query3 = mysql_query("SELECT expenseAmt FROM monthlyexpenses WHERE memberID='" . $_SESSION['id'] . "'");
$query4 = mysql_query("SELECT expenseID, type, expenseDesc, expenseAmt FROM monthlyexpenses WHERE memberID='" . $_SESSION['id'] . "'");
$query5 = mysql_query("SELECT savingID, savingAmt FROM monthlysavings WHERE memberID='" . $_SESSION['id'] . "'");

$recent_paycheque = mysql_fetch_row($query);
$total_planned_expenses = 0;
$total_planned_savings = 0;
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
				<li><a href="php/php/yodlee/apps/yodleApp.php">Support &amp; FAQ's</a></li>
				<li><a href="members_account.php">Account</a></li>
			</ul>
		</nav>
		<div id="members_background">
			<div id="calculator_title">
				<p>Calculator</p>
			</div>
			<div id="calculator_content">
				<p id="calculator_content_p1">Your current pay cheque</p>
				<p id="calculator_content_p2">$<?php echo number_format($recent_paycheque[0],2); ?></p>
				<p id="calculator_seperator"></p>
				<p id="expenses_title">Planned Expenses</p>
				<div id="expenses_div">
					<table id="expenses_table">
						<tr id="expenses_header_row">
							<th id="expenses_type">Type</th>
							<th id="expenses_desc">Description</th>
							<th id="expenses_amt">Amount</th>
						</tr>
						<?php
						while($row = mysql_fetch_assoc($query2)) {
								echo "<tr class='expense_row'>";
								echo "<td>" . $row['type'] . "</td>";
								echo "<td>" . $row['expenseDesc'] . "</td>";
								echo "<td>$" . number_format($row['expenseAmt'],2) . "</td>";
								echo "</tr>";
						}
						?>
					</table>
				</div>
				<form action="process_add_expense.php" method="post">
					<select id="expenses_add_type" class="add_input" name="expenses_add_type" required>
						<option value="" selected disabled>Choose Type</option>
						<option value="Reccuring">Reccuring</option>
						<option value="One Off">One-Off</option>
					</select>
					<input type="text" id="expenses_add_desc" class="add_input" name="expenses_add_desc" placeholder="Enter expense description" pattern="[A-Za-z,.'`- ]{1,50}" required/>
					<input type="text" id="expenses_add_amt" class="add_input" name="expenses_add_amt" placeholder="Enter expense amount" pattern="[0-9.]{1,30}" required/>
					<input type="submit" id="expenses_add" name="expenses_add" value="Add"/>
				</form>
				<form action="process_delete_expense.php" method="post">
					<?php
					$select="<select id='expense_remove' class='add_input' name='expense_remove' required><option value='' selected disabled>Click to Choose</option>";
					while($row = mysql_fetch_assoc($query4)) {
					$select.="<option value='" . $row['expenseID'] . "''>" . $row['type'] . " - " . $row['expenseDesc'] . " - $" . number_format($row['expenseAmt'],2) . "</option>";
					}
					$select.="</select>";
					echo $select;
					?>
					<input type="submit" id="expense_delete" name="expense_delete" value="Delete"/>
				</form>
				<p id="calculator_content_p3">After planned expenses</p>
				<p id="calculator_content_p4">$<?php 
					while($row = mysql_fetch_assoc($query3)) {
						$total_planned_expenses += $row['expenseAmt'];
					} 
					$recent_paycheque[0] -= $total_planned_expenses;
					echo number_format($recent_paycheque[0],2); ?></p>
				<p id="calculator_seperator_2"></p>
				<p id="savings_title">Fixed Savings</p>
				<form action="fixed_savings_edit.php" method="post">
					<input type="submit" id="savings_change" name="savings_change" value="Change"/>
				</form>
				<p id="savings_amount">$-<?php 
					while($row = mysql_fetch_assoc($query5)) {
						$total_planned_savings += $row['savingAmt'];
					}
					echo number_format($total_planned_savings,2); ?></p>
				<p id="calculator_seperator_3"></p>
				<p id="total_title">Pay cheque remainder</p>
				<?php 
					$recent_paycheque[0] -= $total_planned_savings;
					$_SESSION['recent_paycheque'] = $recent_paycheque[0];
					if($recent_paycheque[0] >= 0) {
						echo "<p id='calculator_total_pos'>$" . number_format($recent_paycheque[0],2) . "</p>";
					} else {
						echo "<p id='calculator_total_neg'>$" . number_format($recent_paycheque[0],2) . "</p>";
					}
				?>
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