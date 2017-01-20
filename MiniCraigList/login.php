<html>
    <head>
        <title>Login</title>
        <link href="craiglist.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <?php

    /* 
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    //including the db connection php
    include 'connectDB.php';

    //start the session
    session_start();

    //Lets check if the user entered all the required values
    if(empty($_POST['username']) OR empty($_POST['password'])){
        echo "Please enter all the required fields!";
    }
    else{
        //get the values of username and password from the superglobal post
        $username = $_POST['username'];
        $password = md5($_POST['password']);
        
        //validate username and password with regular expressions

        //check if the user exists in the database
        $command = "SELECT id, username from login where username = '".$username."' AND password ='".$password."'";        
        $loginQuery = mysqli_query($conn,$command) or die(mysqli_error($conn));        
        $row = mysqli_fetch_row($loginQuery);        
        
        //if user does not exists, display a message
        if(empty($row[0])){
            echo "No user found with the username ".$username;
        }
        //if user exists, take him to the user panel
        else{
            $_SESSION['user_id'] = $row[0]; 
            $_SESSION['user_name'] = $row[1];
            header('location:index.php');
        }
    }
    ?>
        <br><br>
        <a href="index.php">Back to home page</a>
    </body>
</html>