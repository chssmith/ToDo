package edu.roanoke.cs.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ToDoDescriptionActivity extends Activity {
    private long id;
    private ToDoDataSource DAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_description);

        DAO = new ToDoDataSource(this);
        DAO.open();

        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.TITLE_MESSAGE);
        String desc  = intent.getStringExtra(MainActivity.DESC_MESSAGE);
        System.out.println(intent.getStringExtra(MainActivity.ID_MESSAGE));
        id = intent.getLongExtra(MainActivity.ID_MESSAGE, -1);
        TextView titleView = (TextView)findViewById(R.id.TitleDisplay);
        TextView descView = (TextView)findViewById(R.id.DescriptionDisplay);

        titleView.setText(title);
        descView.setText(desc);
    }

    public void onBackClick(View view) {
        this.finish();
    }

    public void onDeleteClick(View view) {
        DAO.deleteToDo(id);
        this.finish();
    }
}
