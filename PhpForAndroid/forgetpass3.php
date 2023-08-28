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
      	    
          
          $newcode=$_POST['newcode'];
          $newcodeconf=$_POST['newcodeconf'];
          $email = $_POST['Email'];

          //session_start();
          //$email=$_SESSION['email2'];

          if($newcode === $newcodeconf)

          {


                   $reqreset="UPDATE Utilisateur SET Password_User='$newcode' WHERE Email_User = '".$email."'";

                   if(mysqli_query($bdcnx,$reqreset))
                   {
                      echo "password updated";
                      
                   }
                   else
                   {
                    echo $reqreset;
                   }
                   
          }

          else
          {
            echo "code non identique";
          }
    

      }
      else
     {
        echo "error in REQUEST METHOD";
     }


?>