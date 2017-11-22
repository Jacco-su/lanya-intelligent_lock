package com.dream.brick.equipment.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *市级专项基金扶持
 * 
 * @author maolei
 * @date 2017-12-10 10:50
 */
@Entity
@Table(name = "t_fuchi")
public class Fuchi {
	private String id;
	private String project_id;//工程项目
	private String product_id;//产品id
	private String prodname;//产品名称
	private String compname;//墙材企业名称
	private String projname;//工程名称
	private String sbdate;//申报日期
	private String fc_year;//年份
	private String fc_zijin;//扶持资金
	private String item_bt_id;//补贴类别 多项改为单项
	private String item_lz_id;//列支项目
	private String item_bt_desc;//补贴类别 多项描述
	private String item_lz_desc;//列支项目 多项描述
	private String djtime;
	private String djren;
	private String djrenid;

	//extends
	private String projectProgress;//project_progress;项目进展情况
	private String descx;//描述发票
	private String gzwxry;//跟踪问效人员
	private String useFund;//基金使用情况
	private Company company;//公司
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
	public String getSbdate() {
		return sbdate;
	}
	public void setSbdate(String sbdate) {
		this.sbdate = sbdate;
	}
	public String getFc_year() {
		return fc_year;
	}
	public void setFc_year(String fc_year) {
		this.fc_year = fc_year;
	}
	public String getFc_zijin() {
		return fc_zijin;
	}
	public void setFc_zijin(String fc_zijin) {
		this.fc_zijin = fc_zijin;
	}
	public String getItem_bt_id() {
		return item_bt_id;
	}
	public void setItem_bt_id(String item_bt_id) {
		this.item_bt_id = item_bt_id;
	}
	public String getItem_lz_id() {
		return item_lz_id;
	}
	public void setItem_lz_id(String item_lz_id) {
		this.item_lz_id = item_lz_id;
	}
	public String getItem_bt_desc() {
		return item_bt_desc;
	}
	public void setItem_bt_desc(String item_bt_desc) {
		this.item_bt_desc = item_bt_desc;
	}
	public String getItem_lz_desc() {
		return item_lz_desc;
	}
	public void setItem_lz_desc(String item_lz_desc) {
		this.item_lz_desc = item_lz_desc;
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
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
    @Column(name = "project_progress")
	public String getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}

	public String getDescx() {
		return descx;
	}

	public void setDescx(String descx) {
		this.descx = descx;
	}

	public String getGzwxry() {
		return gzwxry;
	}

	public void setGzwxry(String gzwxry) {
		this.gzwxry = gzwxry;
	}
    @Column(name = "use_fund")
	public String getUseFund() {
		return useFund;
	}

	public void setUseFund(String useFund) {
		this.useFund = useFund;
	}
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false, updatable = false)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
