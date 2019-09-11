<?php

$server_name = "localhost";
$db_user = "id10547876_hjgr";
$db_password = "password";
$db_name = "id10547876_pm2019";

$name = $_GET["name"];
$user = $_GET["user"];
$password = $_GET["password"];

$conn = new mysqli($server_name, $db_user, $db_password, $db_name);

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
} 

$sql = "INSERT INTO users (name, user, password) VALUES ('$name', '$user', '$password')";

if ($conn->query($sql) === TRUE) {
  echo("New record created successfully");
} else {
  echo("Error");
}

$conn->close();

?>