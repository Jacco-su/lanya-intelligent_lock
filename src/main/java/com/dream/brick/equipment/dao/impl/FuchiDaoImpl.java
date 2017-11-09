package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Fuchi;
import com.dream.brick.equipment.dao.FuchiDao;
import com.dream.util.StringUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class FuchiDaoImpl extends BaseDaoImpl implements FuchiDao {
	public List<Fuchi> findFuchiList(Pager pager){
		 String areacode=pager.getParamValue("areacode");
		//地区编码，不同地区只能查看自己管辖的地区数据	
		String begindate=pager.getParamValue("begindate");
		String compname=pager.getParamValue("compname");
		String enddate=pager.getParamValue("enddate");
		String company_id=pager.getParamValue("company_id");
		String item_bt_id=pager.getParamValue("item_bt_id");
		begindate=StringUtil.dealParam(begindate);
		compname=StringUtil.dealParam(compname);
		enddate=StringUtil.dealParam(enddate);			 
		company_id=StringUtil.dealParam(company_id);
		item_bt_id=StringUtil.dealParam(item_bt_id);	
		 StringBuilder sb=new StringBuilder("");
		 sb.append("from Fuchi t where  ");
		 sb.append("t.company.areacode like '").append(areacode).append("%' ");
		 if(!"".equals(compname)){
			 sb.append(" and t.company.name like '%").append(compname).append("%' ");
		 }
		 if(!"".equals(begindate)){
			 sb.append(" and t.sbdate>='").append(begindate).append("' ");
	     }	
		 if(!"".equals(enddate)){
			 sb.append(" and t.sbdate<='").append(enddate).append("' ");
	     }
		 if(!"".equals(company_id)){
			 sb.append(" and t.company.id='").append(company_id).append("' ");
	     }
		 if(!"".equals(item_bt_id)){
			 sb.append(" and t.item_bt_id='").append(item_bt_id).append("' ");
	     }		 
		 
		sb.append(" order by t.djtime desc");
		 //地区编码，不同地区只能查看自己管辖的地区数据	
		 return query(sb.toString(), pager);
	}
	public List<Object[]> fuchitj(Map<String,String> params){

		String areacode=params.get("areacode");
		String begindate=params.get("begindate");
		String enddate=params.get("enddate");
		String companyId=params.get("companyId");
		String compname=params.get("compname");
		String item_bt_id=params.get("item_bt_id");
		begindate=StringUtil.dealParam(begindate);
		enddate=StringUtil.dealParam(enddate);
		compname=StringUtil.dealParam(compname);
		StringBuilder sb=new StringBuilder("");
		sb.append("select t.company.id,t.item_bt_id,sum(t.fc_zijin) from Fuchi t ");
		sb.append("where t.company.areacode like '").append(areacode).append("%' ");
		if(!"".equals(begindate)){
			sb.append(" and t.sbdate>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.sbdate<='").append(enddate).append("' ");
		}
		if(!"".equals(companyId)){
			sb.append(" and t.company.id='").append(companyId).append("' ");
		}
		if(!"".equals(item_bt_id)){
			sb.append(" and t.item_bt_id='").append(item_bt_id).append("' ");
		}
		if(!"".equals(compname)){
			sb.append(" and t.company.name like '%").append(compname).append("%' ");
		}
		sb.append(" group by t.company.id,t.item_bt_id ");
		return findList(sb.toString());
	}
}
