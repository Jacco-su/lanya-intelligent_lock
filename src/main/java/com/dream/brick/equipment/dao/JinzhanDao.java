package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Jinzhan;

import java.util.List;

public interface JinzhanDao extends BaseDao {
	public List<Jinzhan> findJinzhanList(Pager pager);
}
