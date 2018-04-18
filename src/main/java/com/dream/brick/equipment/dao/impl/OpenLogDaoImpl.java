package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.OpenLog;
import com.dream.brick.equipment.dao.IOpenLogDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class OpenLogDaoImpl extends BaseDaoImpl implements IOpenLogDao {
	public List<OpenLog> findLockLogList(String deptId, Pager pager) {
		StringBuilder hql=new StringBuilder();
		hql.append(" from OpenLog t where 1=1 order by t.createTime desc");
		return query(hql.toString(), pager);
	}

	@Override
	public List<OpenLog> findOpenLog(Map<String, String> params) {
		StringBuilder hql=new StringBuilder();
		hql.append(" from OpenLog t where 1=1");
		if(StringUtils.isNotEmpty(params.get("authStartTime"))){
			hql.append(" and openTime >= '").append(params.get("authStartTime")).append("'");
		}
		if(StringUtils.isNotEmpty(params.get("authEndTime"))){
			hql.append(" and openTime <= '").append(params.get("authEndTime")).append("'");
		}
		return findList(hql.toString());
	}
}
