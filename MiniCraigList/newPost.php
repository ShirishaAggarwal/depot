<html>
    <head>
        <title>Add New Post</title>
        <link href="craiglist.css" rel="stylesheet" type="text/css">            
    
<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    //including the db connection php
    include_once 'connectDB.php';
    
    //write a query and get all the categories from the database
    $categoriesQry = "SELECT category_id,categoryname FROM category order by 1 asc";    
    $categories = mysqli_query($conn,$categoriesQry) or die(mysqli_error($conn));    

    if(!empty($categories)){        
        while ($row = mysqli_fetch_array($categories)){
            $categoriesArr[] = array("id"=>$row['category_id'],"val"=>$row['categoryname']);
        }

    }        

    //write a query and get all the sub-categories from the database
    $subcategoriesQry = "SELECT subcategory_id,category_id,subcategoryname FROM subcategory order by 1 asc";    
    $subcategories = mysqli_query($conn,$subcategoriesQry) or die(mysqli_error($conn));    

    if(!empty($subcategories)){    
        while ($row = mysqli_fetch_array($subcategories)){
            $subcategoriesArr[$row['category_id']][] = array("id"=>$row['subcategory_id'],"val"=>$row['subcategoryname']);
        }    
    }
    
    //write a query and get all the regions from the database
    $regionsQry = "SELECT region_id,regionname FROM region order by 1 asc";    
    $regions = mysqli_query($conn,$regionsQry) or die(mysqli_error($conn));    
    
    if(!empty($regions)){        
        while ($row = mysqli_fetch_array($regions)){
            $regionsArr[] = array("id"=>$row['region_id'],"val"=>$row['regionname']);
        }        
    }
    
    //write a query and get all the locations from the database
    $locationsQry = "SELECT location_id,region_id,locationname FROM location order by 1 asc";    
    $locations = mysqli_query($conn,$locationsQry) or die(mysqli_error($conn));    
    
    if(!empty($locations)){        
        while ($row = mysqli_fetch_array($locations)){
            $locationsArr[$row['region_id']][] = array("id"=>$row['location_id'],"val"=>$row['locationname']);
        }        
    }

    
    $jsonCategories = json_encode($categoriesArr);
    $jsonSubCategories = json_encode($subcategoriesArr);
    $jsonRegions = json_encode($regionsArr);
    $jsonLocations = json_encode($locationsArr);

?>
    
    </head>
    
    <script type="text/javascript">
        <?php
        echo "var categories = $jsonCategories; \n";        
        echo "var subCategories = $jsonSubCategories; \n";            
        echo "var regions = $jsonRegions; \n";
        echo "var locations = $jsonLocations; \n";
        ?>            
        function loadCategories(){            
            var select = document.getElementById("categoriesSelect");            
            select.onchange = updateSubCats;
            for(var i = 0; i < categories.length; i++){
                select.options[i] = new Option(categories[i].val,categories[i].id);          
            }
            loadRegions();
        }
        function updateSubCats(){            
            var catid = this.value;
            var subcatSelect = document.getElementById("subcatsSelect");
            subcatSelect.options.length = 0; //delete all options if any present
            for(var i = 0; i < subCategories[catid].length; i++){
              subcatSelect.options[i] = new Option(subCategories[catid][i].val,subCategories[catid][i].id);
            }
        }
        function loadRegions(){
            var regionSelect = document.getElementById("regionsSelect");
            regionSelect.onchange = updateLocations;
            for(var i=0;i < regions.length; i++){
                regionSelect.options[i] = new Option(regions[i].val,regions[i].id);
            }
        }
        function updateLocations(){
            var regionId = this.value;
            var locationSelect = document.getElementById("locationsSelect");
            locationSelect.options.length = 0;//delete all options if any present
            for (var i=0;i < locations[regionId].length;i++){
                locationSelect.options[i] = new Option(locations[regionId][i].val,locations[regionId][i].id);
            }
        }
    </script>
    
    <body onload='loadCategories()'>
        <h1>Add a new post here</h1>
        <form id="addpost" action="addPost.php" method="post">
        <label for="postTitle">Posting Title*</label>
        <input type="text" id="postTitle" name="postTitle">
        <br><br>
        <label for="postBody">Post Body*</label>
        <textarea id="postBody" name="postBody" rows="5" cols="100" placeholder="Enter a message in less than 150 characters"></textarea>
        <br><br>
        <label for="contactEmail">Contact Email*</label>
        <input type="email" id="contactEmail" name="contactEmail" placeholder="xxx@xxx.xxx">
        <br><br>
        <label for="contactPhone">Contact Number*</label>
        <input type="text" id="contactPhone" name="contactPhone" placeholder="111-111-1111">
        <br><br>    
        <label for="categoriesSelect">Categories*</label>
        <select id='categoriesSelect' name='categoriesSelect'>
        </select>
        <br><br>
        <label for="subcatsSelect">Sub Categories*</label>
        <select id='subcatsSelect' name='subcatsSelect'>
        </select>
        <br><br>
        <label for="regionsSelect">Regions/Countries*</label>
        <select id='regionsSelect' name='regionsSelect'>
        </select>
        <br><br>
        <label for="locationsSelect">Locations/Cities*</label>
        <select id='locationsSelect' name='locationsSelect'>
        </select>
            
        <br><br>
        <input type="submit" value="Add Post">

    </form>
    
    <br>
    <a href="index.php">Back to home page</a>
    </body>
</html>