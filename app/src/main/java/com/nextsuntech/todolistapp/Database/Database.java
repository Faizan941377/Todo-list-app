package com.nextsuntech.todolistapp.Database;

import androidx.room.RoomDatabase;

import com.nextsuntech.todolistapp.Model.AddCategoryDTO;
import com.nextsuntech.todolistapp.Model.TaskDTO;
import com.nextsuntech.todolistapp.category.AddCategoryDao;
import com.nextsuntech.todolistapp.task.AddTaskDao;

@androidx.room.Database(entities = {AddCategoryDTO.class,TaskDTO.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract AddCategoryDao addCategoryDao();
    public abstract AddTaskDao addTaskDao();
}
