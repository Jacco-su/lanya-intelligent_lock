package com.dream.brick.equipment.dao.impl;


import com.dream.brick.equipment.bean.Keyss;
import com.dream.brick.equipment.dao.IKeyssDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据库访问 接口实现类
 */


@Transactional
@Repository
public class KeyssDaoImpl extends BaseDaoImpl implements IKeyssDao {


    public List<Keyss> findAllKeyss() {
        String hql = "from Keyss t order by t.sortorder";
        return findList(hql);
    }

    public List<Keyss> findKeyssList(Pager pager) {
        String hql = "from Keyss t order by t.sortorder";
        return query(hql, pager);
    }

}