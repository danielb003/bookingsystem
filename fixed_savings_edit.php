<!DOCTYPE html>
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
			<div id="edit_fs_title">
				<p>Edit Fixed Savings</p>
			</div>
			<div id="edit_fs_content">
				<p id="edit_fs_intro">The amount you submit over-rides your current fixed savings, it does not get added on top.</p>
				<form action="process_savings_edit.php" method="post">
					<label id="edit_fs_label">Enter new fixed savings</label>
					<input type="text" id="edit_fs_input" name="edit_fs_input" placeholder="Enter amount" pattern="[0-9.]{1,11}" required />
					<input type="submit" id="edit_fs_submit" name="edit_fs_submit" value="Change" />
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