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

	$categ = $_POST['categ'];

	$query = "SELECT DISTINCT post.id_Post,Photo_Post,Nom_Categorie,Titre_Post,Name_User,Photo_Profil,post.Location_User,post.Email_User,post.date_ FROM post,utilisateur WHERE post.Email_User = utilisateur.Email_User AND post.Nom_Categorie='$categ'";

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