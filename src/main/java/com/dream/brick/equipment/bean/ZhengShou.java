package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 基金征收
 * 
 * @author maolei
 * @date 2016-02-09 16:10
 */
@Entity
@Table(name = "t_zhengshou")
@PropertyNote(name = "基金征收统计")
public class ZhengShou {
	private String id;
	@PropertyNote(name = "序号")
	private String xuhao;//序号
	@PropertyNote(name = "预收日期")
	private String zsdate;//征收日期
	@PropertyNote(name = "报建编号")
	private String bjbh;//报建编号
	private Project project;//对应项目
	private String djtime ;//添加时间
	private String djren;//登记人
	private String djrenid;//登记人id
	@PropertyNote(name ="建设单位" )
	private String compname;//建设单位（开发商）
	private String projname;//项目名称


	@PropertyNote(name = "项目名称")
	private String projectName;

	@PropertyNote(name = "项目地址")
	private String projectAddress;
	@PropertyNote(name = "报建面积")
	private double bjmj;//报建面积
    @PropertyNote(name = "应缴基金")
	private double jijin;//应缴基金
	@PropertyNote(name = "联系人")
	private String lxren;//联系人
	@PropertyNote(name = "电话")
	private String lxr_phone;//电话
	@PropertyNote(name = "缴款通知书票据号")
	private String jktzspjh;//缴款通知书票据号
	@PropertyNote(name = "收入票据票据号")
	private String srpjpjh;//收入票据票据号
	@PropertyNote(name = "收入票据机打日期")
	private String srpjjdrq;//收入票据机打日期
	//extends
	private String lxren2;//联系人
	private String lxr_phone2;//电话

	private String areacode;//所在地区编码
	private String areaname;//所在地区名称
	private String remark;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	public String getZsdate() {
		return zsdate;
	}
	public void setZsdate(String zsdate) {
		this.zsdate = zsdate;
	}
	public String getBjbh() {
		return bjbh;
	}
	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false, updatable = true)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public double getBjmj() {
		return bjmj;
	}
	public void setBjmj(double bjmj) {
		this.bjmj = bjmj;
	}
	public double getJijin() {
		return jijin;
	}
	public void setJijin(double jijin) {
		this.jijin = jijin;
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
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}

	public String getLxr_phone2() {
		return lxr_phone2;
	}

	public void setLxr_phone2(String lxr_phone2) {
		this.lxr_phone2 = lxr_phone2;
	}

	public String getLxren2() {
		return lxren2;
	}

	public void setLxren2(String lxren2) {
		this.lxren2 = lxren2;
	}

	public String getJktzspjh() {
		return jktzspjh;
	}

	public void setJktzspjh(String jktzspjh) {
		this.jktzspjh = jktzspjh;
	}

	public String getSrpjjdrq() {
		return srpjjdrq;
	}

	public void setSrpjjdrq(String srpjjdrq) {
		this.srpjjdrq = srpjjdrq;
	}

	public String getSrpjpjh() {
		return srpjpjh;
	}

	public void setSrpjpjh(String srpjpjh) {
		this.srpjpjh = srpjpjh;
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


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	@Transient
	public String getProjectName() {
		return projectName;
	}
	@Transient
	public String getProjectAddress() {
		return projectAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
