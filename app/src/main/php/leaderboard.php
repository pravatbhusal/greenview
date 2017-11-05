<?php
include("dbconnection.php");

$query = "SELECT  * FROM users ORDER BY `score` DESC";
$select_user = mysqli_query($link, $query);
$counter=0;
while(($row = mysqli_fetch_assoc($select_user)) && ($counter<5)){
    $realname = $row['Name'];
    $score = $row['score'];
    echo $realname."=".$score."&";
    $counter++;
}
?>