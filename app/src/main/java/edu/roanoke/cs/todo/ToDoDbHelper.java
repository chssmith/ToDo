package edu.roanoke.cs.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cssmith on 1/30/17.
 */

public class ToDoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDo.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ToDoContract.ToDoEntry.TABLE_NAME + " (" +
            ToDoContract.ToDoEntry._ID + " INTEGER PRIMARY KEY," +
            ToDoContract.ToDoEntry.COLUMN_NAME_TITLE + " VARCHAR(255)," +
            ToDoContract.ToDoEntry.COLUMN_NAME_DESC + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ToDoContract.ToDoEntry.TABLE_NAME;


    public ToDoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
