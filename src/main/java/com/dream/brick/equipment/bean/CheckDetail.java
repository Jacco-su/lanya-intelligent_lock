package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *现场核查
 * 
 * @author maolei
 * @date 2016-02-10 17:50
 */
@Entity
@Table(name = "t_xc_detail")
@PropertyNote(name = "核查明细统计")
public class CheckDetail {
	private String id;
	private Project project;//对应现场核查
	@PropertyNote(name = "序号")
	private String xuhao;//序号
	@PropertyNote(name = "档案编号")
	private String danganbx;//档案编号
	@PropertyNote(name = "建设单位")
	private String builder;//建设单位<br/>（开发商）
	@PropertyNote(name = "项目名称")
	private String projectName;
	@PropertyNote(name = "工程地址")
	private String address;
	@PropertyNote(name = "现场核查情况")
	private String hcqk;//核查情况
	@PropertyNote(name = "是否分段")
	private String fenduan;//是否分段 0 否 1 是
	@PropertyNote(name = "联系人")
	private String lxren;//联系人
	@PropertyNote(name = "缴费标准")
	private String jfbz;//缴费标准
	@PropertyNote(name = "缴费日期")
	private String jfdate;//缴费日期
	private String jfbzdesc;//缴费标准描述
	private String lxr_phone;//联系人手机
	@PropertyNote(name = "建筑面积")
	private double jzhumj;//建筑面积
	@PropertyNote(name = "缴费金额")
	private double jfmoney;//缴费金额
	private String djtime;//登记时间
	private String djren;//登记人
	private String djrenid;//登记人ID
	private String descrip;//备注

	//extends
	private String qcsyqk;//墙材使用情况
	@PropertyNote(name = "退费办理情况")
	private String fhblqk;//返还办理情况
	private String compname;//企业名称
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false, updatable = true)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDanganbx() {
		return danganbx;
	}
	public void setDanganbx(String danganbx) {
		this.danganbx = danganbx;
	}
	public String getHcqk() {
		return hcqk;
	}
	public void setHcqk(String hcqk) {
		this.hcqk = hcqk;
	}
	public String getJfbz() {
		return jfbz;
	}
	public void setJfbz(String jfbz) {
		this.jfbz = jfbz;
	}
	public String getFenduan() {
		return fenduan;
	}
	public void setFenduan(String fenduan) {
		this.fenduan = fenduan;
	}
	public String getLxren() {
		return lxren;
	}
	public void setLxren(String lxren) {
		this.lxren = lxren;
	}
	public String getLxr_phone() {
		return lxr_phone;
	}
	public void setLxr_phone(String lxr_phone) {
		this.lxr_phone = lxr_phone;
	}
	public double getJzhumj() {
		return jzhumj;
	}
	public void setJzhumj(double jzhumj) {
		this.jzhumj = jzhumj;
	}
	public String getJfdate() {
		return jfdate;
	}
	public void setJfdate(String jfdate) {
		this.jfdate = jfdate;
	}
	public double getJfmoney() {
		return jfmoney;
	}
	public void setJfmoney(double jfmoney) {
		this.jfmoney = jfmoney;
	}
	public String getDjtime() {
		return djtime;
	}
	public void setDjtime(String djtime) {
		this.djtime = djtime;
	}
	public String getDjren() {
		return djren;
	}
	public void setDjren(String djren) {
		this.djren = djren;
	}
	public String getDjrenid() {
		return djrenid;
	}
	public void setDjrenid(String djrenid) {
		this.djrenid = djrenid;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	@Transient
	public String getJfbzdesc() {
		return jfbzdesc;
	}
	public void setJfbzdesc(String jfbzdesc) {
		this.jfbzdesc = jfbzdesc;
	}

	public String getQcsyqk() {
		return qcsyqk;
	}

	public void setQcsyqk(String qcsyqk) {
		this.qcsyqk = qcsyqk;
	}

	public String getFhblqk() {
		return fhblqk;
	}

	public void setFhblqk(String fhblqk) {
		this.fhblqk = fhblqk;
	}

	public String getCompname() {
		return compname;
	}

	public void setCompname(String compname) {
		this.compname = compname;
	}
	@Transient
	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}
	@Transient
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Transient
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Transient
	public String getXuhao() {
		return xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
}