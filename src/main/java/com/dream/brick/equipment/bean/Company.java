package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 企业基本情况
 * 
 * @author maolei
 * @date 2017-12-05
 */
@Entity
@Table(name = "t_company")
@PropertyNote(name = "公司统计")
public class Company implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 552794295488839980L;
	private String id;
	@PropertyNote(name = "企业名称")
	private String name;
	@PropertyNote(name = "法人代表")
	private String frdb;//法人代表
	private String fr_zhiwu;//法人职务或职称
	@PropertyNote(name = "联系电话")
	private String fr_phone;//法人电话
	private String js_fzr;//技术负责人
	private String js_zhiwu;//职务或职称
	private String js_phone;//电  话
	@PropertyNote(name = "联系人")
	private String lxren;//联 系 人
	private String lxr_zhiwu;//联 系 人职务
	@PropertyNote(name = "联系电话")
	private String lxr_phone;//联 系 人电  话
	@PropertyNote(name = "企业地址")
	private String address;
	@PropertyNote(name = "邮政编码")
	private String yzcode;
	private String zzbfjg;//营业执照颁发机关
	private String zc_hao;//注册号
	private String zz_bftime;//颁发时间
	private String qypzjg;//设立企业<br/>批准机关
	private String pzwenhao;//批准文号
	private String pz_time;//批准时间
	private String yd_pzjg;//建厂用地批准机关
	private String yd_pzwh;//用地批准文号
	private String yd_pztime;//用地批准文号批准时间
    private String hj_pjjg;//环境影响<br/>评价区域
    private String hj_pzwh;//批准文号
	private String hj_pjtime;//批准时间
	private String scnl;//生产能力
	private String cl_one;//近三年产品<br/>产量1
	private String cl_two;//近三年产品<br/>产量2
	private String cl_three;//近三年产品<br/>产量3
	private String eq_one;//主要生产<br/>设备名称1
	private String eq_one_xh;//型号
	private String eq_one_zzs;//制造商
	private String eq_two;
	private String eq_two_xh;
	
	private String eq_three;
	private String eq_three_zzs;
	private String eq_three_xh;
	private String eq_two_zzs;
	private String hypx_time;//培训时间
	private String hypx_content;//参加行业<br/>培训时间
	private String djren;
	private String djtime;
	private String djrenid;
	private String areacode;//企业所在地区编码
	private String areaname;//企业所在地区名称
    private String orgid;//所属区域ID
    private String orgname;//所属区域名称

	//extents
	private String scgyqk;//生产工艺,<br/>技术装备和<br/>产能升级情<br/>况
	private String zlbzsm;//企业质量<br/>保证体系<br/>执行情况
	private String qyjbqk;//企业基本情况
	private String qy_phone;//企业负责人电话
	private String qy_fzr;//企业负责人
	private String qy_zhiwu;//企业负责人职务

	//extends excel
	@PropertyNote(name = "产品名称")
	private String productName;
	@PropertyNote(name = "执行标准")
	private String zxbz;
	@PropertyNote(name = "确认证书编号")
	private String qrzsbh;
	@PropertyNote(name = "有效期")
	private String validBeginTime;
	/*@PropertyNote(name = "产品名称")
	private String productName1;
	@PropertyNote(name = "执行标准")
	private String zxbz1;
	@PropertyNote(name = "确认证书编号")
	private String qrzsbh1;
	@PropertyNote(name = "有效期")
	private String validBeginTime1;*/

	//private List<ExtendsCompany> companies;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYzcode() {
		return yzcode;
	}
	public void setYzcode(String yzcode) {
		this.yzcode = yzcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFrdb() {
		return frdb;
	}
	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}
	public String getFr_zhiwu() {
		return fr_zhiwu;
	}
	public void setFr_zhiwu(String fr_zhiwu) {
		this.fr_zhiwu = fr_zhiwu;
	}
	public String getFr_phone() {
		return fr_phone;
	}
	public void setFr_phone(String fr_phone) {
		this.fr_phone = fr_phone;
	}
	public String getJs_fzr() {
		return js_fzr;
	}
	public void setJs_fzr(String js_fzr) {
		this.js_fzr = js_fzr;
	}
	public String getJs_zhiwu() {
		return js_zhiwu;
	}
	public void setJs_zhiwu(String js_zhiwu) {
		this.js_zhiwu = js_zhiwu;
	}
	public String getJs_phone() {
		return js_phone;
	}
	public void setJs_phone(String js_phone) {
		this.js_phone = js_phone;
	}
	public String getLxren() {
		return lxren;
	}
	public void setLxren(String lxren) {
		this.lxren = lxren;
	}
	public String getLxr_zhiwu() {
		return lxr_zhiwu;
	}
	public void setLxr_zhiwu(String lxr_zhiwu) {
		this.lxr_zhiwu = lxr_zhiwu;
	}
	public String getLxr_phone() {
		return lxr_phone;
	}
	public void setLxr_phone(String lxr_phone) {
		this.lxr_phone = lxr_phone;
	}
	public String getZzbfjg() {
		return zzbfjg;
	}
	public void setZzbfjg(String zzbfjg) {
		this.zzbfjg = zzbfjg;
	}
	public String getZc_hao() {
		return zc_hao;
	}
	public void setZc_hao(String zc_hao) {
		this.zc_hao = zc_hao;
	}
	public String getZz_bftime() {
		return zz_bftime;
	}
	public void setZz_bftime(String zz_bftime) {
		this.zz_bftime = zz_bftime;
	}
	public String getQypzjg() {
		return qypzjg;
	}
	public String getPzwenhao() {
		return pzwenhao;
	}
	public void setPzwenhao(String pzwenhao) {
		this.pzwenhao = pzwenhao;
	}
	public String getPz_time() {
		return pz_time;
	}
	public void setPz_time(String pz_time) {
		this.pz_time = pz_time;
	}
	public String getYd_pzjg() {
		return yd_pzjg;
	}
	public void setYd_pzjg(String yd_pzjg) {
		this.yd_pzjg = yd_pzjg;
	}
	public String getYd_pztime() {
		return yd_pztime;
	}
	public void setYd_pztime(String yd_pztime) {
		this.yd_pztime = yd_pztime;
	}
	public String getHj_pjjg() {
		return hj_pjjg;
	}
	public void setHj_pjjg(String hj_pjjg) {
		this.hj_pjjg = hj_pjjg;
	}
	public String getHj_pzwh() {
		return hj_pzwh;
	}
	public void setHj_pzwh(String hj_pzwh) {
		this.hj_pzwh = hj_pzwh;
	}
	public String getHj_pjtime() {
		return hj_pjtime;
	}
	public void setHj_pjtime(String hj_pjtime) {
		this.hj_pjtime = hj_pjtime;
	}
	public String getScnl() {
		return scnl;
	}
	public void setScnl(String scnl) {
		this.scnl = scnl;
	}
	public String getCl_one() {
		return cl_one;
	}
	public void setCl_one(String cl_one) {
		this.cl_one = cl_one;
	}
	public String getCl_two() {
		return cl_two;
	}
	public void setCl_two(String cl_two) {
		this.cl_two = cl_two;
	}
	public String getCl_three() {
		return cl_three;
	}
	public void setCl_three(String cl_three) {
		this.cl_three = cl_three;
	}
	public String getEq_one() {
		return eq_one;
	}
	public void setEq_one(String eq_one) {
		this.eq_one = eq_one;
	}
	public String getEq_one_xh() {
		return eq_one_xh;
	}
	public void setEq_one_xh(String eq_one_xh) {
		this.eq_one_xh = eq_one_xh;
	}
	public String getEq_one_zzs() {
		return eq_one_zzs;
	}
	public void setEq_one_zzs(String eq_one_zzs) {
		this.eq_one_zzs = eq_one_zzs;
	}
	public String getEq_two() {
		return eq_two;
	}
	public void setEq_two(String eq_two) {
		this.eq_two = eq_two;
	}
	public String getEq_two_xh() {
		return eq_two_xh;
	}
	public void setEq_two_xh(String eq_two_xh) {
		this.eq_two_xh = eq_two_xh;
	}
	public String getEq_three() {
		return eq_three;
	}
	public void setEq_three(String eq_three) {
		this.eq_three = eq_three;
	}
	public String getEq_three_zzs() {
		return eq_three_zzs;
	}
	public void setEq_three_zzs(String eq_three_zzs) {
		this.eq_three_zzs = eq_three_zzs;
	}
	public String getEq_three_xh() {
		return eq_three_xh;
	}
	public void setEq_three_xh(String eq_three_xh) {
		this.eq_three_xh = eq_three_xh;
	}
	public String getEq_two_zzs() {
		return eq_two_zzs;
	}
	public void setEq_two_zzs(String eq_two_zzs) {
		this.eq_two_zzs = eq_two_zzs;
	}
	public String getHypx_time() {
		return hypx_time;
	}
	public void setHypx_time(String hypx_time) {
		this.hypx_time = hypx_time;
	}
	public String getHypx_content() {
		return hypx_content;
	}
	public void setHypx_content(String hypx_content) {
		this.hypx_content = hypx_content;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYd_pzwh() {
		return yd_pzwh;
	}
	public void setYd_pzwh(String yd_pzwh) {
		this.yd_pzwh = yd_pzwh;
	}
	public void setQypzjg(String qypzjg) {
		this.qypzjg = qypzjg;
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


	public String getScgyqk() {
		return scgyqk;
	}

	public void setScgyqk(String scgyqk) {
		this.scgyqk = scgyqk;
	}

	public String getZlbzsm() {
		return zlbzsm;
	}

	public void setZlbzsm(String zlbzsm) {
		this.zlbzsm = zlbzsm;
	}

	public String getQyjbqk() {
		return qyjbqk;
	}

	public void setQyjbqk(String qyjbqk) {
		this.qyjbqk = qyjbqk;
	}

	public String getQy_phone() {
		return qy_phone;
	}

	public void setQy_phone(String qy_phone) {
		this.qy_phone = qy_phone;
	}

	public String getQy_fzr() {
		return qy_fzr;
	}

	public void setQy_fzr(String qy_fzr) {
		this.qy_fzr = qy_fzr;
	}

	public String getQy_zhiwu() {
		return qy_zhiwu;
	}

	public void setQy_zhiwu(String qy_zhiwu) {
		this.qy_zhiwu = qy_zhiwu;
	}

}
