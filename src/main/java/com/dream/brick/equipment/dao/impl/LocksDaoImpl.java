package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Locks;
import com.dream.brick.equipment.dao.ILocksDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据库 接口实现类
 */


@Transactional
@Repository
public class LocksDaoImpl extends BaseDaoImpl implements ILocksDao {

    public List<Locks> findAllLocks() {
        String hql = "from Locks";
        return findList(hql);
    }

    @Override
    public List<Locks> findLocksByDis(String disId) {
        String hql="from Locks t where dissId="+disId;
       return findList(hql);
    }

//    public List<Locks> findLocksByAreacode(String areacode){
//        String hql="from Locks t where order by t.sortorder";
//        return findList(hql);
//    }

    @Override
    public List<Locks> findLocksList(Pager pager) {
        String hql = "from Locks";
        return query(hql, pager);
    }

    public List<Locks> findLocksList(String deptId,Pager pager) {
        String hql=null;
        if(StringUtils.isNotEmpty(deptId)){
            hql = "from Locks where qgdis.dept.id= "+deptId;
        }else{
            hql = "from Locks";
        }
        return query(hql, pager);
    }



}
