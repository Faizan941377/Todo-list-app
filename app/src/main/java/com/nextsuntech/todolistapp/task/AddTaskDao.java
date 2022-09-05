package com.nextsuntech.todolistapp.task;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nextsuntech.todolistapp.Model.AddCategoryDTO;
import com.nextsuntech.todolistapp.Model.TaskDTO;

import java.util.List;

@androidx.room.Dao
public interface AddTaskDao {

    @Insert
    void insert(TaskDTO taskDTO);

    @Query("SELECT * FROM TaskDTO where cate_id =:id")
    List<TaskDTO> getAllData(String id);


    @Query("DELETE FROM TaskDTO WHERE id=:id")
    void deleteById(String id);

    @Query("UPDATE TaskDTO SET title=:title WHERE id=:id")
    void updateById(String id, String title);
}
