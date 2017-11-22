package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Locks;
import com.dream.brick.equipment.dao.ILocksDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class LocksDaoImpl extends BaseDaoImpl implements ILocksDao {

    public List<Locks> findAllLocks() {
        String hql = "from Locks t order by t.sortorder";
        return findList(hql);
    }

//    public List<Locks> findLocksByAreacode(String areacode){
//        String hql="from Locks t where order by t.sortorder";
//        return findList(hql);
//    }

    @Override
    public List<Locks> findLocksList(Pager pager) {
        String hql = "from Locks t order by t.sortorder";
        return query(hql, pager);
    }
}
