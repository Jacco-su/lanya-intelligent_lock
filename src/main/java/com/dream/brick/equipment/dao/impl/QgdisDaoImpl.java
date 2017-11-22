package com.dream.brick.equipment.dao.impl;


import com.dream.brick.equipment.bean.Area;
import com.dream.brick.equipment.bean.Qgdis;
import com.dream.brick.equipment.dao.QgdisDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据库操作实现类
 */


@Transactional
@Repository
public class QgdisDaoImpl extends BaseDaoImpl implements QgdisDao {
    public List<Area> findAllArea() {
        String hql = "from Area t order by t.sortorder";
        return findList(hql);
    }

    public List<Qgdis> findAllQgdis() {
        String hql = "from Qgdis t order by t.sortorder";
        return findList(hql);
    }

    public Area findAreaByAreacode(String areacode) {
        return (Area) find(Area.class, areacode);
    }

    public List<Qgdis> findQgdisByAreacode(String areacode) {
        String hql = "from Qgdis t where t.areacode like '" + areacode + "%' order by t.sortorder";
        return findList(hql);
    }

    public List<Qgdis> findQgdisList(Pager pager) {
        String hql = "from Qgdis t order by t.areacode";
        return query(hql, pager);
    }
}
