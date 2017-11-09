package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Company;

import java.util.List;
import java.util.Map;

public interface CompanyDao  extends BaseDao {
	public List<Company> findAllCompany(String areacode);
	public List<Company> findCompanyList(Pager pager);
	public List<Company> findCompanyByAreacode(String areacode);
	public List<Company> findAllCompany();
	Company getCompanyByName(String name);
	public Map<String,String> cacheCompanyName();
	public Map<String,String> cacheProdName();
	 List<Object[]> searchCompany(Map<String, String> params) throws Exception;
}
