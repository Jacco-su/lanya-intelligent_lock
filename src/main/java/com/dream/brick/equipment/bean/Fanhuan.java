package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *专项基金返还
 * 
 * @author maolei
 * @date 2017-12-11 13:50
 */
@Entity
@Table(name = "t_fanhuan")
@PropertyNote(name = "专项基金返还统计")
public class Fanhuan {
	private String id;
	private CheckDetail checkDetail;
	@PropertyNote(name = "序号")
	private String xuhao;//序号
	@PropertyNote(name = "返退编号")
	private String ftbh;//返退编号
	@PropertyNote(name = "档案编号")
	private String danganbx;//档案编号

	private String checkuser;//经办审核人

	private String djtime;//登记时间
	private String djren;//登记人
	private String djrenid;//登记人ID
	private Project project;
	@PropertyNote(name = "建设单位")
	private String builder;//建设单位(开发商)
	@PropertyNote(name = "项目名称")
	private String projname;//工程描述
	@PropertyNote(name = "工程地址")
	private String address;//工程描述
	@PropertyNote(name = "联系人及电话")
	private String lxr;
	@PropertyNote(name = "缴费标准")
	private String jfbz;//缴费标准
	@PropertyNote(name = "缴费日期")
	private String jfdate;//缴费日期
	@PropertyNote(name = "审批面积")
	private double jzhumj;//建筑面积
    @PropertyNote(name = "缴费金额")
	private double jfmoney;//缴费金额
	@PropertyNote(name = "返退金额")
	private double ftmoney;//返退金额
	@PropertyNote(name = "返退比例")
	private String ftbili;//返退比例
	//extends
	@PropertyNote(name = "还退时间")
    private String fttime;//还退时间
	@PropertyNote(name = "备注")
	private String descrip;//备注
	
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
	@JoinColumn(name = "xcdetail_id", nullable = false, updatable = false)
	public CheckDetail getCheckDetail() {
		return checkDetail;
	}
	public void setCheckDetail(CheckDetail checkDetail) {
		this.checkDetail = checkDetail;
	}
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	public String getFtbh() {
		return ftbh;
	}
	public void setFtbh(String ftbh) {
		this.ftbh = ftbh;
	}
	public double getJfmoney() {
		return jfmoney;
	}
	public void setJfmoney(double jfmoney) {
		this.jfmoney = jfmoney;
	}
	public double getFtmoney() {
		return ftmoney;
	}
	public void setFtmoney(double ftmoney) {
		this.ftmoney = ftmoney;
	}
	public String getCheckuser() {
		return checkuser;
	}
	public void setCheckuser(String checkuser) {
		this.checkuser = checkuser;
	}
	public String getFtbili() {
		return ftbili;
	}
	public void setFtbili(String ftbili) {
		this.ftbili = ftbili;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
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
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	public String getBuilder() {
		return builder;
	}
	public void setBuilder(String builder) {
		this.builder = builder;
	}
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false, updatable = false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	public String getFttime() {
		return fttime;
	}

	public void setFttime(String fttime) {
		this.fttime = fttime;
	}
	@Transient
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Transient
	public String getDanganbx() {
		return danganbx;
	}

	public void setDanganbx(String danganbx) {
		this.danganbx = danganbx;
	}
	@Transient
	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	@Transient
	public String getJfbz() {
		return jfbz;
	}

	public void setJfbz(String jfbz) {
		this.jfbz = jfbz;
	}
	@Transient
	public String getJfdate() {
		return jfdate;
	}

	public void setJfdate(String jfdate) {
		this.jfdate = jfdate;
	}
	@Transient
	public double getJzhumj() {
		return jzhumj;
	}

	public void setJzhumj(double jzhumj) {
		this.jzhumj = jzhumj;
	}

}