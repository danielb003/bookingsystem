<!DOCTYPE html>
<?php 
require 'session_start.php';
require 'connect_inc.php';

if(isset($_SESSION['valid_email'])) {
	if($_SESSION['valid_email'] == 1) {
		echo '<script>alert("That email is already registered! Please try again.");</script>';
		$_SESSION['valid_email'] = 0;
	} else if($_SESSION['valid_email'] == 2) {
		echo '<script>alert("Something went wrong! Please try again.");</script>';
		$_SESSION['valid_email'] = 0;
	} else {
		$_SESSION['valid_email'] = 0;
	}
}
?>
<html>
<head>
	<meta charset="utf-8">
	<title>CashSaver - Register</title>
	<link rel="stylesheet" type="text/css" href="cashsaver.css" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Slab" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito|Poppins"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arimo"/>
	<link rel="icon" href="dollar_sign.png"/>
</head>

<body>
	<div id="mainframe">
		<header id="header">
		<p id="header_title"><span id="header_title_word1">Cash</span><span id="header_title_word2">Saver</span><span id="header_title_copyright">&copy;</span></p>
		</header>
		<nav id="register_nav_bar">
			<ul>
			</ul>
		</nav>
		<div id="register_background">
			<div id="register_content">
				<p id="register_title">Register Your Account</p>
				<form action="process_register.php" method="post">
					<label class="register_label">First Name</label>
					<input type="text" class="register_input" name="register_firstname" placeholder="Enter first name" pattern="[A-Za-z,.'`-]{1,30}" title="Enter letters only" required/>
					<br>
					<label class="register_label">Last Name</label>
					<input type="text" class="register_input" name="register_lastname" placeholder="Enter last name" pattern="[A-Za-z,.'`-]{1,30}" required/>
					<br>
					<label class="register_label">Email</label>
					<input type="email" class="register_input" name="register_email" placeholder="Enter email" required/>
					<br>
					<label class="register_label">Password</label>
					<input type="password" id="password" class="register_input" name="register_password" placeholder="Enter password" pattern="^(?=.*[a-z])(?=.*\d).{8,16}$" title="Enter 8-16 characters" required/>
					<br>
					<label class="register_label">Confirm Password</label>
					<input type="password" id="confirm_password" class="register_input" name="register_confirm_password" placeholder="Re-enter password" pattern="^(?=.*[a-z])(?=.*\d).{8,16}$" title="Enter 8-16 characters"required/>
					<br>
					<label class="register_label">Street Address</label>
					<input type="text" class="register_input" name="register_street" placeholder="Enter street address" pattern="[a-zA-Z0-9,./\-(){}|'@#&~` ]{5,100}" required/>
					<br>
					<label class="register_label">Suburb</label>
					<input type="text" class="register_input" name="register_suburb" placeholder="Enter suburb" pattern="[a-zA-Z ]{1,30}" required/>
					<br>
					<label class="register_label">State</label>
					<input type="text" class="register_input" name="register_state" placeholder="Enter state" pattern="[a-zA-Z ]{1,20}" required/>
					<br>
					<label class="register_label">Postcode</label>
					<input type="text" class="register_input" name="register_postcode" placeholder="Enter postcode" pattern="[0-9]{4}" required/>
					<br>
					<label class="register_label">Mobile</label>
					<input type="tel" class="register_input" name="register_mobile" placeholder="Enter mobile number" pattern="[\+(\) 0-9]{10,15}" required/>
					<br>
					<label class="register_label">Bank Account Name</label>
					<input type="text" class="register_input" name="register_account_name" placeholder="Enter account name" pattern="[a-zA-Z \-\'\,\`(\)\.\_\&\#\%]{1,60}" required/>
					<br>
					<label class="register_label">BSB Number</label>
					<input type="text" class="register_input" name="register_bsb" placeholder="Enter BSB number" pattern="[0-9 \-]{6,7}" required/>
					<br>
					<label class="register_label">Bank Account Number</label>
					<input type="text" class="register_input" name="register_account_num" placeholder="Enter account number" pattern="[0-9 \-]{9,11}" required/>
					<script>
						var password = document.getElementById("password")
						var confirm_password = document.getElementById("confirm_password");

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
					<br>
					<input type="submit" id="register_submit" name="register_submit" value="Submit"/>
				</form>
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