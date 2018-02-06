package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Collectore;
import com.dream.brick.equipment.dao.CollectoreDao;
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
public class CollectoreDaoImpl extends BaseDaoImpl implements CollectoreDao {
    //    public List<Qgdis> findAllQgdis() {
//        String hql = "from Qgdis t order by t.sortorder";
//
//        return findList(hql);
//    }
//
    public List<Collectore> findAllCollectore() {
        String hql = "from Collectore";
        return findList(hql);
    }

    //
//    public Qgdis findQgdisByAreacode(String disId) {
//        return (Qgdis) find(Qgdis.class, disId);
//    }
//
    public List<Collectore> findCollectoreByQgdisid(String disId) {
        String hql = "from Collectore t ";
        return findList(hql);
    }

    public List<Collectore> findCollectoreList(Pager pager) {
        String hql = "from Collectore";

        return query(hql, pager);
    }

    @Override
    public void addCollectore(Collectore collectore) {

        save(collectore);


    }
}