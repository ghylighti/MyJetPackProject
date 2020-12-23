/**
  * Copyright 2020 json.cn 
  */
package com.ghy.api.dto.newProjectRootBean;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-11-21 22:46:11
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class NewProjectDatas implements Parcelable {

    private int curPage;
    private List<NewProjectData> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    protected NewProjectDatas(Parcel in) {
        curPage = in.readInt();
        datas = in.createTypedArrayList(NewProjectData.CREATOR);
        offset = in.readInt();
        over = in.readByte() != 0;
        pageCount = in.readInt();
        size = in.readInt();
        total = in.readInt();
    }

    public static final Creator<NewProjectDatas> CREATOR = new Creator<NewProjectDatas>() {
        @Override
        public NewProjectDatas createFromParcel(Parcel in) {
            return new NewProjectDatas(in);
        }

        @Override
        public NewProjectDatas[] newArray(int size) {
            return new NewProjectDatas[size];
        }
    };

    public void setCurPage(int curPage) {
         this.curPage = curPage;
     }
     public int getCurPage() {
         return curPage;
     }

    public void setData(List<NewProjectData> data) {
         this.datas = data;
     }
     public List<NewProjectData> getData() {
         return datas;
     }

    public void setOffset(int offset) {
         this.offset = offset;
     }
     public int getOffset() {
         return offset;
     }

    public void setOver(boolean over) {
         this.over = over;
     }
     public boolean getOver() {
         return over;
     }

    public void setPageCount(int pageCount) {
         this.pageCount = pageCount;
     }
     public int getPageCount() {
         return pageCount;
     }

    public void setSize(int size) {
         this.size = size;
     }
     public int getSize() {
         return size;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    @Override
    public String toString() {
        return "AuthorBookData{" +
                "curPage=" + curPage +
                ", data=" + datas +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(curPage);
        dest.writeTypedList(datas);
        dest.writeInt(offset);
        dest.writeByte((byte) (over ? 1 : 0));
        dest.writeInt(pageCount);
        dest.writeInt(size);
        dest.writeInt(total);
    }
}