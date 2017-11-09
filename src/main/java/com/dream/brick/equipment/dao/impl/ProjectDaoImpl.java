package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Project;
import com.dream.brick.equipment.dao.ProjectDao;
import com.dream.util.StringUtil;
import com.dream.util.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class ProjectDaoImpl  extends BaseDaoImpl implements ProjectDao {
	public List<Project> findProjectList(Pager pager){
		
		String areacode=pager.getParamValue("areacode");
		String keyword=pager.getParamValue("keyword");
		String builder= StringUtil.dealParam(pager.getParamValue("builder"));
		 if(keyword==null){
			 keyword="";
		 }
		 keyword=keyword.replaceAll("'", "").replaceAll("\"", "").trim();		
		 //地区编码，不同地区只能查看自己管辖的地区数据				
		String hql = "from Project t where t.areacode like '"+areacode+"%'  ";
		if(!"".equals(keyword)){
			hql=hql+" and t.name like '%"+keyword+"%' ";
		}
		if(StringUtils.isNotEmpty(builder)){
			hql=hql+" and t.builder like '%"+builder+"%' ";
		}
		hql=hql+" order by t.djtime desc ";
		
		return query(hql, pager);		
	}
	public List<Project> findAllProject(){
		String hql = "from Project t order by t.djtime desc";
		return findList(hql);
	}
	@Override
	public Project getProjectByName(String name) {
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.name,t.orgname,t.areaname from Project t ");
		sb.append(" where t.name ='").append(name).append("' ");
		sb.append(" order by t.areacode asc,t.djtime asc");
		String hql=sb.toString();
		List<Object[]> results=findList(hql);
		if(results!=null&&results.size()>0){
			Object[] objs=results.get(0);
			Project comp=new Project();
			comp.setId((String)objs[0]);
			comp.setName((String)objs[1]);
			comp.setOrgname((String)objs[2]);
			comp.setAreaname((String)objs[3]);
			return comp;
		}
		return null;
	}
	public List<Project> findProjectByAreacode(String areacode){
		String hql = "from Project t where t.areacode like '"+areacode+"%' order by t.djtime desc";
		return findList(hql);
	}

	public Map<String,Project> cacheAllProject(){
		Map<String,Project> projMap=new HashMap<String,Project>();
		String hql = "from Project t";
		List<Project> list=findList(hql);
		for(Project proj:list){
			projMap.put(proj.getId(), proj);
		}
		return projMap;
	}
}
