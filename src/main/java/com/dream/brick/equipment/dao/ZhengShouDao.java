package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.ZhengShou;

import java.util.List;
import java.util.Map;

public interface ZhengShouDao extends BaseDao {
	public List<ZhengShou> findzsList(Pager pager);
	 List<ZhengShou> serarch(Map<String, String> params) throws Exception;
	Object[] sumZhengShou(Pager pager);
}
