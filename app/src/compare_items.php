<?php
include("dbconnection.php");

$item0 = $_POST["item0"];
$item1 = $_POST["item1"];
$item2 = $_POST["item2"];
$item3 = $_POST["item3"];
$item4 = $_POST["item4"];
$item5 = $_POST["item5"];
$item6 = $_POST["item6"];
$item7 = $_POST["item7"];
$item8 = $_POST["item8"];
$item9 = $_POST["item9"];
$item10 = $_POST["item10"];
$item11 = $_POST["item11"];
$item12 = $_POST["item12"];
$item13 = $_POST["item13"];
$item14 = $_POST["item14"];
$item15 = $_POST["item15"];
$item16 = $_POST["item16"];
$item17 = $_POST["item17"];
$item18 = $_POST["item18"];
$item19 = $_POST["item19"];

        
$query = "SELECT * FROM recyclable_items WHERE item = '$item0'";
if($result0 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result0)){
    $item_name0 = $row["item"];
    echo $item_name0;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item1'";
if($result1 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result1)){
    $item_name1 = $row["item"];
    echo $item_name1;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item2'";
if($result2 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result2)){
    $item_name2 = $row["item"];
    echo $item_name2;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item3'";
if($result3 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result3)){
    $item_name3 = $row["item"];
    echo $item_name3;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item4'";
if($result4 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result4)){
    $item_name4 = $row["item"];
    echo $item_name4;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item5'";
if($result5 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result5)){
    $item_name5 = $row["item"];
    echo $item_name5;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item6'";
if($result6 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result6)){
    $item_name6 = $row["item"];
    echo $item_name6;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item7'";
if($result7 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result7)){
    $item_name7 = $row["item"];
    echo $item_name7;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item8'";
if($result8 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result8)){
    $item_name8 = $row["item"];
    echo $item_name8;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item9'";
if($result9 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result9)){
    $item_name9 = $row["item"];
    echo $item_name9;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item10'";
if($result10 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result10)){
    $item_name10 = $row["item"];
    echo $item_name10;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item11'";
if($result11 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result11)){
    $item_name11 = $row["item"];
    echo $item_name11;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item2'";
if($result12 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result12)){
    $item_name12 = $row["item"];
    echo $item_name12;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item13'";
if($result13 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result13)){
    $item_name13 = $row["item"];
    echo $item_name13;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item14'";
if($result14 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result14)){
    $item_name14 = $row["item"];
    echo $item_name14;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item15'";
if($result15 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result15)){
    $item_name15 = $row["item"];
    echo $item_name15;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item16'";
if($result16 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result16)){
    $item_name16 = $row["item"];
    echo $item_name16;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item17'";
if($result17 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result17)){
    $item_name17 = $row["item"];
    echo $item_name17;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item18'";
if($result18 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result18)){
    $item_name18 = $row["item"];
    echo $item_name18;
    exit;
    }}

$query = "SELECT * FROM recyclable_items WHERE item = '$item19'";
if($result19 = mysqli_query($link, $query)){
while($row = mysqli_fetch_assoc($result19)){
    $item_name19 = $row["item"];
    echo $item_name19;
    exit;
    }}
      
echo "not recyclable";
exit;
?> 