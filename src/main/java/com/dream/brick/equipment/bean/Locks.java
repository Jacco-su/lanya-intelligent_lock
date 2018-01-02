package com.dream.brick.equipment.bean;


import javax.persistence.*;
import java.util.Date;

/**
 * 智能门锁实体类
 */
@Entity
@Table(name = "t_locks")
public class Locks {

    private Integer id;         //锁id 编号
    private String lockNum;     //锁编号
    private String lockCode;       //锁识别码
    private String lockDate;      //安装时间
    private Qgdis qgdis;
    private String address;//详细地址
    /**
     * 常显
     */
    private int alwaysShow;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLockNum() {
        return lockNum;
    }

    public void setLockNum(String lockNum) {
        this.lockNum = lockNum;
    }

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode;
    }

    public String getLockDate() {
        return lockDate;
    }

    public void setLockDate(String lockDate) {
        this.lockDate = lockDate;
    }


    public int getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(int alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "dissId", nullable = false, updatable = true)
    public Qgdis getQgdis() {
        return qgdis;
    }

    public void setQgdis(Qgdis qgdis) {
        this.qgdis = qgdis;
    }
}
