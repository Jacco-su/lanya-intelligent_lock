package com.dream.brick.equipment.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品基本情况
 * 
 * @author maolei
 * @date 2016-02-08
 */
@Entity
@Table(name = "t_product")
public class Product implements Serializable {
	private String id;
	private String name;//产品名称
	private String zxbz;//执行标准
	private String jyjgmc;//产品检验地区名称
	private String jytime;//检验时间
	private String ycl_one;//主要原材料1
	private String ly_one;//来源
	private String ycl_two;
	private String ly_two;
	private String ycl_three;
	private String ly_three;
	private String zlbzsm; //企业质量保证<br/>体系说明
	private String attr1;//县（市）区墙改管理地区审查意见
	private String atime1;//审核日期
	private String attr2;//质量保证<br/>体系和现场<br/>考核意见
	private String uname2;//考核人
	private String attr3;//省辖市确认委员会推荐意见
	private String uname3;//签字
	private String atime3;//签字日期4
	private String attr4;//省辖市墙改<br/>管理地区<br/>初审意见<br/>
	private String atime4 ;//签字日期4
	private String attr5;//外省企业<br/>省级墙改<br/>管理地区<br/>审核意见
	private String atime5;
	private String attr6;//产品表示
	private String ownerid;//登记人
	private String owname;//登记人姓名
	private String create_time;//登记时间
	private String orgname;//登记地区名称
	private String orgid;//登记地区ID
	private String typeid;//墙材类型ID
	private String typedesc;//墙材类型描述
	private Company company;//所属公司
	private String qrzsbh;//确认（认定）证书编号
	private String produnit;


	//extends
	private String ptype;//产品类型
	private String files;//产品标示图片
	private String scgyqk;//生产工艺,<br/>技术装备和<br/>产能升级情<br/>况
    private String validBeginTime;//有效期
	private String validEndTime;//有效期
	private String scnl;//生产能力
	private String tcyy;//停产原因
    private String qrtime;//确认时间
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
	public String getZxbz() {
		return zxbz;
	}
	public void setZxbz(String zxbz) {
		this.zxbz = zxbz;
	}
	public String getJyjgmc() {
		return jyjgmc;
	}
	public void setJyjgmc(String jyjgmc) {
		this.jyjgmc = jyjgmc;
	}
	public String getJytime() {
		return jytime;
	}
	public void setJytime(String jytime) {
		this.jytime = jytime;
	}
	public String getYcl_one() {
		return ycl_one;
	}
	public void setYcl_one(String ycl_one) {
		this.ycl_one = ycl_one;
	}
	public String getLy_one() {
		return ly_one;
	}
	public void setLy_one(String ly_one) {
		this.ly_one = ly_one;
	}
	public String getYcl_two() {
		return ycl_two;
	}
	public void setYcl_two(String ycl_two) {
		this.ycl_two = ycl_two;
	}
	public String getLy_two() {
		return ly_two;
	}
	public void setLy_two(String ly_two) {
		this.ly_two = ly_two;
	}
	public String getYcl_three() {
		return ycl_three;
	}
	public void setYcl_three(String ycl_three) {
		this.ycl_three = ycl_three;
	}
	public String getLy_three() {
		return ly_three;
	}
	public void setLy_three(String ly_three) {
		this.ly_three = ly_three;
	}
	public String getZlbzsm() {
		return zlbzsm;
	}
	public void setZlbzsm(String zlbzsm) {
		this.zlbzsm = zlbzsm;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAtime1() {
		return atime1;
	}
	public void setAtime1(String atime1) {
		this.atime1 = atime1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getUname2() {
		return uname2;
	}
	public void setUname2(String uname2) {
		this.uname2 = uname2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
	public String getUname3() {
		return uname3;
	}
	public void setUname3(String uname3) {
		this.uname3 = uname3;
	}
	public String getAtime3() {
		return atime3;
	}
	public void setAtime3(String atime3) {
		this.atime3 = atime3;
	}
	public String getAttr4() {
		return attr4;
	}
	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}
	public String getAtime4() {
		return atime4;
	}
	public void setAtime4(String atime4) {
		this.atime4 = atime4;
	}
	public String getAttr5() {
		return attr5;
	}
	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}
	public String getAttr6() {
		return attr6;
	}
	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getOwname() {
		return owname;
	}
	public void setOwname(String owname) {
		this.owname = owname;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	@Transient
	public String getTypedesc() {
		return typedesc;
	}
	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false, updatable = false)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getAtime5() {
		return atime5;
	}
	public void setAtime5(String atime5) {
		this.atime5 = atime5;
	}
	public String getQrzsbh() {
		return qrzsbh;
	}
	public void setQrzsbh(String qrzsbh) {
		this.qrzsbh = qrzsbh;
	}
	public String getProdunit() {
		return produnit;
	}
	public void setProdunit(String produnit) {
		this.produnit = produnit;
	}
    @Column(name = "pinfo")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getScgyqk() {
		return scgyqk;
	}

	public void setScgyqk(String scgyqk) {
		this.scgyqk = scgyqk;
	}

	public String getValidEndTime() {
		return validEndTime;
	}

	public void setValidEndTime(String validEndTime) {
		this.validEndTime = validEndTime;
	}

	public String getValidBeginTime() {
		return validBeginTime;
	}

	public void setValidBeginTime(String validBeginTime) {
		this.validBeginTime = validBeginTime;
	}

	public String getScnl() {
		return scnl;
	}

	public void setScnl(String scnl) {
		this.scnl = scnl;
	}

	public String getTcyy() {
		return tcyy;
	}

	public void setTcyy(String tcyy) {
		this.tcyy = tcyy;
	}

	public String getQrtime() {
		return qrtime;
	}

	public void setQrtime(String qrtime) {
		this.qrtime = qrtime;
	}
}
