package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstToDo;
    ArrayList<Todo> tasks;
    ToDoAdapter adapter;
    EditText etName;
    Button btnAddTask;
    Spinner spFilter;
    private static final String[] filterOptions= new String[] {
            "All", "Completed", "Incomplete"
    };
    ArrayAdapter<String> filterAdapter;

    ArrayList<Todo> completedTasks;
    ArrayList<Todo> incompleteTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etTaskName);
        btnAddTask = findViewById(R.id.btnAddTask);
        lstToDo = findViewById(R.id.lstTodoList);

        spFilter = findViewById(R.id.spFilter);
        tasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
        incompleteTasks = new ArrayList<>();

        tasks.add(new Todo("Mow the lawn"));
        tasks.add(new Todo("Do the dishes"));
        tasks.add(new Todo("Study for test (lol jk)"));
        tasks.add(new Todo("Finish homework"));
        tasks.add(new Todo("Do the laundry"));
        tasks.add(new Todo("Clean my room"));
        tasks.add(new Todo("Go to dentist"));
        tasks.add(new Todo("Grocery run"));
        tasks.add(new Todo("Order kitchen rolls"));
        tasks.add(new Todo("Feed the fish"));
        tasks.add(new Todo("Soccer practice"));

        adapter = new ToDoAdapter(this, tasks, "All");


        lstToDo.setAdapter(adapter);

        filterAdapter = new
                ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,filterOptions);

        spFilter.setAdapter(filterAdapter);

        btnAddTask.setOnClickListener(l->{
            String task = etName.getText()+"";
            if(!task.equals("")){
                Todo newTask = new Todo(etName.getText()+"");
                tasks.add(0,newTask);
                adapter.notifyDataSetChanged();
            }

        });

        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("selected",i+"");
                if(filterOptions[i].equals("All")){
                    etName.setVisibility(View.VISIBLE);
                    btnAddTask.setVisibility(View.VISIBLE);
                    adapter = new ToDoAdapter(getApplicationContext(), tasks, "All");
                    lstToDo.setAdapter(adapter);

                } else if (filterOptions[i].equals("Completed")) {
                    completedTasks.clear();
                    for (Todo todo: tasks) {
                        if(todo.isChecked){
                            completedTasks.add(todo);
                        }
                    }
                    adapter = new ToDoAdapter(getApplicationContext(), completedTasks, "Completed");
                    lstToDo.setAdapter(adapter);
                    etName.setVisibility(View.INVISIBLE);
                    btnAddTask.setVisibility(View.INVISIBLE);

                }else if (filterOptions[i].equals("Incomplete")){
                    incompleteTasks.clear();
                    for (Todo todo: tasks) {
                        if(!todo.isChecked){
                            incompleteTasks.add(todo);
                        }
                    }
                    adapter = new ToDoAdapter(getApplicationContext(), incompleteTasks, "Incomplete");
                    lstToDo.setAdapter(adapter);

                    etName.setVisibility(View.INVISIBLE);
                    btnAddTask.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}