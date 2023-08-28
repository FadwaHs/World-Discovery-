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
	$userName=$_POST["user"];
	

	$query = "SELECT Photo_Post FROM post WHERE Email_User = '$userName';";

	if(mysqli_query($connect , $query)){
		$result = mysqli_query($connect , $query);
		while($row =  mysqli_fetch_assoc($result))
		{
		 $output[] = $row;
		}
		print(json_encode($output));
		//echo "[{\"Photo_Post\":\"user_04032020_120620.png\"}]";//test
	}else{
		echo "error in query";
	}



	// 4 clear
	mysqli_free_result($result);
	//5- close connection
	mysqli_close($connect);
	//echo "is connected";
}


?>