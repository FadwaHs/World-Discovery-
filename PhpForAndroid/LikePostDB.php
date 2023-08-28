<?php

$host="127.0.0.1";
$user="root";
$password="";
$database="DiscoverWorld";
$connect=  mysqli_connect($host, $user, $password, $database);

if(mysqli_connect_errno())
{
 die("cannot connect to database field:". mysqli_connect_error());

}else{

	$image_id = $_POST["image_id"];
	$user = $_POST["user"];

	$query = "SELECT * FROM likepost WHERE email_user='$user' AND id_Post='$image_id';";
	$result = mysqli_query($connect,$query);
	if ($result->num_rows==0) {
		$query = "DELETE FROM likepost WHERE email_user='$user' AND id_Post='$image_id';";
		mysqli_query($connect,$query);
		echo '[{"result":"deleted"}]';
	}else {
		$query = "INSERT INTO likepost(id_Post,email_user) VALUES('$image_id','$user');";
		mysqli_query($connect,$query);
		echo '[{"result":"inserted"}]';
	}

	mysqli_free_result($result);
	mysqli_close($connect);
}

?>