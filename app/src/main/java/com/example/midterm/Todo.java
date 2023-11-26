package com.example.midterm;

public class Todo {
    String task;
    boolean isChecked;

    public Todo(String task) {
        this.task = task;
        isChecked = false;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
