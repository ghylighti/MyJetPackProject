package com.ghy.dao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.ghy.dao.dao.DataDao;
import com.ghy.dao.dao.DataWithStepDao;
import com.ghy.dao.entity.DataEntity;
import com.ghy.dao.entity.StepsEntity;

@Database(entities = {DataEntity.class, StepsEntity.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract DataDao dataDao();
    public abstract DataWithStepDao dataWithStepDao();
}
