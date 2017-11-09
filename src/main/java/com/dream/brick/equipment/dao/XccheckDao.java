package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Xccheck;

import java.util.List;
import java.util.Map;

public interface XccheckDao  extends BaseDao {
	public List<Xccheck> findXccheckList(Pager pager);
	Xccheck getProjectByName(String name);
	Double sum(Pager pager);
	List<Xccheck> serarch(Map<String, String> params) throws Exception;
}
