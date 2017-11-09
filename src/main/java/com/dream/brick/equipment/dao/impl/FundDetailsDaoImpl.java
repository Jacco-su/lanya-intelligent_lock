package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.FundDetails;
import com.dream.brick.equipment.dao.FundDetailsDao;
import com.dream.util.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class FundDetailsDaoImpl extends BaseDaoImpl implements FundDetailsDao{
	public List<FundDetails> findFundList(Pager pager){
		 String areacode=pager.getParamValue("areacode");
		////地区编码，不同地区只能查看自己管辖的地区数据
			String begindate=pager.getParamValue("begindate");
			String enddate=pager.getParamValue("enddate");
			begindate=StringUtil.dealParam(begindate);
			enddate=StringUtil.dealParam(enddate);
			 StringBuilder sb=new StringBuilder("");
			 sb.append("from FundDetails t where ");
			 sb.append("t.areacode like '").append(areacode).append("%' ");
			 if(!"".equals(begindate)){
				 sb.append(" and t.srdate>='").append(begindate).append("' ");
		     }	
			 if(!"".equals(enddate)){
				 sb.append(" and t.srdate<='").append(enddate).append("' ");
		     }
			 sb.append(" order by t.srdate,t.ph asc");
		 return query(sb.toString(), pager);
	}
	public List<FundDetails> searchFund(Map<String, String> params) throws Exception {
		/**
		 * @author       陶乐乐(wangyiqianyi@qq.com)
		 * @Description: 收入数据统计
		 * @date         2016-03-29 14:49:24
		 * @params       [params]
		 * @return       java.util.List<com.dream.brick.equipment.bean.FundDetails>
		 * @throws       Exception
		 */
		String areaCode=params.get("areacode");
		StringBuilder sb=new StringBuilder("");
		sb.append("SELECT @rownum\\:=@rownum+1 AS id,p.dswd,p.kpr,p.srdate,p.tzsph,p.ph,p.jkrname,p.bjmj,p.fund,p.remark");
		sb.append(" FROM (SELECT @rownum\\:=0) r,t_fund_details  p WHERE  p.areacode like '%").append(areaCode).append("'");
		sb.append(" order by p.srdate,p.ph asc");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("dswd", StandardBasicTypes.STRING);
		sqlQuery.addScalar("kpr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("srdate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("tzsph", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ph", StandardBasicTypes.STRING);
		sqlQuery.addScalar("jkrname", StandardBasicTypes.STRING);
		sqlQuery.addScalar("bjmj",StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("fund", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("remark",StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers
				.aliasToBean(FundDetails.class));
		return sqlQuery.list();
	}
	@Override
	public Object[] sumZhengShou(Pager pager) {
		String areacode=pager.getParamValue("areacode");
		String begindate=pager.getParamValue("begindate");
		String enddate=pager.getParamValue("enddate");
		begindate=StringUtil.dealParam(begindate);
		enddate=StringUtil.dealParam(enddate);
		StringBuilder sb=new StringBuilder();
		sb.append("select SUM(t.bjmj),SUM(t.fund) from FundDetails t ");
		sb.append("where t.areacode like '").append(areacode).append("%' ");
		if(!"".equals(begindate)){
			sb.append(" and t.srdate>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.srdate<='").append(enddate).append("' ");
		}
		List<Object[]> results=findList(sb.toString());
		return results.get(0);
	}
}
