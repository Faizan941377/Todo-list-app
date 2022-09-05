package com.nextsuntech.todolistapp.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nextsuntech.todolistapp.Database.Database;
import com.nextsuntech.todolistapp.Model.TaskDTO;
import com.nextsuntech.todolistapp.R;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {

    EditText addTaskET;
    LinearLayout addTaskBT;
    ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        addTaskET = findViewById(R.id.et_add_task);
        addTaskBT = findViewById(R.id.bt_add_task);
        backIV = findViewById(R.id.iv_task_back);

        addTaskBT.setOnClickListener(this);
        backIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add_task:
                if (addTaskET.length() == 0) {
                    addTaskET.setError("Please enter task name");
                } else {
                    new bgThread().start();
                    Toast.makeText(TaskActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case R.id.iv_task_back:
                finish();
                break;
        }
    }

    class bgThread extends Thread {
        public void run() {
            super.run();

            String id = getIntent().getExtras().getString("id");

            Database database = Room.databaseBuilder(getApplicationContext(),
                            Database.class, "Todo_list_database")
                    .build();
            AddTaskDao addTaskDao = database.addTaskDao();
            addTaskDao.insert(new TaskDTO(Integer.valueOf(id), addTaskET.getText().toString()));
            addTaskET.setText("");


        }
    }
}