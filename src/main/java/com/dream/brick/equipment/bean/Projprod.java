package com.dream.brick.equipment.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 产品流向统计 墙材使用
 * 
 * @author maolei
 * @date 2016-02-19 10:35
 */
@Entity
@Table(name = "t_projprod")
public class Projprod {
	private String id;
	private String prodname;//墙材名称 该字段是冗余的,方便显示
	private Projflow projflow;//对应产品流向统计单
	private Product product;//产品
	private String produnit;//计量单位
	private double gycount;//供 应 数 量
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
	@JoinColumn(name = "product_id", nullable = false, updatable = true)
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "projflow_id", nullable = false, updatable = true)
	public Projflow getProjflow() {
		return projflow;
	}
	public void setProjflow(Projflow projflow) {
		this.projflow = projflow;
	}
	public String getProdunit() {
		return produnit;
	}
	public void setProdunit(String produnit) {
		this.produnit = produnit;
	}
	public double getGycount() {
		return gycount;
	}
	public void setGycount(double gycount) {
		this.gycount = gycount;
	}
	public String getQrzsbh() {
		return qrzsbh;
	}
	public void setQrzsbh(String qrzsbh) {
		this.qrzsbh = qrzsbh;
	}

}
