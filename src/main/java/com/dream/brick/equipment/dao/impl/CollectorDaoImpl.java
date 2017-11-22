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
    public List<Qgdis> findAllArea() {
        String hql = "from Qgdis t order by t.sortorder";
        return findList(hql);
    }

    public List<Collector> findAllCollector() {
        String hql = "from Collector t order by t.sortorder";
        return findList(hql);
    }

    public Qgdis findQgdisByAreacode(String disid) {
        return (Qgdis) find(Qgdis.class, disid);
    }

    public List<Collector> findCollectorByAreacode(String areacode) {
        String hql = "from Collector t where order by t.sortorder";
        return findList(hql);
    }

    public List<Collector> findCollectorList(Pager pager) {
        String hql = "from Collector t order by t.disid";
        return query(hql, pager);
    }
}
