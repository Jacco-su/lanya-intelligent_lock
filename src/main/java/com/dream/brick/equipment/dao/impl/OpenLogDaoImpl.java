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
	public List<OpenLog> findLockLogList(String deptId, String userId, String lockName, Pager pager) {
		StringBuilder hql=new StringBuilder();
		hql.append(" from OpenLog t where 1=1");
		if(StringUtils.isNotEmpty(userId)){
            hql.append(" and t.user.id =").append(userId);
		}
		if (StringUtils.isNotEmpty(lockName)) {
			hql.append(" and t.locks.lockNum =").append(lockName);
		}
		hql.append(" order by t.createTime desc");
		return query(hql.toString(), pager);
	}

	public List<OpenLog> qLockLogList(String deptId, String lockName, Pager pager) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from OpenLog t where 1=1");
		if (StringUtils.isNotEmpty(lockName)) {
			hql.append(" and t.lockName =").append(lockName);
		}
		System.out.println(hql.toString());
		hql.append(" order by t.createTime desc");
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

	@Override
	public List<OpenLog> queryTime(String deptId, String openTime, String createTime, Pager pager) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from OpenLog t where 1=1");

		if (StringUtils.isNotEmpty(openTime)) {
			hql.append(" and openTime <= '").append(openTime).append("'");
		}
		if (StringUtils.isNotEmpty(createTime)) {
			hql.append(" and createTime >= '").append(createTime).append("'");
		}

		System.out.println(hql.toString());
		return query(hql.toString(), pager);
//		return findList(hql.toString());
	}

	@Override
	public List<OpenLog> findList(
			String authStartTime,
			String authEndTime,
			String userId,
			Pager pager) {
		StringBuilder hql = new StringBuilder();
		hql.append("from OpenLog t where 1=1 ");
		if (StringUtils.isNotEmpty(authStartTime)) {
			//authStartTime= FormatDate.dateParse(authStartTime);
			hql.append(" and t.openTime  >='").append(authStartTime).append("'");

		}
		if (StringUtils.isNotEmpty(authEndTime)) {
			//authEndTime= FormatDate.dateParse(authEndTime);
			hql.append(" and t.createTime  <= '").append(authEndTime).append("'");
		}
		if (StringUtils.isNotEmpty(userId)) {
			hql.append(" and t.user.username  like '%").append(userId).append("%'");
		}
		hql.append(" order by t.createTime desc");
		return query(hql.toString(), pager);
	}

	public List<OpenLog> queryList(String deptId, String authName, String authStartTime, String authEndTime, String userId, Pager page) {
		StringBuilder hql = new StringBuilder();
		return query(hql.toString(), page);
	}
}
