<?php
include("dbconnection.php");

$userID = $_POST['userID'];

$query = "SELECT * FROM users WHERE userID='$userID'";
$select_user = mysqli_query($link, $query);    

if($row = mysqli_fetch_assoc($select_user)){
    $score = $row['score'];    
    $score+=5;   

    $query = "UPDATE users SET score = '$score' WHERE userID = '$userID'";

    mysqli_query($link, $query);       
}
?>   