package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Project;

import java.util.List;
import java.util.Map;

public interface ProjectDao extends BaseDao {
	public List<Project> findProjectList(Pager pager);
	public List<Project> findAllProject();
	Project getProjectByName(String name);
	public Map<String,Project> cacheAllProject();
	public List<Project> findProjectByAreacode(String areacode);
}
