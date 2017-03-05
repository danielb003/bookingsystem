<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>CashSaver - Members</title>
	<link rel="stylesheet" type="text/css" href="cashsaver.css" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Slab" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito|Poppins"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arimo"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Condensed">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
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
		<div id="members_background">
			<div id="edit_ba_title">
				<p>Edit Bank Account Details</p>
			</div>
			<div id="edit_ba_content">
				<p id="edit_ba_intro">Fill out all the details. All fields are required.</p>
				<form action="process_bankacc_edit.php" method="post">
					<label class="edit_ba_label">Account Name</label>
					<input type="text" class="edit_ba_input" name="edit_account_name" placeholder="Enter account name" pattern="[a-zA-Z \-\'\,\`(\)\.\_\&\#\%]{1,60}" required/>
					<label class="edit_ba_label">BSB Number</label>
					<input type="text" class="edit_ba_input" name="edit_account_bsb" placeholder="Enter account BSB" pattern="[0-9 \-]{6,7}" title="Enter 6 digits" required/>
					<label class="edit_ba_label">Account Number</label>
					<input type="text" class="edit_ba_input" name="edit_account_number" placeholder="Enter account number" pattern="[0-9 \-]{9,11}" title="Enter 9-11 digits" required/>
					<input type="submit" id="edit_ba_submit" name="edit_ba_submit" value="Submit" />
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