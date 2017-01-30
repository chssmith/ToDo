package edu.roanoke.cs.todo;

/**
 * Created by cssmith on 1/30/17.
 */

public class ToDoContent {
    private static final long NEW_ENTRY = -1;
    private long id;
    private String title;
    private String description;

    public ToDoContent(String title, String desc) {
        this.id = NEW_ENTRY;
        this.title = title;
        this.description = desc;
    }

    public ToDoContent(long id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.description = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }
}
