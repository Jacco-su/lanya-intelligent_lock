package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Fanhuan;

import java.util.List;
import java.util.Map;

public interface FanhuanDao  extends BaseDao {
	List<Fanhuan> findFanhuanList(Pager pager);
	 Fanhuan findFanhuanById(String id, boolean hasdetail);
	 List<Object[]> fanhuantj(Map<String, String> params);
	 Double sum(Pager pager) ;
	List<Fanhuan> serarch(Map<String, String> params) throws Exception;
}
