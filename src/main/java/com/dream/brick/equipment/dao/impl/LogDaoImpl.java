package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Log;
import com.dream.brick.equipment.dao.LogDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class LogDaoImpl extends BaseDaoImpl implements LogDao {
	public List<Log> findLogList(String userId,Pager pager){
		 //String areacode=pager.getParamValue("areacode");
		 //地区编码，不同地区只能查看自己管辖的地区数据
		String hql="";
		if(StringUtils.isNotEmpty(userId)) {
			 hql = "from Log t where username='" + userId + "' order by createTime desc";
		}else{
			 hql="from Log t order by createTime desc";
		}
		 return query(hql, pager);
	}
}
