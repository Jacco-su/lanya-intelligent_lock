package com.dream.brick.equipment.bean;

import javax.persistence.*;

/**
 * 采集器 数据库防问实体 类
 *
 * @author
 * @date 2017-12-05
 */
@Entity
@Table(name = "t_collectore")
public class Collectore {
    /**
     *
     */

    private String id;
    private String ceCode; //控制器ID
    private String ceMAC;// 控制器蓝牙地址
    //        private String disId;// 所在站点
    private String cid;
    private String cename;  //控制器名称

    private Collector collector;      // 所在采集器

    private String ceDate;// 日期


    // private Set<Employee> emps = new HashSet<>();
    // private List<Qgdis> dis=new ArrayList<Qgdis>();

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




//    public int getSortorder() {
//        return sortorder;
//    }
//
//    public void setSortorder(int sortorder) {
//        this.sortorder = sortorder;
//    }


//    @Column(name = "collectore_diss")
//    public String getCollectoreDiss() {
//        return collectoreDiss;
//    }
//
//    public void setCollectoreDiss(String collectoreDiss) {
//        this.collectoreDiss = collectoreDiss;
//    }

}
