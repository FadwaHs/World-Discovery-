<?php

$host="127.0.0.1";
$user="root";
$password="";
$database="DiscoverWorld";

    $connect = mysqli_connect($host, $usr, $password, $database);

    if (!$connect)
     {
        die("Connection failed: " . mysqli_connect_error());
     }
     else
     {
        // echo "Connected successfully";
        $search = $_POST['search'];
     	$query = "SELECT Titre_Post FROM post WHERE Titre_Post = '$search';";
     	$query = "SELECT DISTINCT post.id_Post,Photo_Post,Nom_Categorie,Description_Post,Name_User,Photo_Profil,post.Location_User,post.Email_User,post.date FROM post,utilisateur WHERE post.Email_User = utilisateur.Email_User AND City_Post = '$search' ORDER BY rand() LIMIT 6";

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