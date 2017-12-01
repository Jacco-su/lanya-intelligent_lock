package com.dream.brick.equipment.bean;


import javax.persistence.*;
import java.util.Date;

/**
 * 蓝牙钥匙实体类
 */

@Entity
@Table(name = "t_keyss")

public class Keyss {

    private Integer id;             //蓝牙钥匙id 编号
    private String keyssName;       //钥匙自定义名称
    private String keyssMAC;       //蓝牙钥匙MAC地址
    private String userName;    //领用人名字
    private Date keyssDate;         //领用时间
    private Integer sortorder;            //排序
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

    public String getKeyssName() {
        return keyssName;
    }

    public void setKeyssName(String keyssName) {
        this.keyssName = keyssName;
    }

    public String getKeyssMAC() {
        return keyssMAC;
    }

    public void setKeyssMAC(String kryssMAC) {
        this.keyssMAC = kryssMAC;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getKeyssDate() {
        return keyssDate;
    }

    public void setKeyssDate(Date keyssDate) {
        this.keyssDate = keyssDate;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public int getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(int alwaysShow) {
        this.alwaysShow = alwaysShow;
    }
}
