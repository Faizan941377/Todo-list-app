package com.nextsuntech.todolistapp.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nextsuntech.todolistapp.Database.Database;
import com.nextsuntech.todolistapp.Model.TaskDTO;
import com.nextsuntech.todolistapp.R;
import com.nextsuntech.todolistapp.category.AddCategoryDao;
import com.nextsuntech.todolistapp.dashboard.DashboardActivity;
import com.nextsuntech.todolistapp.task.adapter.TaskAdapter;

import java.util.List;

public class TaskDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView titleTV;
    RecyclerView taskRV;
    LinearLayout addTaskBT;
    TaskAdapter taskAdapter;
    String cateId;
    ImageView backIV;
    List<TaskDTO> taskDTOList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        titleTV = findViewById(R.id.tv_cate_det_title);
        taskRV = findViewById(R.id.rv_cate_detail_task);
        addTaskBT = findViewById(R.id.ll_row_cate_det_container);
        backIV = findViewById(R.id.iv_cate_det_back);

        String id = getIntent().getStringExtra("id");
        cateId = id;

        String title = getIntent().getExtras().getString("title");
        titleTV.setText(title);


        addTaskBT.setOnClickListener(this);
        backIV.setOnClickListener(this);

        setAdapter();


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //Toast.makeText(ListActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();

                Database database = Room.databaseBuilder(getApplicationContext(),
                                Database.class, "Todo_list_database").allowMainThreadQueries()
                        .build();
                AddTaskDao addTaskDao = database.addTaskDao();
                String id = String.valueOf(taskDTOList.get(position).getId());
                addTaskDao.deleteById(id);
                taskDTOList.remove(position);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Task Deleted", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(taskRV);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    private void setAdapter() {

        Database database = Room.databaseBuilder(getApplicationContext(),
                        Database.class, "Todo_list_database").allowMainThreadQueries()
                .build();
        AddTaskDao addTaskDao = database.addTaskDao();
        taskDTOList = addTaskDao.getAllData(cateId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        taskAdapter = new TaskAdapter(taskDTOList);
        taskRV.setLayoutManager(layoutManager);
        taskRV.setAdapter(taskAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_row_cate_det_container:
                cateId = getIntent().getExtras().getString("id");
                Intent intent = new Intent(view.getContext(), TaskActivity.class);
                intent.putExtra("id", String.valueOf(cateId));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
                Log.e("cate_id", cateId);
                break;
            case R.id.iv_cate_det_back:
                startActivity(new Intent(TaskDetailActivity.this, DashboardActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TaskDetailActivity.this,DashboardActivity.class));
    }
}