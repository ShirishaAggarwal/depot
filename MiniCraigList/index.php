<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Mini CraigList</title>
        <link href="craiglist.css" rel="stylesheet" type="text/css">
    </head>
    <body>        
        <h1>Mini CraigList</h1>
        
        <?php
        include_once 'connectDB.php';
        //unset($_SESSION);
        session_start();
        if(isset($_SESSION['user_name'])){
            echo "<h2>Welcome ".$_SESSION['user_name']."</h2>";
            echo '<a href="viewMyPosts.php">View My Posts</a><br>';
        }
        else{
            echo '<a href="login.html">[Login]</a>&nbsp;&nbsp;';
            echo '<a href="register.html">[New User? Click here to register.]</a><br>';
        }
        ?>                
        <br>
        <a href="newPost.php">Add New Post</a>
        <br><br>    
        
        <!--
        <label for="search">Search Mini Craiglist:</label><br><br>
        <input type="text" name="search" id="search" value="">	
        <input type="submit" name="submit" value="Submit" />&nbsp;&nbsp;
        <input type="reset" value="Reset" />
        <br><br>-->
        
        <!--div's for index page with all categories and sub categories-->
        <div id="container">
            <div id="left">
                <h3>Housing</h3>
                <?php
                $subcategoriesQry = "SELECT subcategory_id,subcategoryname FROM subcategory where category_id = '4' order by 1 asc";    
                $subcategories = mysqli_query($conn,$subcategoriesQry) or die(mysqli_error($conn));    
                
                if(!empty($subcategories)){
                    while ($row = mysqli_fetch_array($subcategories)){
                        $param1 = $row['subcategory_id'];
                        echo "<a href='viewPost.php?category=$param1'>".$row['subcategoryname']."</a><br>";
                    }
                }
                ?>                
            </div>
            <div id="right">
                <h3>Jobs</h3>
                <?php
                $subcategoriesQry1 = "SELECT subcategory_id,subcategoryname FROM subcategory where category_id = '5' order by 1 asc";    
                $subcategories1 = mysqli_query($conn,$subcategoriesQry1) or die(mysqli_error($conn));    
    
                if(!empty($subcategories1)){
                    while ($row = mysqli_fetch_array($subcategories1)){
                        $param1 = $row['subcategory_id'];
                        echo "<a href='viewPost.php?category=$param1'>".$row['subcategoryname']."</a><br>";
                    }
                }
                ?>  
            </div>
            <div id="center">
                <h3>For Sale</h3>
                <?php
                $subcategoriesQry2 = "SELECT subcategory_id,subcategoryname FROM subcategory where category_id = '6' order by 1 asc";    
                $subcategories2 = mysqli_query($conn,$subcategoriesQry2) or die(mysqli_error($conn));    
    
                if(!empty($subcategories2)){                    
                    while ($row = mysqli_fetch_array($subcategories2)){
                        $param1 = $row['subcategory_id'];
                        echo "<a href='viewPost.php?category=$param1'>".$row['subcategoryname']."</a><br>";
                    }
                }
                ?>  
            </div>
        </div>
        <br><br>
        <div id="container">
            <div id="left">
                <h3>Regions/Countries</h3>
                <?php
                $regionsQry = "SELECT region_id,regionname FROM region order by 1 asc";    
                $regions = mysqli_query($conn,$regionsQry) or die(mysqli_error($conn));    
    
                if(!empty($regions)){
                    
                    while ($row = mysqli_fetch_array($regions)){
                        $param1 = $row['region_id'];
                        echo "<a href='viewPost.php?region=$param1'>".$row['regionname']."</a><br>";
                    }
                }
                ?>  
            </div>
            <div id="right">
                <h3>Cities/Locations</h3>
                <?php
                $locationsQry = "SELECT location_id,locationname FROM location order by 1 asc";    
                $locations = mysqli_query($conn,$locationsQry) or die(mysqli_error($conn));    
    
                if(!empty($locations)){
                    
                    while ($row = mysqli_fetch_array($locations)){
                        $param1 = $row['location_id'];
                        echo "<a href='viewPost.php?location=$param1'>".$row['locationname']."</a><br>";
                    }
                }
                ?>  
            </div> 
            <div id="center">
                <h3>Miscellanous</h3>
                <?php
                $subcategoriesQry3 = "SELECT subcategory_id,subcategoryname FROM subcategory where category_id = '8' order by 1 asc";    
                $subcategories3 = mysqli_query($conn,$subcategoriesQry3) or die(mysqli_error($conn));    
    
                if(!empty($subcategories3)){                    
                    while ($row = mysqli_fetch_array($subcategories3)){
                        $param1 = $row['subcategory_id'];
                        echo "<a href='viewPost.php?category=$param1'>".$row['subcategoryname']."</a><br>";
                    }
                }
                ?> 
            </div> 
        </div>
        
        <!--Links for terms and conditions page and about us page-->
        
        <br><br>
        <a href="tandc.html">Terms and Conditions</a>
        
        <br><br>
        <a href="about.html">About us</a>            
    </body>
</html>
