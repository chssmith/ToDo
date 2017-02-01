package edu.roanoke.cs.todo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public static final String TITLE_MESSAGE = "edu.roanoke.cs.todo.TITLE";
    public static final String DESC_MESSAGE  = "edu.roanoke.cs.todo.DESCRIPTION";
    public static final String ID_MESSAGE    = "edu.roanoke.cs.todo.ID";
    private ArrayList<ToDoContent> model;
    private ArrayAdapter<ToDoContent> adapter;
    private ToDoDataSource DAO;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAO = new ToDoDataSource(this);
        DAO.open();
        model = DAO.getAllToDo();

        list = (ListView)findViewById(R.id.ToDoList);

        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ToDoDescriptionActivity.class);
                ToDoContent content = (ToDoContent)list.getItemAtPosition(position);
                intent.putExtra(TITLE_MESSAGE, content.getTitle());
                intent.putExtra(DESC_MESSAGE, content.getDescription());
                intent.putExtra(ID_MESSAGE, content.getId());
                startActivity(intent);
            }
        });
        adapter = new ArrayAdapter<ToDoContent>(this,
                android.R.layout.simple_list_item_1, model);
        list.setAdapter(adapter);
    }

    public void onClick(View view) {

        if(view.getId() == R.id.SaveButton) {
            EditText title = (EditText) findViewById(R.id.ToDoTitle);
            EditText desc  = (EditText) findViewById(R.id.ToDoDesc);

            ToDoContent newEntry = new ToDoContent(title.getText().toString(),
                    desc.getText().toString());
            title.setText("");
            desc.setText("");
            title.requestFocus();

            DAO.addToDo(newEntry);
            adapter.add(newEntry);
        }

    }
    protected void onResume() {
        DAO.open();
        model = DAO.getAllToDo();
        adapter = new ArrayAdapter<ToDoContent>(this, android.R.layout.simple_list_item_1, model);
        list.setAdapter(adapter);
        super.onResume();
    }

    protected void onPause() {
        DAO.close();
        super.onPause();
    }


}

