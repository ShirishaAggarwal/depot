<?php
	
	$firstName = '';
	$lastName  = '';
	$nickName = '';
	$email  = '';
	$phone = '';
	
	
	if(isset($_POST['submit'])) {
		
		$firstName = $_POST['firstName'];
		$lastName = $_POST['lastName'];
		$nickName = $_POST['nickName'];
		$email = $_POST['email'];
		$phone = $_POST['phone'];

		//Check if First Name is not empty
		if(empty($firstName)){
			echo "Please Enter First Name."	;		
		}
		elseif(empty($lastName)){
			echo "Please Enter Last Name.";
		}
		elseif(empty($email)){
			echo "Please Enter a Valid Email Id.";
		}
		elseif(empty($phone)){
			echo "Please Enter a Valid Phone Number.";
		}
		elseif(!empty($nickName)){
			echo "<br>ThankYou $nickName for making an appoitment with us! You will receive an email with appointment details.See you :)";
		}
		else{
			echo "<br>ThankYou $firstName $lastName for making an appoitment with us! You will receive an email with appointment details.See you :)";
		}
	}		
	
	if(isset($_POST['clear'])) {
		echo "clear now";
	}
?>