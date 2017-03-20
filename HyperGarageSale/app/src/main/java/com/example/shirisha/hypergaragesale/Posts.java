package com.example.shirisha.hypergaragesale;

import android.provider.BaseColumns;

/**
 * Created by Shirisha on 3/18/2017.
 */

public class Posts {
    public Posts(){}

    public static abstract class PostEntry implements BaseColumns{
        public static final String TABLE_NAME = "posts";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PRICE = "price";
    }
}
