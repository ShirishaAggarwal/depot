<!DOCTYPE html>
<html>
<head>
	<title>Appointment</title>
	<meta charset="UTF-8">
	<link href="myStyle.css" rel="stylesheet" type="text/css">	
</head>
<body>
	<h1>Appointment</h1>
	<?php
		include 'inav.php';
		include 'validate.php';	
	?>		
<form id="survey" method="POST" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']);?>">
	<br>
	<br>
	<label for="firstName">First Name*: </label>
	<input type="text" name="firstName" id="firstName" value="<?php echo $firstName ?>" >
	<br><br>
	<label for="lastName">Last Name*: </label>
	<input type="text" name="lastName" id="lastName" value="<?php echo $lastName ?>" >		
	<br><br>
	<label for="nickName">Kid's Name: </label>
	<input type="text" name="nickName" id="nickName" value="<?php echo $nickName ?>">
	
	<br>
	<br>
	<fieldset>
		<legend>Contact Info</legend>
		
		<p id="disclaimer" class="disclaimer">You can use a default email..(nobody@ucsc-extension.edu)</p>				
		</br>		
		<label for="email">E-mail*: </label>
		<input type="email" name="email" id="email" value="<?php echo $email ?>" >
		
		<p id="disclaimer" class="disclaimer">You can use any default phone number..(888-888-8888)</p>		
		</br>
		<label for="phone">Phone*: </label>
		<input type="tel" name="phone" id="phone" value="<?php echo $phone ?>" >

		<br>
		
		<p>	
			<input type="reset" name="clear" value="Clear Form" onclick="clearForm()"/>&nbsp;&nbsp;
			<input type="submit" name="submit" value="Send Form" />
		</p>

	</fieldset>
</form>
</body>
</html>

<br><br>
<?php		
	include 'footer.php';
?>	