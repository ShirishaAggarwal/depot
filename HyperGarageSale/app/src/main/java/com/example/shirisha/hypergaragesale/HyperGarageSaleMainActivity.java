package com.example.shirisha.hypergaragesale;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class HyperGarageSaleMainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private ContentValues values;

    private EditText titleText;
    private EditText descText;
    private EditText priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyper_garage_sale_main);

        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        titleText = (EditText)findViewById(R.id.textView_title);
        descText = (EditText)findViewById(R.id.textView_desc);
        priceText = (EditText)findViewById(R.id.textView_price);

        // Gets the data repository in write mode
        PostsDBHelper mDbHelper = new PostsDBHelper(this);
        db = mDbHelper.getWritableDatabase();
    }

   /* public void newPostAdded(View v){
        addPost();
        //showSnackBar();
    }*/

    private void showSnackBar(View v){
        //Snackbar.make(findViewById(R.id.myCoordinatorLayout),R.string.new_post_snackbar,Snackbar.LENGTH_LONG).show();
        if(v == null){
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),R.string.new_post_snackbar,Snackbar.LENGTH_LONG).show();
        }
        else{
            Snackbar.make(v,R.string.new_post_snackbar,Snackbar.LENGTH_LONG).show();
        }
    }

    private void addPost() {
        // Create a new map of values, where column names are the keys
        values = new ContentValues();
        values.put(Posts.PostEntry.COLUMN_NAME_TITLE, titleText.getText().toString());
        values.put(Posts.PostEntry.COLUMN_NAME_DESCRIPTION, descText.getText().toString());
        values.put(Posts.PostEntry.COLUMN_NAME_PRICE, priceText.getText().toString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                Posts.PostEntry.TABLE_NAME,
                null,
                values);
        System.out.println("Added new post");
        // Done adding new entry into database, navigate user back to browsing screen
        startActivity(new Intent(this, BrowsePostsActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hypergaragesale_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.action_new_post){
            showSnackBar(null);
            addPost();
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
