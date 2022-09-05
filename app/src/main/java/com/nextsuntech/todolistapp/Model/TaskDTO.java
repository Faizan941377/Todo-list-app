package com.nextsuntech.todolistapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskDTO {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public  int cate_id;

    @ColumnInfo(name = "title")
    public String title;

    public TaskDTO( int cate_id, String title) {
        this.cate_id = cate_id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
