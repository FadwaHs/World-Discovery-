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
	$image=$_POST["image"];
	
	$img_name = $userName."_".date('dmyy_His',time());

	$date1 = date('d/m/yy',time());
	$date2 = date('H:i',time());
	$date3 = $date1." at ".$date2;

	$bin = base64_decode($image);
	$img = imagecreatefromstring($bin);
	if (!$img) {
		die('Error');
	}
	$img_file = "Image/$img_name.png";
	imagepng($img,$img_file,0);
	$url="$img_name.png";

	$query = "UPDATE utilisateur SET Photo_Profil='$url' WHERE Email_User = '$userName';";

	if(mysqli_query($connect , $query)){
	echo "[{\"result\":\"YES\"}]";
	}else{
		echo "[{\"result\":\"NO\"}]";
	}



	// 4 clear
	mysqli_free_result($result);
	//5- close connection
	mysqli_close($connect);
	//echo "is connected";
}

/**   Email_User       varchar(50) not null,
   Nom_Categorie varchar(50) not null,
   Titre_Post      varchar(50),
   Description_Post         varchar(10000),
   Location_User       varchar(50),
   Photo_Post     Blob,
   City_Post   varchar(50),*/

?>

