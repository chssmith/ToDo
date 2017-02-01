package edu.roanoke.cs.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by cssmith on 1/30/17.
 */

public class ToDoDataSource {
    private SQLiteDatabase database;
    private ToDoDbHelper dbHelper;

    public ToDoDataSource(Context context) {
        dbHelper = new ToDoDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addToDo(ToDoContent content) {
        ContentValues values = new ContentValues();

        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_TITLE, content.getTitle());
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_DESC, content.getDescription());

        long insertID = database.insert(ToDoContract.ToDoEntry.TABLE_NAME,
                null,
                values);
        content.setId(insertID);
    }

    public void deleteToDo(long id) {
        System.out.println("Deleting entry with ID: " + id);

        database.delete(ToDoContract.ToDoEntry.TABLE_NAME,
                ToDoContract.ToDoEntry._ID + " = " + id, null);
    }

    public ArrayList<ToDoContent> getAllToDo() {
        ArrayList<ToDoContent> entries = new ArrayList<ToDoContent>();

        Cursor c = database.query(ToDoContract.ToDoEntry.TABLE_NAME,
                ToDoContract.ToDoEntry.ALL_COLUMNS, null, null, null, null, null);

        while(c.moveToNext()) {
            entries.add(cursorToEntry(c));
        }

        return entries;
    }

    private ToDoContent cursorToEntry(Cursor c) {
        long id = c.getLong(c.getColumnIndexOrThrow(ToDoContract.ToDoEntry._ID));
        String title = c.getString(c.getColumnIndexOrThrow((ToDoContract.ToDoEntry.COLUMN_NAME_TITLE)));
        String desc = c.getString(c.getColumnIndexOrThrow(ToDoContract.ToDoEntry.COLUMN_NAME_DESC));

        return new ToDoContent(id, title, desc);
    }
}
