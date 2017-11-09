package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Company;
import com.dream.brick.equipment.bean.Product;
import com.dream.brick.equipment.dao.ProductDao;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class ProductDaoImpl   extends BaseDaoImpl implements ProductDao{
	public List<Product> findProductList(Pager pager){
		 String areacode=pager.getParamValue("areacode");
		String name=pager.getParamValue("name");
		String compname=pager.getParamValue("compname");
		String tcyy=pager.getParamValue("tcyy");
		 //地区编码，不同地区只能查看自己管辖的地区数据			
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.name,t.typeid,t.qrzsbh,t.produnit,t.company.id,t.company.name,t.ptype,t.orgname,t.validBeginTime,t.validEndTime,t.company.address,t.scnl,t.zxbz from Product t where ");
		sb.append(" t.company.areacode like '").append(areacode).append("%' ");
		if(StringUtils.isNotEmpty(name)){
			sb.append(" and t.name like '%").append(name).append("%' ");
		}
		if(StringUtils.isNotEmpty(compname)){
			sb.append(" and t.company.name like '%").append(compname).append("%' ");
		}
		if (StringUtils.isNotEmpty(tcyy)) {
			if("0".equals(tcyy)){
				sb.append(" and t.tcyy ='' ");
			}else{
				sb.append(" and t.tcyy !=''");
			}
		}
		sb.append(" order by t.company.areacode asc,t.create_time desc");
		String hql=sb.toString();
		List<Object[]> results=query(hql,pager);
		List<Product> prodList=new ArrayList<Product>();
		for(Object[] objs:results){
			Product prod=new Product();
			prod.setId((String)objs[0]);
			prod.setName((String)objs[1]);
			prod.setTypeid((String)objs[2]);
			prod.setPtype((String)objs[7]);
			prod.setQrzsbh((String)objs[3]);
			prod.setProdunit((String)objs[4]);
			prod.setValidBeginTime((String)objs[9]);
			prod.setValidEndTime((String)objs[10]);
			prod.setScnl((String)objs[12]);
			prod.setZxbz((String)objs[13]);

			Company company=new Company();
			company.setId((String)objs[5]);
			company.setName((String)objs[6]);
			prod.setCompany(company);
			company.setAreaname((String)objs[8]);
			company.setAddress((String)objs[11]);
			prodList.add(prod);
		}
		return prodList;
	}
	/**
	 * 该方法是选择墙材的时候用
	 * **/
	public List<Product> searchProductList(Pager pager){
		String companyId=pager.getParamValue("companyId");
		String name=pager.getParamValue("name");
		 String areacode=pager.getParamValue("areacode");
		 //地区编码，不同地区只能查看自己管辖的地区数据			
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.name,t.typeid,t.qrzsbh,t.produnit,t.company.id,t.company.name from Product t where 1=1 ");
		if(companyId!=null&&!"".equals(companyId)){
			sb.append(" and t.company.id='").append(companyId).append("' ");
		}
		if(name!=null&&!"".equals(name)){
			sb.append(" and t.name like '%").append(name).append("%' ");
		}
		sb.append(" and t.company.areacode like '").append(areacode).append("%' ");
		sb.append(" order by t.company.areacode asc,t.create_time desc");
		String hql=sb.toString();
		List<Object[]> results=query(hql,pager);
		List<Product> prodList=new ArrayList<Product>();
		for(Object[] objs:results){
			Product prod=new Product();
			prod.setId((String)objs[0]);
			prod.setName((String)objs[1]);
			prod.setTypeid((String)objs[2]);
			
			prod.setQrzsbh((String)objs[3]);
			prod.setProdunit((String)objs[4]);
			
			Company company=new Company();
			company.setId((String)objs[5]);
			company.setName((String)objs[6]);

			
			prod.setCompany(company);
			prodList.add(prod);
		}
		return prodList;
	}
	public Product findBasicProduct(String id){
		String hql="select t.id,t.name,t.typeid,t.qrzsbh,t.company.name  from Product t where t.id=? ";
		List<Object[]> results=query(hql,0,0,id);
		if(results.size()==0){
			return null;
		}
		Object[] objs=results.get(0);
		Product prod=new Product();
		prod.setId((String)objs[0]);
		prod.setName((String)objs[1]);
		prod.setTypeid((String)objs[2]);
		prod.setTypedesc("");
		prod.setQrzsbh((String)objs[3]);
		Company company=new Company();
		company.setName((String)objs[4]);
		prod.setCompany(company);
		return prod;
	}

	@Override
	public List<Product> searchProductList(String id) {
		return findList("from Product p where p.company.id='"+id+"'");
	}
	@Override
	public Product searchProductQrzsbh(String qrzsbh) {
		List<Product> productList=findList("from Product p where p.qrzsbh='"+qrzsbh+"'");
		if(productList.size()>0){
			return productList.get(0);
		}else{
		return null;
		}
	}

	@Override
	public Product searchProduct(Map<String, String> params) {
		String name=params.get("name");
		String compname=params.get("compName");
		//地区编码，不同地区只能查看自己管辖的地区数据
		StringBuilder sb=new StringBuilder();
		sb.append(" from Product t where ");
		if(StringUtils.isNotEmpty(name)){
			sb.append(" t.name = '").append(name).append("' ");
		}
		if(StringUtils.isNotEmpty(compname)){
			sb.append(" and t.company.name = '").append(compname).append("' ");
		}
		String hql=sb.toString();
		List<Product> productList=findList(hql);
		if(productList.size()>0){
			Product product= productList.get(0);
			return product;
		}
		return null;
	}
}
