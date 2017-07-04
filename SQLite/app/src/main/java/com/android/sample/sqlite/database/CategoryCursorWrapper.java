package com.android.sample.sqlite.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.android.sample.sqlite.model.Category;

/**
 * Created by yoon on 2017. 7. 4..
 */

public class CategoryCursorWrapper extends CursorWrapper {

    public CategoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Category getCategory() {
        int id = getInt(getColumnIndex(CategoryDbSchema.CategoryTable.Cols.ID));
        int number = getInt(getColumnIndex(CategoryDbSchema.CategoryTable.Cols.NUMBER));
        String name = getString(getColumnIndex(CategoryDbSchema.CategoryTable.Cols.NAME));

        Category category = new Category();
        category.setId(id);
        category.setNumber(number);
        category.setName(name);
        return category;
    }
}
