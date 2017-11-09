package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by taller on 16/3/22.
 */
@Entity
@Table(name = "t_fund_details")
@PropertyNote(name = "基金收入统计")
public class FundDetails implements Serializable {
    @PropertyNote(name = "序号")
    private Integer id;
    @PropertyNote(name = "网点编码")
    private String dswd;
    @PropertyNote(name = "开票人")
    private String kpr;
    @PropertyNote(name = "收款日期")
    private String srdate;
    @PropertyNote(name = "通知书号")
    private String tzsph;
    @PropertyNote(name = "票号")
    private String ph;
    @PropertyNote(name = "缴款单位")
    private String jkrname;
    @PropertyNote(name = "报建面积")
    private Double bjmj;
    @PropertyNote(name = "收费金额")
    private double fund;
    @PropertyNote(name = "备注")
    private String remark;
    private String areacode;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrdate() {
        return srdate;
    }

    public void setSrdate(String srdate) {
        this.srdate = srdate;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getJkrname() {
        return jkrname;
    }

    public void setJkrname(String jkrname) {
        this.jkrname = jkrname;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public String getTzsph() {
        return tzsph;
    }

    public void setTzsph(String tzsph) {
        this.tzsph = tzsph;
    }


    public String getDswd() {
        return dswd;
    }

    public void setDswd(String dswd) {
        this.dswd = dswd;
    }

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getBjmj() {
        return bjmj;
    }

    public void setBjmj(Double bjmj) {
        this.bjmj = bjmj;
    }
}
