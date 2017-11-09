package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Fuchi;

import java.util.List;
import java.util.Map;


public interface FuchiDao extends BaseDao {
	public List<Fuchi> findFuchiList(Pager pager);
	public List<Object[]> fuchitj(Map<String, String> params);
}
