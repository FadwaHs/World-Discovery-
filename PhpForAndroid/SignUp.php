<?php

    $servername = "localhost";
    $username = "root";
    $password = "fadwa";
    $db = "DiscoverWorld";

    $bdcnx = mysqli_connect($servername, $username, $password, $db);

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

     	$name=$_POST['name'];
     	$location=$_POST['location'];
     	$email=$_POST['email'];
     	$password= $_POST['password'];
        $date = date("Y/m/d");
        $vkey = md5(time() . $name);

        $PK="SELECT * FROM Utilisateur  WHERE Email_User=$email";
        $resultPK = mysqli_query($bdcnx, $PK);

          if ( mysqli_num_rows($resultPK) == 0)
          {
                  

         $query="INSERT INTO Utilisateur (Email_User, Name_User, Location_User, Photo_Profil, Password_User, Date_Creation, Vkeys) VALUES ('$email', '$name', '$location', '', '$password', '$date', '$vkey')";

         if(mysqli_query($bdcnx, $query))
         {
            echo "registration successfully";

            //send Email
            $to = $email;
            $subject = "Email Verification";
            $message = "<a href ='http://localhost/PhpForAndroid/verify.php?vkey=$vkey'>Register Account</a>";
            $headers = "From : Fadwa hsissou\r\n";
            $headers .= "MIME-Version: 1.0" . "\r\n";
            $headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";
            
            mail($to, $subject, $message, $headers);
        
            
         }
         else
         {
            echo "Email Already exists";
         }

          }
          else
          {
            echo "Email Already exists";
          }

     }

     else
     {
        echo "error in REQUEST METHOD";
     }

     

?>