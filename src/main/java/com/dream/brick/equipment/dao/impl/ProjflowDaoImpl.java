package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Product;
import com.dream.brick.equipment.bean.Projflow;
import com.dream.brick.equipment.bean.Projprod;
import com.dream.brick.equipment.dao.ProjflowDao;
import com.dream.util.StringUtil;
import com.dream.util.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class ProjflowDaoImpl  extends BaseDaoImpl implements ProjflowDao {
	public List<Projflow> findProjFlowList(Pager pager){
		String areacode=pager.getParamValue("areacode");
		String compname=pager.getParamValue("compname");
		String proj_name=pager.getParamValue("proj_name");
		//地区编码，不同地区只能查看自己管辖的地区数据
		String qcname=pager.getParamValue("qcname");
		String ghbegin=pager.getParamValue("ghbegin");
		String ghend=pager.getParamValue("ghend");
		String htl=pager.getParamValue("htl");
		compname= StringUtil.dealParam(compname);
		proj_name=StringUtil.dealParam(proj_name);
		qcname=StringUtil.dealParam(qcname);
		ghbegin=StringUtil.dealParam(ghbegin);
		ghend=StringUtil.dealParam(ghend);
		String orderx=StringUtil.dealParam(pager.getParamValue("orderx"));
		StringBuilder sb=new StringBuilder();
		sb.append("from Projflow t where 1=1 ");
		sb.append(" and t.areacode like '").append(areacode).append("%' ");
		//所在地区
		if(!"".equals(proj_name)){
			sb.append(" and t.proj_name like '%").append(proj_name).append("%' ");
		}
		if(!"".equals(compname)){
			sb.append(" and t.compname like '%").append(compname).append("%' ");
		}
		if(!"".equals(qcname)){
			sb.append(" and t.qcname like '%").append(qcname).append("%' ");
		}
		if(!"".equals(ghbegin)){
			sb.append(" and t.ghbegin>='").append(ghbegin).append("' ");
		}
		if(!"".equals(ghend)){
			sb.append(" and t.ghend<='").append(ghend).append("' ");
		}
		if(StringUtils.isNotEmpty(orderx)){
			sb.append(" and t.orderx like '%").append(orderx).append("%' ");
		}
		if(htl!=null&&!"".equals(htl)){
			sb.append(" and t.htl='").append(htl).append("' ");
		}
		sb.append(" order by t.orderx desc");
		return query(sb.toString(), pager);
	}
	
	public Double sumQcnum(Pager pager){
		String areacode=pager.getParamValue("areacode");
		String compname=pager.getParamValue("compname");
		String proj_name=pager.getParamValue("proj_name");
		//地区编码，不同地区只能查看自己管辖的地区数据
		String qcname=pager.getParamValue("qcname");
		String ghbegin=pager.getParamValue("ghbegin");
		String ghend=pager.getParamValue("ghend");
		String htl=pager.getParamValue("htl");
		compname= StringUtil.dealParam(compname);
		proj_name=StringUtil.dealParam(proj_name);
		qcname=StringUtil.dealParam(qcname);
		ghbegin=StringUtil.dealParam(ghbegin);
		ghend=StringUtil.dealParam(ghend);
		String orderx=StringUtil.dealParam(pager.getParamValue("orderx"));
		StringBuilder sb=new StringBuilder();
		sb.append("select sum(t.qcnum) from Projflow t where 1=1 ");
		sb.append(" and t.areacode like '").append(areacode).append("%' ");
		//所在地区
		if(!"".equals(proj_name)){
			sb.append(" and t.proj_name like '%").append(proj_name).append("%' ");
		}
		if(!"".equals(compname)){
			sb.append(" and t.compname like '%").append(compname).append("%' ");
		}
		if(!"".equals(qcname)){
			sb.append(" and t.qcname like '%").append(qcname).append("%' ");
		}
		if(!"".equals(ghbegin)){
			sb.append(" and t.ghbegin>='").append(ghbegin).append("' ");
		}
		if(!"".equals(ghend)){
			sb.append(" and t.ghend<='").append(ghend).append("' ");
		}
		if(StringUtils.isNotEmpty(orderx)){
			sb.append(" and t.orderx like '%").append(orderx).append("%' ");
		}
		if(htl==null||"".equals(htl)){
			//默认统计合同量
			htl="0";
		}
		sb.append(" and t.htl='").append(htl).append("' ");
		List<Double> list=query(sb.toString(), pager);
		if(list.size()==0){
			return (new Double(0));
		}
		if(list.get(0)==null){
			return (new Double(0));
		}
		return list.get(0);
	}	
	
	public List<Projflow> findAllProjFlow(){
		String hql = "from Projflow t where t.htl='1' order by t.areaname desc";
		return findList(hql);		
	}
	public void saveProjflow(Projflow projflow,List<Projprod> list){
			String qcname="";
		    double qcnum=0;
			for(Projprod object:list){
				qcname+=object.getProdname()+";";
				qcnum+=object.getGycount();
			}
	/*	if(list.size()>0){
			//qcnum=qcnum.substring(0,qcnum.length()-1);
			projflow.setQcnum(qcnum);
			qcname=qcname.substring(0,qcname.length()-1);
			projflow.setQcname(qcname);
		}*/
		projflow.setQcnum(qcnum);
		projflow.setQcname(qcname);
		save(projflow);
		for(Projprod object:list){
			save(object);
		}
	}
	public void updateProjflow(Projflow projflow,List<Projprod> list){
		String hql="delete from Projprod t where t.projflow.id=?";
		//先删除墙材使用
		executeUpdate(hql,projflow.getId());
		String qcname="";
		double qcnum = 0;
		for(Projprod object:list){
			qcname+=object.getProdname()+";";
			qcnum+=object.getGycount();
		}
		/*if(list.size()>0) {
			qcname = qcname.substring(0, qcname.length() - 1);
			//qcnum = qcnum.substring(0, qcnum.length() - 1);
			projflow.setQcnum(qcnum);
			projflow.setQcname(qcname);
		}*/
		projflow.setQcnum(qcnum);
		projflow.setQcname(qcname);
		update(projflow);
		for(Projprod object:list){
			save(object);
		}
   }
	public void deleteProjflow(Projflow projflow){
		String hql="delete from Projprod t where t.projflow.id=?";
		//先删除墙材使用
		executeUpdate(hql,projflow.getId());
		delete(projflow);
    }	
	public List<Projprod> findProjprodByFlowId(String flowId){
		String hql="select t.id,t.prodname,t.produnit,t.gycount,t.qrzsbh,t.product.id " +
				" from Projprod t where t.projflow.id='"+flowId+"' order by gycount desc";
		List<Object[]> results=findList(hql);
		List<Projprod> pprodList=new ArrayList<Projprod>();
		for(Object[] objs:results){
			Projprod pprod=new Projprod();
			pprod.setId((String)objs[0]);
			pprod.setProdname((String)objs[1]);
			pprod.setProdunit((String)objs[2]);
			pprod.setGycount((Double) objs[3]);
			pprod.setQrzsbh((String)objs[4]);
			
			Product product=new Product();
			product.setId((String)objs[5]);
			pprod.setProduct(product);
			pprodList.add(pprod);
		}
		return pprodList;
	}
	public List<Object[]> productSaletj(Map<String,String> params){
		String areacode=params.get("areacode");
		String begindate=params.get("begindate");
		String enddate=params.get("enddate");
		String companyId=params.get("companyId");
		String qcname=params.get("qcname");
		String compname=params.get("compname");
		begindate=StringUtil.dealParam(begindate);
		enddate=StringUtil.dealParam(enddate);
		qcname=StringUtil.dealParam(qcname);
		compname=StringUtil.dealParam(compname);
		StringBuilder sb=new StringBuilder();
		sb.append("select t.product.company.id,t.product.id,sum(t.gycount) from Projprod t ");
		sb.append("where t.product.company.areacode like '").append(areacode).append("%' and t.projflow.htl='0' ");
		if(!"".equals(begindate)){
			sb.append(" and t.projflow.ghbegin>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.projflow.ghbegin<='").append(enddate).append("' ");
		}
		if(!"".equals(companyId)){
			sb.append(" and t.product.company.id='").append(companyId).append("' ");
		}
		if(!"".equals(qcname)){
			sb.append(" and t.product.name like '%").append(qcname).append("%' ");
		}
		if(!"".equals(compname)){
			sb.append(" and t.product.company.name like '%").append(compname).append("%' ");
		}
		sb.append(" group by t.product.company.id,t.product.id");
		List<Object[]> results=findList(sb.toString());
		return results;
	}
	public List<Projflow> findAllProjFlow(String type){
		String hql="";
		if ("1".equals(type)) {
			hql = "from Projflow t where t.htl='1' order by t.areaname,t.compname,proj_name desc";
		}else if("2".equals(type)){
			hql = "from Projflow t where t.htl='1' order by t.compname,proj_name desc";
		}else if("3".equals(type)) {
			hql = "from Projflow t where t.htl='1' order by t.proj_name,t.compname desc";
		}
		return findList(hql);
	}
}
