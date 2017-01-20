<html>
    <head>
        <title>Register</title>
        <link href="myCss.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <?php

    /* 
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    //including the db connection php
    include_once 'connectDB.php';
    
    //start the session
    session_start();
    
    //check if user entered all the required fields
    if(empty($_POST['newuser']) OR empty($_POST['newpassword']) OR empty($_POST['newemail'])){
        echo "Please fill in all the fields!";
    }
    //user entered all the fields
    else{
        //get them from the superglobal post
        $uname = $_POST['newuser'];
        $pass = $_POST['newpassword'];
        $email = $_POST['newemail'];
        
        //validate the user entries using regular expressions
        
        $selectCommand = "SELECT count(id) FROM login where username = '".$uname."' and email = '".$email."'";
        $insertCommand = "INSERT INTO LOGIN (username,password,email) VALUES('".$uname."','".md5($pass)."','".$email."')";
        
        //check if the user already exists in the database
        $selQueryRes = mysqli_query($conn,$selectCommand) or die(mysqli_error($conn));
        $row = mysqli_fetch_row($selQueryRes);
                
        //If user does not exists,we create a new user
        if(empty($row[0])){
            if(mysqli_query($conn, $insertCommand)){
                echo "New Record Inserted Successfully! Please Login to view your information.";                
            }
            else{
                echo "An Error occured while inserting this record. <br>". mysqli_error($conn);
            }
        }
        //else give a message that the user already exists and ask user to login
        else{
            echo "User Already exists! Please Login to view your information.";
        }
    }

    ?>
        <br><br>
        <a href="index.php">Back to home page</a>
    </body>
</html>