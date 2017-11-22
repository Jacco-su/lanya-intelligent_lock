//package com.dream.brick.admin.bean;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
///**
// * 采集器 实体类
// *
// * @author maolei
// *
// */
//@Entity
//@Table(name = "t_collector")
//public class Collector {
//
//    /**
//     * 采集器ID  编号
//     */
//    private String id;
//    /**
//     * 所属配电房名称
//     */
//    private String dname;
//    /**
//     * IP地址
//     */
//    private String cip;
//    /**
//     * 添加日期
//     */
//    private String cdate;
//
//    private String orderId;
//    /**
//     * 描述
//     */
//    private String descript;
//
//    private String areacode;
//    private String areaname;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getDname() {
//        return dname;
//    }
//
//    public void setDname(String dname) {
//        this.dname = dname;
//    }
//
//    public String getCip() {
//        return cip;
//    }
//
//    public void setCip(String cip) {
//        this.cip = cip;
//    }
//
//    public String getCdate() {
//        return cdate;
//    }
//
//    public void setCdate(String cdate) {
//        this.cdate = cdate;
//    }
//
//
//    public String getDescript() {
//        return descript;
//    }
//
//    public void setDescript(String descript) {
//        this.descript = descript;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getAreacode() {
//        return areacode;
//    }
//
//    public void setAreacode(String areacode) {
//        this.areacode = areacode;
//    }
//
//    public String getAreaname() {
//        return areaname;
//    }
//
//    public void setAreaname(String areaname) {
//        this.areaname = areaname;
//    }
//
//
//}