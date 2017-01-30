package edu.roanoke.cs.todo;

import android.provider.BaseColumns;

/**
 * Created by cssmith on 1/30/17.
 */

public final class ToDoContract {
    private ToDoContract(){}

    public static class ToDoEntry implements BaseColumns {
        public static final String TABLE_NAME = "ToDoEntry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESC = "description";
        public static final String[] ALL_COLUMNS = {ToDoEntry._ID,
                COLUMN_NAME_TITLE,
                COLUMN_NAME_DESC};
    }
}
