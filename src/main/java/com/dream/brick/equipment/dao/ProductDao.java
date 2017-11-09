package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao extends BaseDao {
	public List<Product> findProductList(Pager pager);
	public List<Product> searchProductList(Pager pager);
	public Product findBasicProduct(String id);
	List<Product> searchProductList(String id);
	Product searchProductQrzsbh(String qrzsbh);

	Product searchProduct(Map<String, String> params);
}
