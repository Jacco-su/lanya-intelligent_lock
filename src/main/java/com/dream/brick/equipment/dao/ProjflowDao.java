package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Projflow;
import com.dream.brick.equipment.bean.Projprod;

import java.util.List;
import java.util.Map;

public interface ProjflowDao extends BaseDao {
	public List<Projflow> findProjFlowList(Pager pager);
	public List<Projflow> findAllProjFlow();
	public void saveProjflow(Projflow projflow, List<Projprod> list);
	
	/**
	 * 产品流向统计，根据流程单  墙材使用查询
	 * **/	
	public List<Projprod> findProjprodByFlowId(String flowId);
	public void updateProjflow(Projflow projflow, List<Projprod> list);
	public void deleteProjflow(Projflow projflow);
	public List<Object[]> productSaletj(Map<String, String> params);

	List<Projflow> findAllProjFlow(String type);
}
