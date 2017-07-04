package com.android.sample.sqlite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.sample.sqlite.database.CategoryBaseHelper;
import com.android.sample.sqlite.database.CategoryCursorWrapper;
import com.android.sample.sqlite.database.CategoryDbSchema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoon on 2017. 7. 4..
 */

public class CategoryStorage {

    private static CategoryStorage sCategoryStorage;
//    private List<Category> mCategories;

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public static CategoryStorage get(Context context) {
        if (sCategoryStorage == null) {
            sCategoryStorage = new CategoryStorage(context);
        }
        return sCategoryStorage;
    }

    private CategoryStorage(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new CategoryBaseHelper(mContext).getWritableDatabase();
//        mCategories = new ArrayList<>();
    }

    public List<Category> getCategories() {
//        return mCategories;
//        return new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        CategoryCursorWrapper cursorWrapper = queryCategories(null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                categories.add(cursorWrapper.getCategory());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return categories;
    }

    public Category getCategory(int id) {
        CategoryCursorWrapper cursorWrapper = queryCategories(
                CategoryDbSchema.CategoryTable.Cols.ID + " =?",
                new String[]{String.valueOf(id)}
        );

        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getCategory();
        } finally {
            cursorWrapper.close();
        }
    }

    private static ContentValues getContentValues(Category category) {
        ContentValues values = new ContentValues();
        values.put(CategoryDbSchema.CategoryTable.Cols.ID, category.getId());
        values.put(CategoryDbSchema.CategoryTable.Cols.NUMBER, category.getNumber());
        values.put(CategoryDbSchema.CategoryTable.Cols.NAME, category.getName());
        return values;
    }

    public void addCategory(Category category) {
        ContentValues values = getContentValues(category);
        mSQLiteDatabase.insert(CategoryDbSchema.CategoryTable.Name, null, values);
    }

    public void updateCategory(Category category) {
        int id = category.getId();
        int number = category.getNumber();
        String name = category.getName();
        ContentValues values = getContentValues(category);

        mSQLiteDatabase.update(CategoryDbSchema.CategoryTable.Name, values,
                CategoryDbSchema.CategoryTable.Cols.ID + " = ?", new String[] {String.valueOf(id)});
    }

//    private Cursor queryCategories(String whereClause, String[] whereArgs) {
//        Cursor cursor = mSQLiteDatabase.query(
//                CategoryDbSchema.CategoryTable.Name,
//                null,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null
//        );
//        return cursor;
//    }

    private CategoryCursorWrapper queryCategories(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(
                CategoryDbSchema.CategoryTable.Name,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CategoryCursorWrapper(cursor);
    }
}
