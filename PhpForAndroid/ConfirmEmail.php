<?php


    $host="127.0.0.1";
	$user="root";
	$password="";
	$database="DiscoverWorld";

    $bdcnx = mysqli_connect($host, $user, $password, $database);

    if (!$bdcnx)
     {
        die("Connection failed: " . mysqli_connect_error());
     }
     else
     {
         //echo "Connected successfully";
     }


    if($_SERVER['REQUEST_METHOD']=='POST')
     {
            $code=$_POST['code'];
            $Email=$_POST['Email'];

     
         $query="SELECT `Vkeys` FROM `Utilisateur` WHERE Email_User='$Email'";

         $result=mysqli_query($bdcnx,$query);
         $Vkeys= $result->fetch_assoc();

         if($Vkeys['Vkeys'] === $code)
         {
            $queryUP="UPDATE `Utilisateur` SET `Cheked`='oui' WHERE Email_User='$Email'";
            $resultUP=mysqli_query($bdcnx,$queryUP);
            echo "email valide";
         } 
         else
         { 
              echo "Invalid Email";
         }


    }
    else
    {
        echo "error in REQUEST METHOD";
    }

?>  