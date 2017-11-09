package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Company;
import com.dream.brick.equipment.bean.ExtendsCompany;
import com.dream.brick.equipment.bean.Product;
import com.dream.brick.equipment.dao.CompanyDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class CompanyDaoImpl  extends BaseDaoImpl implements CompanyDao{
	public List<Company> findAllCompany(String areacode){

		//地区编码，不同地区只能查看自己管辖的地区数据
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.name,t.frdb,t.fr_phone,t.orgname,t.areaname,t.address,t.yzcode ");
		sb.append(" from Company t where t.areacode like '").append(areacode).append("%' ");
		sb.append(" order by t.areacode asc,t.djtime asc");
		String hql=sb.toString();
		List<Company> compList=findList(hql);
		return compList;
	}
	public List<Company> findAllCompany(){

		//地区编码，不同地区只能查看自己管辖的地区数据
		StringBuilder sb=new StringBuilder();
		//sb.append("select t.id,t.name,t.frdb,t.fr_phone,t.orgname,t.areaname,t.address,t.yzcode ");
		sb.append(" from Company t ");
		sb.append(" order by t.djtime asc");
		String hql=sb.toString();
		List<Company> compList=findList(hql);
		return compList;
	}
	public List<Company> findCompanyByAreacode(String areacode){
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.name,t.orgname,t.areaname from Company t ");
		sb.append(" where t.areacode like '").append(areacode).append("%' ");
		sb.append(" order by t.areacode asc,t.djtime asc");
		String hql=sb.toString();
		List<Object[]> results=findList(hql);
		List<Company> compList=new ArrayList<Company>();
		for(Object[] objs:results){
			Company comp=new Company();
			comp.setId((String)objs[0]);
			comp.setName((String)objs[1]);
			comp.setOrgname((String)objs[2]);
			comp.setAreaname((String)objs[3]);
			compList.add(comp);
		}
		return compList;
	}
	public List<Company> findCompanyList(Pager pager){
		 String areacode=pager.getParamValue("areacode");
		String keyword=pager.getParamValue("keyword");
		String djtime=pager.getParamValue("djtime");
		String areaName=pager.getParamValue("areaName");
		 //地区编码，不同地区只能查看自己管辖的地区数据		
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.name,t.frdb,t.fr_phone,t.orgname,t.areaname,t.address,t.yzcode,t.djtime,t.eq_one,t.eq_one_xh,t.eq_one_zzs");
		sb.append(",t.lxren,t.lxr_phone");
		sb.append(" from Company t where t.areacode like '").append(areacode).append("%' ");
		if(StringUtils.isNotEmpty(keyword)){
			sb.append(" and t.name like '%").append(keyword).append("%' ");
		}
		if(StringUtils.isNotEmpty(keyword)){
			keyword=keyword.replaceAll("'", "").replaceAll("\"", "").trim();
			sb.append(" and t.name like '%").append(keyword).append("%' ");
		}
		if(StringUtils.isNotEmpty(djtime)){
			sb.append(" and t.djtime like '").append(djtime).append("%' ");
		}
		if(StringUtils.isNotEmpty(areaName)){
			sb.append(" and t.address like '%").append(areaName).append("%' ");
		}
		sb.append(" order by t.areacode asc,t.djtime asc");
		String hql=sb.toString();
		List<Object[]> results=query(hql,pager);
		List<Company> compList=new ArrayList<Company>();
		for(Object[] objs:results){
			Company comp=new Company();
			comp.setId((String)objs[0]);
			comp.setName((String)objs[1]);
			comp.setFrdb((String)objs[2]);
			comp.setFr_phone((String)objs[3]);
			comp.setOrgname((String)objs[4]);
			comp.setAreaname((String)objs[5]);
			comp.setAddress((String)objs[6]);
			comp.setYzcode((String)objs[7]);
			comp.setDjtime((String)objs[8]);
			comp.setEq_one((String)objs[9]);
			comp.setEq_one_xh((String)objs[10]);
			comp.setEq_one_zzs((String)objs[11]);
			comp.setLxren((String)objs[12]);
			comp.setLxr_phone((String)objs[13]);
			compList.add(comp);
		}
		return compList;
	}

	@Override
	public Company getCompanyByName(String name) {
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.name,t.orgname,t.areaname from Company t ");
		sb.append(" where t.name ='").append(name).append("' ");
		sb.append(" order by t.areacode asc,t.djtime asc");
		String hql=sb.toString();
		List<Object[]> results=findList(hql);
		if(results!=null&&results.size()>0){
			Object[] objs=results.get(0);
			Company comp=new Company();
			comp.setId((String)objs[0]);
			comp.setName((String)objs[1]);
			comp.setOrgname((String)objs[2]);
			comp.setAreaname((String)objs[3]);
			return comp;
		}
		return null;
	}
	public Map<String,String> cacheCompanyName(){
		Map nameMap=new HashMap<String,String>();
		String hql="select id,name from Company";
		List<Object[]> list=findList(hql);
		for(Object[] objs:list){
			nameMap.put((String)objs[0], (String)objs[1]);
		}
		return nameMap;
	}
	public Map<String,String> cacheProdName(){
		Map nameMap=new HashMap<String,String>();
		String hql="select id,name,qrzsbh,produnit from Product";
		List<Object[]> list=findList(hql);
		for(Object[] objs:list){
			nameMap.put((String)objs[0], (String)objs[1]);
			nameMap.put("qrzsbh"+(String)objs[0], (String)objs[2]);
			nameMap.put("produnit"+(String)objs[0], (String)objs[3]);
		}
		return nameMap;
	}
	public List<Object[]> searchCompany(Map<String, String> params) throws Exception {
		/**
		 * @author       陶乐乐(wangyiqianyi@qq.com)
		 * @Description: 企业信息搜索
		 * @date         2016-03-29 16:30:49
		 * @params       [params]
		 * @return       java.util.List<com.dream.brick.equipment.bean.Company>
		 * @throws       Exception
		 */

		/*sb.append("select t.id,t.name,t.frdb,t.fr_phone,t.lxren,t.lxr_phone,t.address,t.yzcode");
		sb.append(" from Company t where t.areacode  like '%").append(areaCode).append("'");
		sb.append(" order by p.srdate asc");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		sqlQuery.addScalar("frdb", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fr_phone", StandardBasicTypes.STRING);
		sqlQuery.addScalar("lxren", StandardBasicTypes.STRING);
		sqlQuery.addScalar("lxr_phone", StandardBasicTypes.STRING);
		sqlQuery.addScalar("address", StandardBasicTypes.STRING);
		sqlQuery.addScalar("yzcode", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers
				.aliasToBean(Company.class));*/
		List<Object[]> objs=new ArrayList<>();
		String areaCode=params.get("areacode");
		StringBuilder sb=new StringBuilder("");
		/*sb.append("from Company t where t.areacode  like '%").append(areaCode).append("'");
		List<Company> listCompany= findList(sb.toString());

		StringBuilder sb2=new StringBuilder("");
		sb2.append("from Product t where t.company.areacode like '%").append(areaCode).append("'");

		List<Product> productList=  findList(sb2.toString());

		//List<Company> companyList=new ArrayList<>();


		for (int i = 0; i <listCompany.size() ; i++) {
			Company company=listCompany.get(i);
			List<ExtendsCompany> exCompanyList=new ArrayList<>();
			for (int j = 0; j <productList.size() ; j++) {
				Product p=productList.get(j);
				if(p.getCompany().getId().equals(company.getId())){
					ExtendsCompany exCompany=new ExtendsCompany();
					exCompany.setProductName(p.getName());
					exCompany.setZxbz(p.getZxbz());
					exCompany.setQrzsbh(p.getQrzsbh());
					exCompany.setValidBeginTime(p.getValidBeginTime()+"至"+p.getValidEndTime());
					exCompanyList.add(exCompany);
				}
			}
			Object[] obj=new Object[50];
			obj[0]=company.getName();
			obj[1]=company.getFrdb();
			obj[2]=company.getFr_phone();
			obj[3]=company.getLxren();
			obj[4]=company.getLxr_phone();
			obj[5]=company.getAddress();
			obj[6]=company.getYzcode();
			for (int j = 0; j <exCompanyList.size() ; j++) {
				ExtendsCompany exCompany=exCompanyList.get(j);
				obj[6+j*4+1]=exCompany.getProductName();
				obj[6+j*4+2]=exCompany.getZxbz();
				obj[6+j*4+3]=exCompany.getQrzsbh();
				obj[6+j*4+4]=exCompany.getValidBeginTime();
			}
			objs.add(obj);
			//company.setCompanies(exCompanyList);
			//companyList.add(company);
		}*/
		return objs;
	}
}
