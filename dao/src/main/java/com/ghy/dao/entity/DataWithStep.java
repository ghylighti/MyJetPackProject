package com.ghy.dao.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DataWithStep {
    @Embedded
    private DataEntity dataEntity;
    @Relation(parentColumn = "uid",entityColumn = "stepId")
    private List<StepsEntity> stepsEntities;

    public DataWithStep(DataEntity dataEntity, List<StepsEntity> stepsEntities) {
        this.dataEntity = dataEntity;
        this.stepsEntities = stepsEntities;
    }

    public DataEntity getDataEntity() {
        return dataEntity;
    }

    public void setDataEntity(DataEntity dataEntity) {
        this.dataEntity = dataEntity;
    }

    public List<StepsEntity> getStepsEntities() {
        return stepsEntities;
    }

    public void setStepsEntities(List<StepsEntity> stepsEntities) {
        this.stepsEntities = stepsEntities;
    }

    @Override
    public String toString() {
        return "DataWithStep{" +
                "dataEntity=" + dataEntity +
                ", stepsEntities=" + stepsEntities +
                '}';
    }
}
