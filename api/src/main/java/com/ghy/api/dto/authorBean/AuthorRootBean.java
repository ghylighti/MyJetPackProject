/**
  * Copyright 2020 json.cn 
  */
package com.ghy.api.dto.authorBean;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-12-06 18:29:44
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class AuthorRootBean implements Serializable {

    private List<AuthorData> data;
    private int errorCode;
    private String errorMsg;
    public void setData(List<AuthorData> data) {
         this.data = data;
     }
     public List<AuthorData> getData() {
         return data;
     }

    public void setErrorCode(int errorCode) {
         this.errorCode = errorCode;
     }
     public int getErrorCode() {
         return errorCode;
     }

    public void setErrorMsg(String errorMsg) {
         this.errorMsg = errorMsg;
     }
     public String getErrorMsg() {
         return errorMsg;
     }

}