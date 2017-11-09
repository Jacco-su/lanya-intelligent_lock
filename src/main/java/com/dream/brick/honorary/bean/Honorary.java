package com.dream.brick.honorary.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 工作荣誉实体
 * Created by taller on 16/2/23.
 */
@Entity
@Table(name = "t_honorary")
public class Honorary {
    private String id;
    private String year;
    private String honoraryWork;
    private String areacode;
    private String areaname;
    private String orgid;
    private String orgname;
    private String checkTime;
    @Column(name = "honorary_work")
    public String getHonoraryWork() {
        return honoraryWork;
    }

    public void setHonoraryWork(String honoraryWork) {
        this.honoraryWork = honoraryWork;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
}
