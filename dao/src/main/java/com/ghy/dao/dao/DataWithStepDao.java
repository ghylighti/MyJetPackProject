package com.ghy.dao.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.ghy.dao.entity.DataEntity;
import com.ghy.dao.entity.DataWithStep;
import com.ghy.dao.entity.StepsEntity;

import java.util.List;

@Dao
public abstract class DataWithStepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(DataEntity dataEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<StepsEntity> stepsEntities);

    @Transaction
    public void insert(DataWithStep dataWithStep){
        insert(dataWithStep.getDataEntity());
        insert(dataWithStep.getStepsEntities());
    }
    @Transaction
    @Query("SELECT * FROM dataentity")
    public abstract List<DataWithStep> getAll();
}
