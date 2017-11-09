package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.CheckDetail;
import com.dream.brick.equipment.bean.Detailprod;

import java.util.List;
import java.util.Map;

public interface CheckDetailDao  extends BaseDao {
	public List<CheckDetail> findCheckDetailList(Pager pager);
	public void saveCheckDetail(CheckDetail checkDetail, List<Detailprod> list);
	public void updateCheckDetail(CheckDetail checkDetail, List<Detailprod> list);
	public void deleteCheckDetail(CheckDetail checkDetail);
	/**
	 * 现场核查明细，墙材使用查询
	 * **/
	public List<Detailprod> findDprodByDetailId(String chdetailId);
	 void saveCheckDetail(CheckDetail checkDetail, Detailprod detailprod);
	CheckDetail getProjectByName(String name);
	Object[] sumCheckDetail(Pager pager);
	CheckDetail getCdh(String name);
	 List<CheckDetail> serarch(Map<String, String> params) throws Exception;
}
