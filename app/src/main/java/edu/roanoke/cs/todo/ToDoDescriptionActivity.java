package edu.roanoke.cs.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ToDoDescriptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_description);
        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.TITLE_MESSAGE);
        String desc  = intent.getStringExtra(MainActivity.DESC_MESSAGE);
        TextView titleView = (TextView)findViewById(R.id.TitleDisplay);
        TextView descView = (TextView)findViewById(R.id.DescriptionDisplay);

        titleView.setText(title);
        descView.setText(desc);
    }
}
