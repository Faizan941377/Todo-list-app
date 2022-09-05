package com.nextsuntech.todolistapp.category;

import androidx.room.Insert;
import androidx.room.Query;

import com.nextsuntech.todolistapp.Model.AddCategoryDTO;

import java.util.List;

@androidx.room.Dao
public interface AddCategoryDao {

    @Insert
    void insert(AddCategoryDTO addCategoryDTO);

    @Query("SELECT * FROM AddCategoryDTO")
    List<AddCategoryDTO> getAllData();

    @Query("DELETE FROM AddCategoryDTO WHERE id=:id")
    void deleteById(String id);

    @Query("DELETE FROM TaskDTO WHERE cate_id=:id")
    void deleteByCateId(String id);
}
