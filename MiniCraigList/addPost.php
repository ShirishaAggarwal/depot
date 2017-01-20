<html>
    <head>
        <title>Add Post</title>
        <link href="craiglist.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Add Post</h1>
<?php

//including the db connection php
include_once 'connectDB.php';

//start the session
session_start();
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//get all the values from the add post page
$valuePostTitle = $_POST['postTitle'];
$valuePostBody = $_POST['postBody'];
$valueContactEmail = $_POST['contactEmail'];
$valueContactPhone = $_POST['contactPhone'];

$valcategoriesSelect = $_POST['categoriesSelect'];
$valsubcatsSelect = $_POST['subcatsSelect'];
$valregionsSelect = $_POST['regionsSelect'];
$vallocationsSelect = $_POST['locationsSelect'];

//vairable to check if the values entered are valid or not
$valid = TRUE;

//checking if any of the variables are missing
if(empty($valuePostTitle) or empty($valuePostBody) or empty($valueContactEmail) or empty($valueContactPhone)
        or empty($valcategoriesSelect) or empty($valsubcatsSelect) or empty($valregionsSelect) or empty($vallocationsSelect)){
    echo "<p>Please Enter all the fields!</p>";
    $valid = FALSE;
}
//regex for email
else if(!filter_var($valueContactEmail,FILTER_VALIDATE_EMAIL)){
    echo "Please enter email in proper format!";
    $valid = FALSE;
}
//regex for phone number
else if(preg_match("/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/", $valueContactPhone)){
    echo "Please enter phone number in proper format!";
    $valid = FALSE;
}
//check for post body size
else if(strlen($valuePostBody > 150)){
    echo "There is a limit on the characters entered in the post body!";
    $valid = FALSE;
}
if($valid){
    //insert into posts table
    $postInsertQry = "INSERT INTO POSTS (CATEGORY,SUBCATEGORY,REGION,LOCATION,POSTTITLE,POSTBODY,EMAIL,PHONE,USERID) VALUES "
            ."((SELECT categoryname from category where category_id = '".$valcategoriesSelect."'),"
            . "(SELECT subcategoryname from subcategory where subcategory_id = '".$valsubcatsSelect."'),"
            . "(SELECT regionname from region where region_id = '".$valregionsSelect."'),"
            . "(SELECT locationname from location where location_id = '".$vallocationsSelect."')"
            . ",'".$valuePostTitle."','".$valuePostBody."','".$valueContactEmail."','".$valueContactPhone."','".$_SESSION['user_id']."')";
        
    if(mysqli_query($conn, $postInsertQry)){
        echo "New Post Inserted Successfully!";                
    }
    else{
        echo "An Error occured while inserting this record. <br>". mysqli_error($conn);
    }
}
?>
<!--Links for new post and home page-->
<br><br>
<a href="newPost.php">Add New Post</a>
<br><br>
<a href="index.php">Back To Home Page</a>
    </body>
</html>