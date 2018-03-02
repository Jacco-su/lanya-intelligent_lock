package com.dream.brick.equipment.dao;

import com.dream.brick.equipment.bean.Keyss;
import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;

import java.util.List;

/**
 * 数据库访问接口
 */

public interface IKeyssDao extends BaseDao {

    public List<Keyss> findAllKeyss();

    public List<Keyss> findKeyssList(Pager pager);

    List<Keyss> findKeyssList(String deptId,Pager pager);
    public List<Keyss> findKeyssUserList(String userId);

}
