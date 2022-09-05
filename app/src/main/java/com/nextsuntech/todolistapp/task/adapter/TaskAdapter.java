package com.nextsuntech.todolistapp.task.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.nextsuntech.todolistapp.Database.Database;
import com.nextsuntech.todolistapp.Model.TaskDTO;
import com.nextsuntech.todolistapp.R;
import com.nextsuntech.todolistapp.task.AddTaskDao;
import com.nextsuntech.todolistapp.task.TaskActivity;
import com.nextsuntech.todolistapp.task.UpdateTaskActivity;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<TaskDTO> taskDTOList;

    public TaskAdapter(List<TaskDTO> taskDTOList) {
        this.taskDTOList = taskDTOList;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.taskTV.setText(taskDTOList.get(position).getTitle());

        holder.editTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(taskDTOList.get(position).getId());
                Intent intent = new Intent(view.getContext(), UpdateTaskActivity.class);
                intent.putExtra("id",(id));
                intent.putExtra("title",taskDTOList.get(position).getTitle());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskDTOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskTV;
        ImageView editTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTV = itemView.findViewById(R.id.tv_row_task);
            editTV = itemView.findViewById(R.id.iv_row_task_edit);
        }
    }
}
