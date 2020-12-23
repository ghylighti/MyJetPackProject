package com.ghy.dao.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverter;

import java.util.List;

@Entity
public class DataEntity {


    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "title")

    private String title;
    @ColumnInfo(name = "tags")

    private String tags;
    @ColumnInfo(name = "imtro")

    private String imtro;
    @ColumnInfo(name = "ingredients")

    private String ingredients;
    @ColumnInfo(name = "burden")

    private String burden;

//    private List<StepsEntity> steps;

    public void setId(String id) {
        this.id = id;
    }

//    public void setSteps(List<StepsEntity> steps) {
//        this.steps = steps;
//    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }



    @Override
    public String toString() {
        return "DataEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", imtro='" + imtro + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", burden='" + burden + '\'' +
//                ", albums=" + albums +
//                ", steps=" + steps +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public String getImtro() {
        return imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getBurden() {
        return burden;
    }



//    public List<StepsEntity> getSteps() {
//        return steps;
//    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
