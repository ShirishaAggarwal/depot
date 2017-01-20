<html>
    <head>
        <title>View Post</title>
        <link href="craiglist.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>View Posts</h1>
        <table>
            <tr>
                <th>Post Title</th>
                <th>Post Body</th>
                <th>Contact Email</th>
                <th>Contact Phone</th>
                <th>Category</th>
                <th>Sub Category</th>
                <th>Region/Country</th>
                <th>Location/City</th>
                <th>Posted By</th>
            </tr>
<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
include_once 'connectDB.php';

session_start();

$viewMyPostsQry = "SELECT * FROM POSTS WHERE USERID = '".$_SESSION['user_id']."'";
$posts = mysqli_query($conn,$viewMyPostsQry) or die(mysqli_error($conn)); 
        
    if(!empty($posts)){                
        while ($row = mysqli_fetch_array($posts)){         
         echo "<tr>";   
         echo "<td>".$row['POSTTITLE']."</td><td>".$row['POSTBODY']."</td><td>".$row['EMAIL']."</td><td>".$row['PHONE']."</td>"
                 . "<td>".$row['CATEGORY']."</td><td>".$row['SUBCATEGORY']."</td><td>".$row['REGION']."</td><td>".$row['LOCATION']."</td>"
                 . "<td>".$_SESSION['user_name']."</td>";
         echo "</tr>";
        }        
    } 
    if(mysqli_num_rows($posts) == 0){
        
        echo "<p>No Posts available yet!</p><br>";
    }

?>
</table>
<br><br>
<a href="index.php">Back To Home Page</a>
    </body>
</html>