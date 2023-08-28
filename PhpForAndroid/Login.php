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
        // echo "Connected successfully";
     }

     $Email_User =$_POST['Email_User'];
     $Password_User=$_POST['Password_User'];

     $query = "SELECT `Email_User`, `Password_User` FROM `Utilisateur` WHERE Email_User ='$Email_User' and Password_User ='$Password_User'and Cheked ='oui'";
     $result = mysqli_query($bdcnx,$query);

     if(mysqli_num_rows($result) > 0)
     {
        echo "login successfully";
     }
     else
     {
        echo "login failed";
     }