package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.AuthLog;
import com.dream.brick.equipment.bean.Charts;
import com.dream.brick.equipment.dao.IAuthLogDao;
import com.dream.brick.equipment.dao.IChartsDao;
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
public class AuthLogDaoImpl extends BaseDaoImpl implements IAuthLogDao {
    @Override
    public List<AuthLog> findAll() {
        String hql = "from AuthLog";
        return findList(hql);
    }

    @Override
    public List<AuthLog> findList(Pager pager) {
        String hql = "from AuthLog";
        return query(hql,pager);
    }
}
