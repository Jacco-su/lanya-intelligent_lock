package com.dream.brick.equipment.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 控制器 数据库防问实体 类
 *
 * @author
 * @date 2017-12-05
 */
@Entity
@Table(name = "t_collectore")
public class Collectore implements Serializable {
    /**
     *
     */
    private String id;
    private String ceCode; //控制器ID
    private String ceMAC;// 控制器蓝牙地址
    private String cename;  //控制器名称

    private Collector collector;      // 所在采集器

    private String ceDate;// 日期

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCeCode() {
        return ceCode;
    }

    public void setCeCode(String ceCode) {
        this.ceCode = ceCode;
    }

    public String getCeMAC() {
        return ceMAC;
    }

    public void setCeMAC(String ceMAC) {
        this.ceMAC = ceMAC;
    }

    public String getCename() {
        return cename;
    }

    public void setCename(String cename) {
        this.cename = cename;
    }

    public String getCeDate() {
        return ceDate;
    }

    public void setCeDate(String ceDate) {
        this.ceDate = ceDate;
    }


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cId", nullable = false, updatable = true)
    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

}
