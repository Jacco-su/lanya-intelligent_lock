package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Xccheck;
import com.dream.brick.equipment.dao.XccheckDao;
import com.dream.util.StringUtil;
import com.dream.util.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class XccheckDaoImpl  extends BaseDaoImpl implements XccheckDao {
	public List<Xccheck> findXccheckList(Pager pager){
		
		 String areacode=pager.getParamValue("areacode");
		 //地区编码，不同地区只能查看自己管辖的地区数据		
		 
			String begindate=pager.getParamValue("begindate");
			String proj_name=pager.getParamValue("proj_name");
			String enddate=pager.getParamValue("enddate");
			begindate=StringUtil.dealParam(begindate);
			proj_name=StringUtil.dealParam(proj_name);
			enddate=StringUtil.dealParam(enddate);
		String builder=StringUtil.dealParam(pager.getParamValue("builder"));
		 StringBuilder sb=new StringBuilder("");
		 sb.append("from Xccheck t where ");
		 sb.append("t.project.areacode like '").append(areacode).append("%' ");
		 if(!"".equals(proj_name)){
				 sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
		 }
		 if(!"".equals(begindate)){
			 sb.append(" and t.check_date>='").append(begindate).append("' ");
	     }	
		 if(!"".equals(enddate)){
			 sb.append(" and t.check_date<='").append(enddate).append("' ");
	     }
		if(StringUtils.isNotEmpty(builder)){
			sb.append(" and t.project.builder like '%").append(builder).append("%'");
		}
		 sb.append(" order by t.sqdate asc");
		 return query(sb.toString(), pager);
	}
	@Override
	public Xccheck getProjectByName(String name) {
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id from Xccheck t ");
		sb.append(" where t.project.name ='").append(name).append("' ");
		String hql=sb.toString();
		List<String> results=findList(hql);
		if(results!=null&&results.size()>0){
			String objs=results.get(0);
			Xccheck comp=new Xccheck();
			comp.setId(objs);
			return comp;
		}
		return null;
	}
	@Override
	public Double sum(Pager pager) {
		StringBuilder sb=new StringBuilder();
		String areacode=pager.getParamValue("areacode");
		String begindate=pager.getParamValue("begindate");
		String proj_name=pager.getParamValue("proj_name");
		String enddate=pager.getParamValue("enddate");
		begindate=StringUtil.dealParam(begindate);
		proj_name=StringUtil.dealParam(proj_name);
		enddate=StringUtil.dealParam(enddate);
		String builder=StringUtil.dealParam(pager.getParamValue("builder"));
		sb.append("select SUM(t.checkmj) from Xccheck t ");
		sb.append(" where t.project.areacode like '").append(areacode).append("' ");
		if(!"".equals(proj_name)){
			sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
		}
		if(!"".equals(begindate)){
			sb.append(" and t.check_date>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.check_date<='").append(enddate).append("' ");
		}
		if(StringUtils.isNotEmpty(builder)){
			sb.append(" and t.project.builder like '%").append(builder).append("%'");
		}
		List<Double> results=findList(sb.toString());
		return results.get(0);
	}

	@Override
	public List<Xccheck> serarch(Map<String, String> params) throws Exception {
		String areaCode=params.get("areacode");
		StringBuilder sb=new StringBuilder("");
		sb.append("SELECT @rownum\\:=@rownum+1 AS xuhao,z.sqdate,p.builder as builder,p. NAME AS projname,p.address as address,z.checkmj,z.lxren,z.check_user,z.check_date,z.remark");
		sb.append(" FROM (SELECT @rownum\\:=0) r,t_xccheck z,t_project p WHERE z.project_id = p.id AND p.areacode like '%").append(areaCode).append("'");
		sb.append(" order by z.djtime asc");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("xuhao", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sqdate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("builder", StandardBasicTypes.STRING);
		sqlQuery.addScalar("projname", StandardBasicTypes.STRING);
		sqlQuery.addScalar("address", StandardBasicTypes.STRING);
		sqlQuery.addScalar("checkmj", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("lxren", StandardBasicTypes.STRING);
		sqlQuery.addScalar("check_user", StandardBasicTypes.STRING);
		sqlQuery.addScalar("check_date", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers
				.aliasToBean(Xccheck.class));
		return sqlQuery.list();
	}
}
