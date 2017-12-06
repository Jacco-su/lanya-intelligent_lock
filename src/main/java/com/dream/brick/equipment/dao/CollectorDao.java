package com.dream.brick.equipment.dao;

import com.dream.brick.equipment.bean.Collector;
import com.dream.brick.equipment.bean.Qgdis;
import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.Pager;

import java.util.List;

/**
 * 数据库访问接口
 */

public interface CollectorDao extends BaseDao {
    public List<Qgdis> findAllQgdis();

    public List<Collector> findAllCollector();

    public Qgdis findQgdisByAreacode(String qgdisid);

    public List<Collector> findCollectorByQgdisid(String disId);

    public List<Collector> findCollectorList(Pager pager, String qgdisId);

    public void addCollector(Collector collector);
}
