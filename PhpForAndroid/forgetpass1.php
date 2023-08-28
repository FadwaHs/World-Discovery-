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

      	  session_start();
      	  
      	  $email2=$_POST['email2'];

          $_SESSION['email2']=$email2;

      	  $codesent = md5(time() . $email2);
      	  

      	 $query="SELECT Email_User FROM Utilisateur  WHERE Email_User='$email2'";
         $emailuser= mysqli_query($bdcnx,$query);

         if(mysqli_num_rows($emailuser) == 1)
         {
              $reqinsert= "INSERT INTO ForgetPass (Email_User, code_forget) VALUES ('$email2','$codesent')";

               if(mysqli_query($bdcnx,$reqinsert))
               {
               	 echo "account exist";
                 
                 //send mail
               	 $to = $email2;
                 $subject = "Forget password code";
                 $message = $codesent;
                 $headers = "From : Fadwa hsissou\r\n";
                 $headers .= "MIME-Version: 1.0" . "\r\n";
                 $headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";
            
                 mail($to, $subject, $message, $headers);
               }
               else
               {
               	echo "Error";
               }

         }
         else
         {
         	echo "Account Not Exist";
         }

      }
      else
     {
        echo "error in REQUEST METHOD";
     }


?>