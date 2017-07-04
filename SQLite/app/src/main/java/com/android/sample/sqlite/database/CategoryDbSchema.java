package com.android.sample.sqlite.database;

/**
 * Created by yoon on 2017. 7. 4..
 */

public class CategoryDbSchema {

    public static final class CategoryTable {
        public static final String Name = "categories";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NUMBER = "number";
            public static final String NAME = "name";
        }
    }
}
