package com.dream.brick.admin.bean;

import javax.persistence.*;

/**
 * 配电房 数据库防问实体 类
 *
 * @author
 * @date 2017-12-05
 */
@Entity
@Table(name = "t_qgdis")
public class Diss implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4725135016331954412L;
    private int id;
    private String name;// 配电房名称
    private String address;// 配电房地址
    private String areacode;// 配电房所在地区编码
    private String areaname;// 配电房所在地区名称

    private String lock;
    private int sortorder;// 配电房显示顺序

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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


    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }


    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }
}
