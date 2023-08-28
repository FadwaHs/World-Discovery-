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
	$userEmail='user@gmail.com';//$_POST["user"];

	$query = "SELECT DISTINCT save.id_Post,Photo_Post,Nom_Categorie,Description_Post,Photo_Profil,Name_User,post.date_ FROM utilisateur,post,save WHERE save.Email_User='$userEmail' AND save.Email_User=utilisateur.Email_User AND save.id_Post=post.id_Post;";

	if (mysqli_query($connect,$query)) {
		$result = mysqli_query($connect,$query);

		while ($row=mysqli_fetch_assoc($result)) {
			$output[] = $row;
		}

		print_r(json_encode($output));
		mysqli_free_result($result);
	}
	
	mysqli_close($connect);

}



?>

