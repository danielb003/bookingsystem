<!DOCTYPE html>
<?php 
require 'session_start.php';
require 'connect_inc.php';
if($_SESSION['valid_password'] == false) {
	echo "<script>alert('Your old password was incorrect! New password not saved.');</script>";
}
$query = mysql_query("SELECT first_name, last_name, street_address, suburb, state, mobile, email, account_name, BSB, account_number FROM users WHERE ID='" . $_SESSION['id'] . "'");
$q1_result = mysql_fetch_row($query);
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
			<div id="account_title">
				<p>Account</p>
			</div>
			<div id="account_content">
				<div id="account_personal">
					<p id="account_content_title">Personal Details</p>
					<form action="personal_details_edit.php" method="post">
						<input type="image" id="account_edit_icon" src="edit_icon.png" width="25px" height="25px" title="Edit personal details">
					</form>
					<p class="account_content_p">Full Name &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<?php echo $q1_result[0] . " " . $q1_result[1]; ?><p>
					<p class="account_content_p">Address &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;<?php echo $q1_result[2] . ", " .$q1_result[3] . "<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;" . $q1_result[4]; ?></p>
					<p class="account_content_p">Mobile &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;<?php echo $q1_result[5]; ?></p>
					<p class="account_content_p">Email &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;<?php echo $q1_result[6]; ?></p>
					<p class="account_content_p">Change Password</p>
					<form action="process_password.php" method="post" id="account_form">
						<input type="password" class="old_and_new_password" name="old_password" placeholder="Enter old password" pattern="^.{8,16}$" title="Enter 8-16 of any character" required/>
						<input type="password" id="new_password" class="old_and_new_password" name="new_password" placeholder="Enter new password" pattern="^(?=.*[a-z])(?=.*\d).{8,16}$" title="Enter 8-16 characters" required/>
						<input type="password" id="confirm_new_password" name="confirm_new_password" placeholder="Re-enter new password" pattern="^(?=.*[a-z])(?=.*\d).{8,16}$" title="Enter 8-16 characters" required/>
						<script>
						var password = document.getElementById("new_password")
						var confirm_password = document.getElementById("confirm_new_password");

						function validatePassword() {
							if(password.value != confirm_password.value) {
								confirm_password.setCustomValidity("Passwords don't match!");
							} else {
								confirm_password.setCustomValidity("");
							}
						}
						password.onchange = validatePassword;
						confirm_password.onkeyup = validatePassword;
						</script>
						<input type="submit" id="new_pass_submit" name="new_pass_submit" value="Go"/>
					</form>
				</div>
				<div id="bank_account_details">
					<p id="bank_details_title">Bank Details</p>
					<form action="bank_account_edit.php" method="post">
						<input type="image" id="bank_edit_icon" src="edit_icon.png" width="25px" height="25px" title="Edit bank details">
					</form>
					<p class="bank_details_p">Account Name &emsp;&emsp;&emsp;&nbsp;&nbsp;<?php echo $q1_result[7]; ?></p>
					<p class="bank_details_p">BSB Number &emsp;&emsp;&emsp;&emsp;&emsp;<?php echo $q1_result[8]; ?></p>
					<p class="bank_details_p">Account Number &emsp;&emsp;&nbsp;&nbsp;<?php echo $q1_result[9]; ?></p>
				</div>
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