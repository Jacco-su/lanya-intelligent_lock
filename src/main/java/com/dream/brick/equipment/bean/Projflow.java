package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 材料产品流向单
 * 
 * @author maolei
 * @date 2017-12-08 15:30
 */
@Entity
@Table(name = "t_projflow")
@PropertyNote(name = "郑州市新型墙材产品流向信息统计表")
public class Projflow implements Serializable {
	private String id;
	private String company_id;//墙材企业
	@PropertyNote(name = "企业名称")
	private String compname;//企业名称   这个字段方便显示及查看
	@PropertyNote(name = "工程名称")
	private String proj_name;//工程名称 方便显示
	private String compaddr;//企业地址 方便显示


	private String lxren;//企业联系人
	private String phone;//企业联系人手机
	private String jbren;//生产单位经办人
	private String jbren_phone;//生产单位经办人手机
	private String djren;
	private String djrenid;
	//private Project project; 停用

	//extends
	@PropertyNote(name = "工程地址")
	private String address;//工程项目所在地址
	@PropertyNote(name = "工程所在区域")
	private String areaname;//所属区域名称
	@PropertyNote(name = "建设单位")
	private String builder;//建设单位<br/>（开发商）
	@PropertyNote(name = "施工单位")
	private String sgdanwei;//施工单位
	@PropertyNote(name = "产品名称")
	private String qcname;//墙材名称
	@PropertyNote(name = "合同量")
	private double qcnum;//墙材数量d
	@PropertyNote(name = "供货日期")
	private String ghbegin;//供货起始日期
	@PropertyNote(name = "编号")
	private String orderx;//序号
    private String htl;//合同量
	@PropertyNote(name = "供货完成日期")
	private String ghend;//供货完成日期
	private String areacode;//areacode
	private String files;//产品标识
	@PropertyNote(name = "登记时间")
	private String djtime;
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getCompaddr() {
		return compaddr;
	}
	public void setCompaddr(String compaddr) {
		this.compaddr = compaddr;
	}
	public String getGhbegin() {
		return ghbegin;
	}
	public void setGhbegin(String ghbegin) {
		this.ghbegin = ghbegin;
	}
	public String getGhend() {
		return ghend;
	}
	public void setGhend(String ghend) {
		this.ghend = ghend;
	}
	public String getLxren() {
		return lxren;
	}
	public void setLxren(String lxren) {
		this.lxren = lxren;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJbren() {
		return jbren;
	}
	public void setJbren(String jbren) {
		this.jbren = jbren;
	}
	public String getJbren_phone() {
		return jbren_phone;
	}
	public void setJbren_phone(String jbren_phone) {
		this.jbren_phone = jbren_phone;
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
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}


	public String getQcname() {
		return qcname;
	}

	public void setQcname(String qcname) {
		this.qcname = qcname;
	}

	public double getQcnum() {
		return qcnum;
	}

	public void setQcnum(double qcnum) {
		this.qcnum = qcnum;
	}

	public String getOrderx() {
		return orderx;
	}

	public void setOrderx(String orderx) {
		this.orderx = orderx;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getHtl() {
		return htl;
	}

	public void setHtl(String htl) {
		this.htl = htl;
	}
}
