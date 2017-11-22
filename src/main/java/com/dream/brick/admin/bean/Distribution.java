package com.dream.brick.admin.bean;

import javax.persistence.*;

/**
 * 配电房实体类
 *
 * @author maolei
 */
@Entity
@Table(name = "t_dis")
public class Distribution {

    /**
     * 配电房ID
     */
    private String id;
    /**
     * 配电房名称
     */
    private String name;
    /**
     * 区域ID
     */
    private String parentId;
    /**
     * 排序ID
     */
    private String orderId;
    /**
     * 描述
     */
    private String descript;

    private String areacode;
    private String areaname;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }


}