package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *现场核查
 * 
 * @author maolei
 * @date 2017-12-10 17:50
 */
@Entity
@Table(name = "t_xccheck")
@PropertyNote(name = "现场核查统计")
public class Xccheck {
	private String id;
	@PropertyNote(name = "序号")
	private String xuhao;//序号
	@PropertyNote(name = "申请日期")
	private String sqdate;//申请日期
	@PropertyNote(name = "建设单位")
	private String builder;//建设单位<br/>（开发商）
	@PropertyNote(name = "项目名称")
	private String projname;//项目名称，该字段是方便显示
	@PropertyNote(name = "工程地址")
	private String address;
	@PropertyNote(name = "核查面积（㎡）")
	private double checkmj;//核查面积
	@PropertyNote(name = "联系人")
	private String lxren;//联系人
	private String lxr_phone;//联系人手机
	@PropertyNote(name = "核查人员")
	private String check_user;//核查人员
	@PropertyNote(name = "核查日期")
	private String check_date;//核查日期
	private String djtime ;//登记时间
	private String djren;//登记人
	private String djrenid;//登记人ID
	private Project project;//对应项目
    //@PropertyNote(name = "审批人")
	private String spren;//经办审核人
	@PropertyNote(name = "备注")
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
	public String getSqdate() {
		return sqdate;
	}
	public void setSqdate(String sqdate) {
		this.sqdate = sqdate;
	}
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	public double getCheckmj() {
		return checkmj;
	}
	public void setCheckmj(double checkmj) {
		this.checkmj = checkmj;
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
	public String getCheck_user() {
		return check_user;
	}
	public void setCheck_user(String check_user) {
		this.check_user = check_user;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
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
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false, updatable = true)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	public String getSpren() {
		return spren;
	}

	public void setSpren(String spren) {
		this.spren = spren;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Transient
	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}
}
