package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.FundDetails;

import java.util.List;
import java.util.Map;

public interface FundDetailsDao extends BaseDao {
	 List<FundDetails> findFundList(Pager pager);
	List<FundDetails> searchFund(Map<String, String> params) throws Exception;
	Object[] sumZhengShou(Pager pager);
}
