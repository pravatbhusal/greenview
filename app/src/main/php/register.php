<?php
include("dbconnection.php");

//variables to receive from Java
$Name = $_POST["Name"];
$userID = $_POST["userID"];

        //check if the userID has already been used. if it is, then don't allow them to use it!
	$query = "SELECT * FROM users WHERE userID='$userID'";
	$result = mysqli_query($link, $query);
    	if(mysqli_num_rows($result) > 0) {
             exit;
    	}

	//this query inserts the values into the club_info table
	$query = "INSERT INTO users (Name, userID) VALUES('$Name', '$userID')";
	mysqli_query($link, $query);

	exit;
?>