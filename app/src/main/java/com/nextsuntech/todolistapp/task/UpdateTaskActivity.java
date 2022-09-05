package com.nextsuntech.todolistapp.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nextsuntech.todolistapp.Database.Database;
import com.nextsuntech.todolistapp.R;
import com.nextsuntech.todolistapp.category.AddCategoryDao;

public class UpdateTaskActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backIV;
    EditText updateET;
    LinearLayout updateBT;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        backIV = findViewById(R.id.iv_update_task_back);
        updateET = findViewById(R.id.et_update_task);
        updateBT = findViewById(R.id.bt_update_task);

        id = getIntent().getExtras().getString("id");
        updateET.setText(getIntent().getStringExtra("title"));


        backIV.setOnClickListener(this);
        updateBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_update_task_back:
                finish();
                break;
            case R.id.bt_update_task:
                Database database = Room.databaseBuilder(view.getContext(),
                                Database.class, "Todo_list_database").allowMainThreadQueries()
                        .build();
                AddTaskDao addTaskDao = database.addTaskDao();
                addTaskDao.updateById(id,updateET.getText().toString());
              //  startActivity(new Intent(UpdateTaskActivity.this,TaskDetailActivity.class));
                finish();
        }
    }
}