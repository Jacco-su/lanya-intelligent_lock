package com.dream.brick.equipment.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 现场核查  墙材使用
 * 
 * @author maolei
 * @date 2017-12-19 14:35
 */
@Entity
@Table(name = "t_xc_prod")
public class Detailprod {
	private String id;
	private String prodname;//墙材名称 该字段是冗余的,方便显示
	private CheckDetail checkDetail;//对应现场核查明细
	private Product product;//对应产品
	private String produnit;//计量单位
	private int gycount;//供应数量
	private String qrzsbh;//确认（认定）证书编号
	
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "xcdetail_id", nullable = false, updatable = true)
	public CheckDetail getCheckDetail() {
		return checkDetail;
	}
	public void setCheckDetail(CheckDetail checkDetail) {
		this.checkDetail = checkDetail;
	}
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false, updatable = true)
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProdunit() {
		return produnit;
	}
	public void setProdunit(String produnit) {
		this.produnit = produnit;
	}
	public int getGycount() {
		return gycount;
	}
	public void setGycount(int gycount) {
		this.gycount = gycount;
	}
	public String getQrzsbh() {
		return qrzsbh;
	}
	public void setQrzsbh(String qrzsbh) {
		this.qrzsbh = qrzsbh;
	}
	
}
