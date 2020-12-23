/**
  * Copyright 2020 json.cn 
  */
package com.ghy.api.dto.newProjectRootBean;

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-21 22:46:11
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Tags {

    private String name;
    private String url;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    @Override
    public String toString() {
        return "Tags{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}