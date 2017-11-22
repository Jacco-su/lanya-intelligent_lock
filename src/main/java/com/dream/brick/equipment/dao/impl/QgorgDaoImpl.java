package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Area;
import com.dream.brick.equipment.bean.Qgorg;
import com.dream.brick.equipment.dao.QgorgDao;
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
public class QgorgDaoImpl extends BaseDaoImpl implements QgorgDao {
    public List<Area> findAllArea(){
        String hql="from Area t order by t.sortorder";
        return findList(hql);
    }

    public List<Qgorg> findAllQgorg(){
        String hql="from Qgorg t order by t.sortorder";
        return findList(hql);
    }

    public Area findAreaByAreacode(String areacode){
        return (Area)find(Area.class,areacode);
    }

    public List<Qgorg> findQgorgByAreacode(String areacode){
        String hql="from Qgorg t where t.areacode like '"+areacode+"%' order by t.sortorder";
        return findList(hql);
    }

    public List<Qgorg> findQgorgList(Pager pager){
        String hql="from Qgorg t order by t.areacode";
        return query(hql,pager);
    }
}
