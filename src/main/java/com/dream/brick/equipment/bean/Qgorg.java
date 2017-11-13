package com.dream.brick.equipment.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 墙改部门
 * 
 * @author maolei
 * @date 2016-02-05
 */
@Entity
@Table(name = "t_qgorg")
public class Qgorg  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4725135016331954412L;
	private String id;
	private String name;//墙改地区名称
	private String address;//墙改地区地址
	private String areacode;//墙改地区所在地区编码
	private String areaname;//墙改地区所在地区名称
	private String phone;//墙改地区手机
	private String telephone;//墙改地区电话
	private int sortorder;////墙改地区显示顺序
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getSortorder() {
		return sortorder;
	}
	public void setSortorder(int sortorder) {
		this.sortorder = sortorder;
	}
	
	
	
}
