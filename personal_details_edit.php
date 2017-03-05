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
			<div id="edit_pd_title">
				<p>Edit Personal Details</p>
			</div>
			<div id="edit_pd_content">
				<p id="edit_pd_intro">Fill out the details you would like to change and leave the fields blank if no update is required</p>
				<form action="process_personal_edit.php" method="post">
					<label class="edit_pd_label">First Name</label>
					<input type="text" class="edit_pd_input" name="edit_firstname" placeholder="Enter first name" pattern="[A-Za-z,.'`-]{1,30}" />
					<label class="edit_pd_label">Last Name</label>
					<input type="text" class="edit_pd_input" name="edit_lastname" placeholder="Enter last name" pattern="[A-Za-z,.'`-]{1,30}" />
					<label class="edit_pd_label">Email</label>
					<input type="email" class="edit_pd_input" name="edit_email" placeholder="Enter email" />
					<label class="edit_pd_label">Street Address</label>
					<input type="text" class="edit_pd_input" name="edit_street" placeholder="Enter street address" pattern="[a-zA-Z0-9,./\-(){}|'@#&~` ]{5,100}" />
					<label class="edit_pd_label">Suburb</label>
					<input type="text" class="edit_pd_input" name="edit_suburb" placeholder="Enter suburb" pattern="[a-zA-Z ]{1,30}" />
					<label class="edit_pd_label">State</label>
					<input type="text" class="edit_pd_input" name="edit_state" placeholder="Enter state" pattern="[a-zA-Z ]{1,20}"/>
					<label class="edit_pd_label">Postcode</label>
					<input type="text" class="edit_pd_input" name="edit_postcode" placeholder="Enter postcode" pattern="[0-9]{4}" />
					<label class="edit_pd_label">Mobile</label>
					<input type="tel" class="edit_pd_input" name="edit_mobile" placeholder="Enter mobile number" pattern="[\+(\) 0-9]{10,15}" />
					<input type="submit" id="edit_pd_submit" name="edit_pd_submit" value="Submit" />
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