<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//connecting to the database lamp_final_project
$conn = new mysqli("localhost","root","","lamp_final_project");

if($conn->connect_errno){
    die("MySQL Connection Failed!! The error is ,".$conn->connect_error);
}
/*else{
    //create login table
    $createLogin = "CREATE TABLE LOGIN ("
            . "ID MEDIUMINT NOT NULL AUTO_INCREMENT,"
            . "USERNAME VARCHAR(255),"
            . "PASSWORD VARCHAR(255),"
            . "EMAIL VARCHAR(255),PRIMARY KEY (ID)"
            . ")";
    echo "Create table query is :".$createLogin;
    if(mysqli_query($conn,$createLogin))
    {
        echo "Table created successfully!";
    }
    else{
        echo "An error occured while creating table,". mysqli_error($conn);
    }
    
}
else{
    //create posts table
    $createLogin = "CREATE TABLE POSTS ("
            . "POSTID MEDIUMINT NOT NULL AUTO_INCREMENT,"
            //. "USERID MEDIUMINT NOT NULL,"
            . "CATEGORY VARCHAR(50),"
            . "SUBCATEGORY VARCHAR(50),"
            . "REGION VARCHAR(50),"
            . "LOCATION VARCHAR(50),"
            . "POSTTITLE VARCHAR(255),"
            . "POSTBODY VARCHAR(255),"
            . "EMAIL VARCHAR(50),"
            . "PHONE VARCHAR(50),"
            . "USERID VARCHAR(50),"
            . "PRIMARY KEY (POSTID)"
            . ")";
    echo "Create table query is :".$createLogin;
    if(mysqli_query($conn,$createLogin))
    {
        echo "Table created successfully!";
    }
    else{
        echo "An error occured while creating table,". mysqli_error($conn);
    }
    
}
*/
//print "Successfully connected to database ,".$conn->host_info;

//$conn->close();

?>