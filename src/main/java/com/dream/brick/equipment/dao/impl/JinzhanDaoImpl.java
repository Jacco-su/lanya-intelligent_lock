package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Jinzhan;
import com.dream.brick.equipment.dao.JinzhanDao;
import com.dream.util.StringUtil;
import com.dream.util.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class JinzhanDaoImpl extends BaseDaoImpl implements JinzhanDao{
	public List<Jinzhan> findJinzhanList(Pager pager){
		String areacode=pager.getParamValue("areacode");
		String year = pager.getParamValue("year");
		String address = pager.getParamValue("address");
		String begindate=pager.getParamValue("begindate");
		String type=pager.getParamValue("type");
		String enddate=pager.getParamValue("enddate");
		begindate=StringUtil.dealParam(begindate);
		address=StringUtil.dealParam(address);
		enddate=StringUtil.dealParam(enddate);
		type = StringUtil.dealParam(type);
		year = StringUtil.dealParam(year);
		 //地区编码，不同地区只能查看自己管辖的地区数据
		StringBuilder sb = new StringBuilder("");
		sb.append("from Jinzhan t where t.areacode like '").append(areacode).append("%'");
		if (StringUtils.isNotEmpty(type)) {
			if("1".equals(type)){
				sb.append(" and t.type =1 ");
			}else{
				sb.append(" and t.type=2");
			}
		}
		if (!"".equals(year)) {
			sb.append(" and t.year = '").append(year).append("' ");
		}
		if(!"".equals(begindate)){
			sb.append(" and t.checktime>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.checktime<='").append(enddate).append("' ");
		}
		if(StringUtils.isNotEmpty(address)){
			sb.append(" and t.areaname like '%").append(address).append("%'");
		}
		sb.append(" order by t.checktime desc");
		 return query(sb.toString(), pager);
	}
}
