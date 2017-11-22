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
    private String kryssMAC;       //蓝牙钥匙MAC地址
    private String userName;    //领用人名字
    private Date keyssDate;         //领用时间


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

    public String getKryssMAC() {
        return kryssMAC;
    }

    public void setKryssMAC(String kryssMAC) {
        this.kryssMAC = kryssMAC;
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
}
