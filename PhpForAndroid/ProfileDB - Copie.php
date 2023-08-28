<?php

$host="127.0.0.1";
$user="root";
$password="";
$database="DiscoverWorld";

    $connect = mysqli_connect($host, $user, $password, $database);

    if (!$connect)
     {
        die("Connection failed: " . mysqli_connect_error());
     }
     else
     {
        // echo "Connected successfully";
        $user = $_POST["user"];
      $query = "SELECT Name_User,Photo_Profil FROM utilisateur WHERE Email_User = '$user';";

        if (mysqli_query($connect , $query)) {

		$result = mysqli_query($connect , $query);

		while($row =  mysqli_fetch_assoc($result))
		{
		 $output[] = $row;
		}
		print(json_encode($output));
		
     }

}


?>