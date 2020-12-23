/**
  * Copyright 2020 json.cn 
  */
package com.ghy.api.dto.authorBookBean;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-12-06 23:16:40
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class AuthorBookDatas implements Serializable {

    private int curPage;
    private List<AuthorBookData> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    public void setCurPage(int curPage) {
         this.curPage = curPage;
     }
     public int getCurPage() {
         return curPage;
     }

    public void setData(List<AuthorBookData> data) {
         this.datas = data;
     }
     public List<AuthorBookData> getData() {
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

}