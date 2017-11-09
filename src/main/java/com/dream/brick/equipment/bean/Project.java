package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 工程基本情况
 * 
 * @author maolei
 * @date 2016-02-05
 */
@Entity
@Table(name = "t_project")
@PropertyNote(name = "工程统计")
public class Project implements Serializable {
	private String id;
	@PropertyNote(name = "工程项目名称")
	private String name;//工程项目名称
	@PropertyNote(name = "建设单位")
	private String builder;//建设单位<br/>（开发商）
	@PropertyNote(name = "施工单位")
	private String sgdanwei;//施工单位
	@PropertyNote(name = "所属区域")
	private String areaname;//所属区域名称
	@PropertyNote(name = "工程项目地址")
	private String address;//工程项目地址
	private String chanpinbs;
	private String djren;
	@PropertyNote(name = "录入时间")
	private String djtime;
	private String djrenid;
	private String areacode;//所属区域编码
	private String orgid;//登记机构ID
	private String orgname;//登记机构名称


	//add to
//	private String sgdanweiName;//施工单位联系人
//	private String sgdanweiPhone;//施工单位联系人电话
//	private String builderName;//建设单位联系人
//	private String builderPhone;//建设单位联系人电话
//	private String jianliName;//监理单位联系人
//	private String jianliPhone;//监理单位联系人电话
//	private String jianli;//监理单位
//	private String descx;//描述
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getBuilder() {
		return builder;
	}
	public void setBuilder(String builder) {
		this.builder = builder;
	}
	public String getSgdanwei() {
		return sgdanwei;
	}
	public void setSgdanwei(String sgdanwei) {
		this.sgdanwei = sgdanwei;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getChanpinbs() {
		return chanpinbs;
	}
	public void setChanpinbs(String chanpinbs) {
		this.chanpinbs = chanpinbs;
	}
	public String getDjren() {
		return djren;
	}
	public void setDjren(String djren) {
		this.djren = djren;
	}
	public String getDjtime() {
		return djtime;
	}
	public void setDjtime(String djtime) {
		this.djtime = djtime;
	}
	public String getDjrenid() {
		return djrenid;
	}
	public void setDjrenid(String djrenid) {
		this.djrenid = djrenid;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
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
//    @Column(name = "sgdanwei_name")
//	public String getSgdanweiName() {
//		return sgdanweiName;
//	}
//
//	public void setSgdanweiName(String sgdanweiName) {
//		this.sgdanweiName = sgdanweiName;
//	}
//	@Column(name = "sgdanwei_phone")
//	public String getSgdanweiPhone() {
//		return sgdanweiPhone;
//	}
//
//	public void setSgdanweiPhone(String sgdanweiPhone) {
//		this.sgdanweiPhone = sgdanweiPhone;
//	}
//	@Column(name = "builder_name")
//	public String getBuilderName() {
//		return builderName;
//	}
//
//	public void setBuilderName(String builderName) {
//		this.builderName = builderName;
//	}
//	@Column(name = "builder_phone")
//	public String getBuilderPhone() {
//		return builderPhone;
//	}
//
//	public void setBuilderPhone(String builderPhone) {
//		this.builderPhone = builderPhone;
//	}
//	@Column(name = "jianli_name")
//	public String getJianliName() {
//		return jianliName;
//	}
//
//	public void setJianliName(String jianliName) {
//		this.jianliName = jianliName;
//	}
//	@Column(name = "jianli_phone")
//	public String getJianliPhone() {
//		return jianliPhone;
//	}
//
//	public void setJianliPhone(String jianliPhone) {
//		this.jianliPhone = jianliPhone;
//	}
//
//	public String getDescx() {
//		return descx;
//	}
//
//	public void setDescx(String descx) {
//		this.descx = descx;
//	}
//
//	public String getJianli() {
//		return jianli;
//	}
//
//	public void setJianli(String jianli) {
//		this.jianli = jianli;
//	}
}
