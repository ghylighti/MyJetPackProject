/**
  * Copyright 2020 json.cn 
  */
package com.ghy.api.dto.authorBookBean;

import java.io.Serializable;

/**
 * Auto-generated: 2020-12-06 23:16:40
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class AuthorBookRootBean implements Serializable {



    private AuthorBookDatas data;
    private int errorCode;
    private String errorMsg;
    public AuthorBookDatas getData() {
        return data;
    }

    public void setData(AuthorBookDatas data) {
        this.data = data;
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