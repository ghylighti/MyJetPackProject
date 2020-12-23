package com.ghy.dao.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BaseEntity {
    @PrimaryKey(autoGenerate = true)
    private int uid;



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
