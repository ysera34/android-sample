package com.android.sample.sqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.sample.sqlite.database.CategoryDbSchema.CategoryTable;

/**
 * Created by yoon on 2017. 7. 4..
 */

public class CategoryBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "categoryBase.db";
    private static final String CREATE_TABLE_CATEGORIES = "create table " + CategoryTable.Name + "(" +
            " _in integer primary key autoincrement, " +
            CategoryTable.Cols.ID + ", " +
            CategoryTable.Cols.NAME + ", " +
            CategoryTable.Cols.NUMBER + ")";

//    public CategoryBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public CategoryBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoryTable.Name);

        onCreate(db);
    }


}
