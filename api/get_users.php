<?php
header("Content-type: application/json; charset=utf-8");

$server_name = "localhost";
$db_user = "id10547876_hjgr";
$db_password = "password";
$db_name = "id10547876_pm2019";

$conn = new mysqli($server_name, $db_user, $db_password, $db_name);

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT * FROM users";
$result = $conn->query($sql);
$users = array();

while ($row = $result->fetch_assoc()) {
    array_push($users, $row);
}

$conn->close();
echo json_encode($users);

?>