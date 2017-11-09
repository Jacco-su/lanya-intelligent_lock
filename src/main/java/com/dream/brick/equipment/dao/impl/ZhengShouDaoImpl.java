package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.ZhengShou;
import com.dream.brick.equipment.dao.ZhengShouDao;
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
public class ZhengShouDaoImpl extends BaseDaoImpl implements ZhengShouDao{
	public List<ZhengShou> findzsList(Pager pager){
		 String areacode=pager.getParamValue("areacode");
		////地区编码，不同地区只能查看自己管辖的地区数据
			String begindate=pager.getParamValue("begindate");
			String proj_name=pager.getParamValue("proj_name");
			String enddate=pager.getParamValue("enddate");
			begindate=StringUtil.dealParam(begindate);
			proj_name=StringUtil.dealParam(proj_name);
			enddate=StringUtil.dealParam(enddate);			 
		    String builder=StringUtil.dealParam(pager.getParamValue("builder"));

			 StringBuilder sb=new StringBuilder("");
			 sb.append("from ZhengShou t where ");
			 sb.append("t.project.areacode like '").append(areacode).append("%' ");
			 if(!"".equals(proj_name)){
					 sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
			 }
			 if(!"".equals(begindate)){
				 sb.append(" and t.zsdate>='").append(begindate).append("' ");
		     }	
			 if(!"".equals(enddate)){
				 sb.append(" and t.zsdate<='").append(enddate).append("' ");
		     }
		     if(StringUtils.isNotEmpty(builder)){
                 sb.append(" and t.compname like '%").append(builder).append("%'");
			 }
			 sb.append(" order by t.zsdate asc");
			 
		 
		 return query(sb.toString(), pager);
	}
	public List<ZhengShou> serarch(Map<String, String> params) throws Exception {
		/**
		 * @author       陶乐乐(wangyiqianyi@qq.com)
		 * @Description: 搜索征收数据
		 *
		 * @date         2016-03-26 15:52:51
		 * @params       [params]
		 * @return       java.util.List<com.dream.brick.equipment.bean.ZhengShou>
		 * @throws Exception
		 */
		String areaCode=params.get("areacode");
		StringBuilder sb=new StringBuilder("");
		sb.append("SELECT @rownum\\:=@rownum+1 AS xuhao,z.zsdate,z.bjbh,p.builder as compname,p. NAME AS projectName,p.address as projectAddress,z.bjmj,z.jijin,z.lxren,z.lxr_phone,z.jktzspjh,z.srpjjdrq,z.srpjpjh");
		sb.append(" FROM (SELECT @rownum\\:=0) r,t_zhengshou z,t_project p WHERE z.project_id = p.id AND p.areacode like '%").append(areaCode).append("'");
		sb.append(" order by z.zsdate asc");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("xuhao", StandardBasicTypes.STRING);
		sqlQuery.addScalar("zsdate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("bjbh", StandardBasicTypes.STRING);
		sqlQuery.addScalar("compname", StandardBasicTypes.STRING);
		sqlQuery.addScalar("projectName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("projectAddress", StandardBasicTypes.STRING);
		sqlQuery.addScalar("bjmj", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("jijin", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("lxren", StandardBasicTypes.STRING);
		sqlQuery.addScalar("lxr_phone", StandardBasicTypes.STRING);
		sqlQuery.addScalar("jktzspjh", StandardBasicTypes.STRING);
		sqlQuery.addScalar("srpjjdrq", StandardBasicTypes.STRING);
		sqlQuery.addScalar("srpjpjh", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers
				.aliasToBean(ZhengShou.class));
		return sqlQuery.list();
	}

	@Override
	public Object[] sumZhengShou(Pager pager) {
		StringBuilder sb=new StringBuilder();
		String areacode=pager.getParamValue("areacode");
		String begindate=pager.getParamValue("begindate");
		String proj_name=pager.getParamValue("proj_name");
		String enddate=pager.getParamValue("enddate");
		begindate=StringUtil.dealParam(begindate);
		proj_name=StringUtil.dealParam(proj_name);
		enddate=StringUtil.dealParam(enddate);
		String builder=StringUtil.dealParam(pager.getParamValue("builder"));

		sb.append("select SUM(t.bjmj),SUM(t.jijin) from ZhengShou t ");
		sb.append("where t.project.areacode like '").append(areacode).append("%' ");
		if(!"".equals(proj_name)){
			sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
		}
		if(!"".equals(begindate)){
			sb.append(" and t.zsdate>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.zsdate<='").append(enddate).append("' ");
		}
		if(StringUtils.isNotEmpty(builder)){
			sb.append(" and t.compname like '%").append(builder).append("%'");
		}
		String hql=sb.toString();
		List<Object[]> results=findList(hql);
		return results.get(0);
	}
}
