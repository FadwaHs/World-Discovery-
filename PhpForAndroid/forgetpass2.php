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
      	    
          
          $code2=$_POST['code2'];
          $email=$_POST['Email2'];

          $req="SELECT code_forget FROM ForgetPass WHERE Email_User ='$email'";

          $res=mysqli_query($bdcnx,$req);

          $codebd= $res->fetch_assoc();

         if($codebd['code_forget'] === $code2)
         {
                echo "code valide";
         } 
         else
         { 
              echo "Invalid code";
         }

      }
      else
     {
        echo "error in REQUEST METHOD";
     }


?>