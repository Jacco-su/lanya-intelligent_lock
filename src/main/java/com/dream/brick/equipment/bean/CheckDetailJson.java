/*
 *  Copyright (c) 2016-2020  陶乐乐,毛磊版权所有 
 *  Tao Lele, Pierre Morel. All Rights Reserved.
 */

package com.dream.brick.equipment.bean;

import java.io.Serializable;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: CheckDetailJson.java
 * @Description:
 * @date 2016-05-02 21:15
 */
public class CheckDetailJson implements Serializable {
    private String projectName;
    private String danganbx;
    private String begindate;
    private String enddate;
    private String builder;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDanganbx() {
        return danganbx;
    }

    public void setDanganbx(String danganbx) {
        this.danganbx = danganbx;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }
}
