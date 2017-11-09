package com.dream.brick.equipment.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by taller on 16/3/16.
 */
@Entity
@Table(name = "t_product_new")
public class ProductNew implements Serializable {
    private String id;
    private String grade;//等级
    private String companyName;//企业名称
    private String productName;//产品名称
    private String samplingTime;//抽样时间
    private String samplingAddress;//抽样地区
    private String endTime;//检测报告结果时间
    private String testResult;//检测结果
    private String isTestResult;//合格不合格
    private String areacode;
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @Column(name = "sampling_time")
    public String getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(String samplingTime) {
        this.samplingTime = samplingTime;
    }
    @Column(name = "sampling_address")
    public String getSamplingAddress() {
        return samplingAddress;
    }

    public void setSamplingAddress(String samplingAddress) {
        this.samplingAddress = samplingAddress;
    }
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    @Column(name = "test_result")
    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
    @Column(name = "is_test_result")
    public String getIsTestResult() {
        return isTestResult;
    }

    public void setIsTestResult(String isTestResult) {
        this.isTestResult = isTestResult;
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
}
