package com.ghy.dao.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.ghy.dao.entity.BaseEntity;
import com.ghy.dao.entity.DataEntity;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM dataentity")
    @Transaction
    List<DataEntity> getAll();
    @Delete
    void delete(DataEntity dataEntities);
    @Insert()
    void insertAll(DataEntity... DataEntity);
}
