package com.nextsuntech.todolistapp.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nextsuntech.todolistapp.Database.Database;
import com.nextsuntech.todolistapp.Model.AddCategoryDTO;
import com.nextsuntech.todolistapp.R;
import com.nextsuntech.todolistapp.dashboard.DashboardActivity;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backIV;
    LinearLayout addCategoryBT;
    EditText addCategoryET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        backIV = findViewById(R.id.iv_add_category_back);
        addCategoryBT = findViewById(R.id.bt_add_category);
        addCategoryET = findViewById(R.id.et_add_category);

        backIV.setOnClickListener(this);
        addCategoryBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_category_back:
                finish();
                break;
            case R.id.bt_add_category:
                if (addCategoryET.length()==0){
                    addCategoryET.setError("Please enter category name");
                }else {
                    new bgThread().start();
                    Toast.makeText(AddCategoryActivity.this, "Category Added", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    class bgThread extends Thread {
        public void run() {
            super.run();
            Database database = Room.databaseBuilder(getApplicationContext(),
                            Database.class, "Todo_list_database")
                    .build();
            AddCategoryDao addCategoryDao = database.addCategoryDao();
            addCategoryDao.insert(new AddCategoryDTO( addCategoryET.getText().toString()));
            addCategoryET.setText("");
        }
    }
}