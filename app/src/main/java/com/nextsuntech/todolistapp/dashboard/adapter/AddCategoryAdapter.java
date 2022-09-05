package com.nextsuntech.todolistapp.dashboard.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.nextsuntech.todolistapp.Database.Database;
import com.nextsuntech.todolistapp.Model.AddCategoryDTO;
import com.nextsuntech.todolistapp.Model.TaskDTO;
import com.nextsuntech.todolistapp.R;
import com.nextsuntech.todolistapp.category.AddCategoryDao;
import com.nextsuntech.todolistapp.task.AddTaskDao;
import com.nextsuntech.todolistapp.task.TaskDetailActivity;

import java.util.List;

public class AddCategoryAdapter extends RecyclerView.Adapter<AddCategoryAdapter.ViewHolder> {

    List<AddCategoryDTO> addCategories;


    public AddCategoryAdapter(List<AddCategoryDTO> addCategories) {
        this.addCategories = addCategories;
    }

    @NonNull
    @Override
    public AddCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_add_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCategoryAdapter.ViewHolder holder, int position) {
        holder.titleTV.setText(addCategories.get(position).getTitle());


        int id = addCategories.get(position).getId();


        holder.addTasksBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskDetailActivity.class);
                intent.putExtra("id",String.valueOf(id));
                intent.putExtra("title",addCategories.get(position).getTitle());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
                ((Activity)view.getContext()).finish();
            }
        });

        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = Room.databaseBuilder(view.getContext(),
                                Database.class, "Todo_list_database").allowMainThreadQueries()
                        .build();
                AddCategoryDao addCategoryDao = database.addCategoryDao();
                addCategoryDao.deleteById(String.valueOf(id));
                addCategoryDao.deleteByCateId(String.valueOf(id));
                addCategories.remove(position);
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Category Deleted", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public int getItemCount() {
        return addCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;
        LinearLayout addTasksBT;
        ImageView deleteIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.tv_row_add_cate_title);
            addTasksBT = itemView.findViewById(R.id.ll_row_add_cate_main_container);
            deleteIV = itemView.findViewById(R.id.iv_row_add_cate_delete);
        }
    }
}
