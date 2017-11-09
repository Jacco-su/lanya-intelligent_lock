package com.dream.brick.equipment.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.ProductNew;

import java.util.List;

/**
 * Created by taller on 16/3/16.
 */
public interface ProductNewDao extends BaseDao {
     List<ProductNew> findProductNewList(Pager pager);
     List<ProductNew> searchProductNewList(Pager pager);
     ProductNew findBasicProductNew(String id);
     List<ProductNew> searchProductNewList(String id);
}
