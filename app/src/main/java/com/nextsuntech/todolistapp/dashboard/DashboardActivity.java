package com.nextsuntech.todolistapp.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nextsuntech.todolistapp.Database.Database;
import com.nextsuntech.todolistapp.Model.AddCategoryDTO;
import com.nextsuntech.todolistapp.Model.TaskDTO;
import com.nextsuntech.todolistapp.R;
import com.nextsuntech.todolistapp.category.AddCategoryActivity;
import com.nextsuntech.todolistapp.category.AddCategoryDao;
import com.nextsuntech.todolistapp.dashboard.adapter.AddCategoryAdapter;
import com.nextsuntech.todolistapp.task.AddTaskDao;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView categoryRV;
    CardView addCategoryBT;
    AddCategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        categoryRV = findViewById(R.id.rv_dashboard);
        addCategoryBT = findViewById(R.id.bt_dash_add_category);

        addCategoryBT.setOnClickListener(this);

        setAdapter();
    }

    private void setAdapter() {
        Database database = Room.databaseBuilder(getApplicationContext(),
                        Database.class, "Todo_list_database").allowMainThreadQueries()
                .build();

        AddCategoryDao addCategoryDao = database.addCategoryDao();
        List<AddCategoryDTO> addCategories = addCategoryDao.getAllData();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        categoryAdapter = new AddCategoryAdapter(addCategories);
        categoryRV.setLayoutManager(gridLayoutManager);
        categoryRV.setAdapter(categoryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_dash_add_category:
                startActivity(new Intent(DashboardActivity.this, AddCategoryActivity.class));
                break;
        }
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Double click to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}