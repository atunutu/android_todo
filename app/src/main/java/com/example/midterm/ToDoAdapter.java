package com.example.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {
    ArrayList<Todo> tasks;
    Context context;
    String filter;

    public ToDoAdapter(Context context,ArrayList<Todo> tasks, String filter ){
        this.tasks = tasks;
        this.context = context;
        this.filter = filter;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view =  LayoutInflater.from(context).inflate(R.layout.layout_todo_item,viewGroup,false);
        }
        Todo task = tasks.get(i);

        CheckBox chkTask = view.findViewById(R.id.chkTask);
        chkTask.setText(task.getTask());
        chkTask.setChecked(task.isChecked());

        if(filter.equals("All")){


        }else if (filter.equals("Incomplete")){
            chkTask.setEnabled(false);

        }else if (filter.equals("Completed")){
            chkTask.setEnabled(false);

        }

        chkTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkTask.isChecked()){
                    tasks.remove(task);
                    task.setChecked(true);
                    tasks.add(tasks.size(), task);
                    notifyDataSetChanged();
                } else if (!chkTask.isChecked()) {
                    tasks.remove(task);
                    task.setChecked(false);
                    tasks.add(0, task);
                    notifyDataSetChanged();

                }
            }

        });

        return view;
    }
}
