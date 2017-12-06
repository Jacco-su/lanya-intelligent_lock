package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Collector;
import com.dream.brick.equipment.bean.Qgdis;
import com.dream.brick.equipment.dao.CollectorDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据库 接口 实现类
 */


@Transactional
@Repository
public class CollectorDaoImpl extends BaseDaoImpl implements CollectorDao {
    public List<Qgdis> findAllQgdis() {
        String hql = "from Qgdis t order by t.sortorder";

        return findList(hql);
    }

    public List<Collector> findAllCollector() {
        String hql = "from Collector t order by t.sortorder";
        return findList(hql);
    }

    public Qgdis findQgdisByAreacode(String disId) {
        return (Qgdis) find(Qgdis.class, disId);
    }

    public List<Collector> findCollectorByQgdisid(String disId) {
        String hql = "from Collector t where order by t.sortorder";
        return findList(hql);
    }

    public List<Collector> findCollectorList(Pager pager, String disId) {
//        String hql = "from Collector t order by t.sortorder";
        String hql = "from Collector t,Qgdis q where q.id = t.disId order by t.sortorder ";

        return query(hql, pager);
    }

    public void addCollector(Collector collector) {
        save(collector);
//        List<UserRole> roleList=user.getRoleList();
//        for(UserRole ur:roleList){
//            ur.setCollector(collector);
//            save(ur);
        //       }
    }
}
