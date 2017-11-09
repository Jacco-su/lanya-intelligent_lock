package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *禁粘禁实信息
 * 
 * @author maolei
 * @date 2016-02-20 16:00
 */
@Entity
@Table(name = "t_jinzhan")
@PropertyNote(name="禁黏禁实")
public class Jinzhan {
	
	private String id;


	private String djrenid;
	private String areacode;//企业所在地区编码
	@PropertyNote(name="地区名称")
	private String areaname;//企业所在地区名称
	private String orgid;//所属机构ID
	private String orgname;//所属机构名称
	@PropertyNote(name = "计划年度")
	private int year;
	private String checktime;
	private int type;
	private String content;
	@PropertyNote(name = "详细地址")
	private String address;
	@PropertyNote(name = "登记人")
	private String djren;
	@PropertyNote(name = "录入时间")
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
	public String getDjren() {
		return djren;
	}
	public void setDjren(String djren) {
		this.djren = djren;
	}
	public String getDjtime() {
		return djtime;
	}
	public void setDjtime(String djtime) {
		this.djtime = djtime;
	}
	public String getDjrenid() {
		return djrenid;
	}
	public void setDjrenid(String djrenid) {
		this.djrenid = djrenid;
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
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getChecktime() {
		return checktime;
	}
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
