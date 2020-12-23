/**
  * Copyright 2020 json.cn 
  */
package com.ghy.dao.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Auto-generated: 2020-10-30 0:18:6
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Entity
public class StepsEntity {


    @PrimaryKey(autoGenerate = true)
    private int stepId;
    @ColumnInfo(name = "img")
    private String img;
    @ColumnInfo(name = "step")
    private String step;

//    public StepsEntity(int stepId, String img, String step) {
//        this.stepId = stepId;
//        this.img = img;
//        this.step = step;
//    }


    public void setImg(String img) {
         this.img = img;
     }

    public void setStep(String step) {
         this.step = step;
     }

    public String getImg() {
        return img;
    }

    public String getStep() {
        return step;
    }
    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    @Override
    public String toString() {
        return "StepsEntity{" +
                "stepId=" + stepId +
                ", img='" + img + '\'' +
                ", step='" + step + '\'' +
                '}';
    }
}