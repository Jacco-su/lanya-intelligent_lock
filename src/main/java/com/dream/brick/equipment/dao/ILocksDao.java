package com.dream.brick.equipment.dao;

import com.dream.brick.equipment.bean.Locks;
import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;

import java.util.List;

/**
 * 访问数据库接口
 */

public interface ILocksDao extends BaseDao {

    public List<Locks> findAllLocks();

    List<Locks> findLocksByDis(String disId);
    public List<Locks> findLocksList(Pager pager);


}
